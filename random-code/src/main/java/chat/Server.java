package chat;


import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Chat server
 */
public class Server {

    /**
     * Handles incoming messages, socket input stream
     */
    private class IncomingThread extends Thread {

        //client socket
        private final Socket socket;
        //input stream to receive messages from client
        private final ObjectInputStream inputStream;
        //queue to send messages to client, to be processed in another thread
        private final BlockingDeque<Message> outgoingMessagesToClient;
        //user name to be retrieved from the first message received
        private String user;

        public IncomingThread(final Socket socket) throws IOException {
            this.socket = socket;
            this.outgoingMessagesToClient = new LinkedBlockingDeque<>();
            this.inputStream = new ObjectInputStream(socket.getInputStream());
        }

        @Override
        public void run() {
            //store information if the user already exists
            AtomicBoolean exists = new AtomicBoolean(true);
            try {

                //wait for initial greeting message (blocking if none)
                final Message initialGreeting = (Message) inputStream.readObject();
                System.out.format("Received %s%n", initialGreeting);

                //retrieve the user
                this.user = initialGreeting.getUser();

                //store new user in the user map only if it does not exists
                users.computeIfAbsent(initialGreeting.getUser(), (k) -> {
                    //mark as non-existent
                    exists.set(false);
                    //add outgoingMessagesToClient to the map
                    return outgoingMessagesToClient;
                });

                //create thread to process the outgoingMessagesToClient and send messages to the client
                //it's not necessary to create the thread until receiving the first greeting message with its user name
                new OutgoingThread(socket, outgoingMessagesToClient, user).start();

                //if the user exists, send a "not welcome" message and quit
                if (exists.get()) {
                    outgoingMessagesToClient.add(new Message(MessageType.NotWelcome, "User already exists!", "", LocalDateTime.now()));
                    return;
                }

                //if initial handshake is ok, greet the new user
                outgoingMessagesToClient.add(new Message(MessageType.Welcome, "hi", "", LocalDateTime.now()));

                //and acknowledge his/her present in front of all users
                sendMessageForAll(new Message(MessageType.InitialGreeting, "", user, LocalDateTime.now()));

                //then wait for new messages come
                for (;;) {
                    //blocks until new message or disconnect
                    final Message message = (Message) inputStream.readObject();
                    System.out.format("Received %s%n", message);

                    //if user says buy, finish
                    if (message.getType() == MessageType.ByeBye) {
                        return;
                    } else {
                        //otherwise send his/her message to everyone
                        sendMessageForAll(new Message(MessageType.Message, message.getText(), user, LocalDateTime.now()));
                    }
                }

            } catch (IOException | ClassNotFoundException e) {

                //EOF means the user disconnected, so no exception
                if (e instanceof EOFException) {
                    System.out.println("User " + user + " disconnected!");
                } else {
                    throw new RuntimeException(e);
                }

            } finally {

                //when incoming thread finished, it needs to clean up things
                //firstly, it adds a "finish" message to unblock and finish outgoing thread; it won't be sent to the client
                outgoingMessagesToClient.add(new Message(MessageType.Finish, "", "", LocalDateTime.now()));
                //if the user is legit (not duplicated or without a name), remove he/her from the list and tell everyone
                if (this.user != null && !exists.get()) {
                    users.remove(this.user);
                    sendMessageForAll(new Message(MessageType.ByeBye, "", user, LocalDateTime.now()));
                }

            }
        }
    }

    /**
     * Handles outgoing messages, socket output stream
     */
    private class OutgoingThread extends Thread {

        //client socket
        private final Socket socket;
        //output stream to send messages to the client
        private final ObjectOutputStream outputStream;
        //queue of messages to consume
        private final BlockingDeque<Message> outgoingMessagesToClient;
        //user name
        private final String user;

        public OutgoingThread(final Socket socket, final BlockingDeque<Message> outgoingMessagesToClient, final String user) throws IOException {
            this.socket = socket;
            this.outgoingMessagesToClient = outgoingMessagesToClient;
            this.user = user;
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            try {
                //process all messages
                for (;;) {
                    //wait for the next message in the outgoingMessagesToClient (blocking if none)
                    final Message message = outgoingMessagesToClient.take();

                    //process message to be sent
                    if (message.getType() == MessageType.Finish) {
                        //close socket and quit if it's a "finish" signal from the incoming thread
                        socket.close();
                        return;
                    } else {
                        //otherwise, send message
                        System.out.format("Sending to %s: %s%n", user, message);
                        outputStream.writeObject(message);
                        outputStream.flush();
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                //if take() times out, then client is inactive, so socket should be closed!
                try {
                    socket.close();
                } catch (IOException e1) {
                    //nothing
                }
            }
        }
    }

    //map with queues per user, maintains a list of connected users and allows adding messages to them
    private final ConcurrentHashMap<String, BlockingDeque<Message>> users = new ConcurrentHashMap<>();

    public Server(final int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        for (;;) {
            //create a new incoming thread per user in order to handle messages
            //outgoing thread
            new IncomingThread(serverSocket.accept()).start();
        }
    }

    //Iterated over all queues (values from ser map) and add a message
    private void sendMessageForAll(final Message message) {
        users.values().stream().forEach(q -> q.add(message));
    }

    public static void main(String[] args) throws IOException {
        new Server(8888);
    }
}
