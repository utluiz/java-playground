package chat;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Chat client
 */
public class Client {

   //identifies different threads for logging purposes
   private static AtomicInteger integer = new AtomicInteger();

    /**
     * Handle incoming messages, server input stream
     */
    private class IncomingThread extends Thread {

        //server input stream
        private final ObjectInputStream inputStream;

        public IncomingThread(InputStream inputStream) throws IOException {
            //wrap input stream
            this.inputStream = new ObjectInputStream(inputStream);
            //define thread name
            setName("[" + user + ":in:" + integer.incrementAndGet() + "]");
        }

        @Override
        public void run() {
            try {
                //read messages "forever"
                for (;;) {
                    //wait (blocked) for new messages to arrive
                    final Message message = (Message) inputStream.readObject();
                    System.out.format(Thread.currentThread().getName() + " received %s%n", message);

                    //decies what to do according to the message type
                    switch (message.getType()) {
                        case Welcome:
                            //user was accepted
                            System.out.println(Thread.currentThread().getName() + " welcomed!");
                            break;
                        case NotWelcome:
                            //user was NOT accepted, so quit
                            System.out.println(Thread.currentThread().getName() + " not welcomed!");
                            return;
                        case InitialGreeting:
                            //acknowledge a new user in chat
                            showMessage(message.getUser(), "[user joined chat]", message.getTime());
                            break;
                        case ByeBye:
                            //acknowledge a user leaving chat
                            showMessage(message.getUser(), "[user quit chat]", message.getTime());
                            break;
                        case Message:
                            //normal message
                            showMessage(message.getUser(), message.getText(), message.getTime());
                            break;
                    }
                }
            } catch (SocketException e) {
                //socket error, maybe closed, so no real error
                System.out.println(Thread.currentThread().getName() + " [socket]: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                //if it's finished for any reason, adds a "finish" message to unblock and finish the outgoing thread
                outgoingMessagesToServer.add(new Message(MessageType.Finish, "", "", LocalDateTime.now()));
            }
        }
    }

    /**
     * Handle outgoing messages, server output stream
     */
    private class OutgoingThread extends Thread {

        //server output stream
        private final ObjectOutputStream outputStream;

        public OutgoingThread(final OutputStream outputStream) throws IOException {
            this.outputStream = new ObjectOutputStream(outputStream);
            this.outputStream.flush();
            //define thread name
            setName("[" + user + ":out:" + integer.incrementAndGet() + "]");
        }

        @Override
        public void run() {
            try {
                //send messages "forever"
                for (;;) {
                    //wait (blocked) for new messages in the queue
                    final Message message = outgoingMessagesToServer.take();

                    //quits if it's a "finish" signal
                    if (message.getType() == MessageType.Finish) {
                        return;
                    }

                    //otherwise sends the message to the server
                    System.out.format(Thread.currentThread().getName() + " sending %s%n", message);
                    outputStream.writeObject(message);
                    outputStream.flush();

                    //and quits also if the user wants to say "bye"
                    if (message.getType() == MessageType.ByeBye) {
                        return;
                    }
                }
            } catch (InterruptedException | IOException e) {
                throw new RuntimeException(e);
            } finally {
                //when thread is finishing for whatever reason, closes socket
                System.out.println(Thread.currentThread().getName() + " out finished");
                try {
                    socket.close();
                } catch (IOException e) {
                    //nothing
                }
            }
        }
    }

    //queue with messages to be sent to the server by the outgoing thread
    private final BlockingDeque<Message> outgoingMessagesToServer = new LinkedBlockingDeque<>();
    //user name of this client instance
    private final String user;
    //socket connected with the server
    private final Socket socket;

    public Client(final String user, final String host, final int port) throws IOException {
        this.user = user;
        this.socket = new Socket(host ,port);
        //adds the initial greeting that will send the user name to then server and add this user to the connected map
        outgoingMessagesToServer.add(new Message(MessageType.InitialGreeting, "", user, LocalDateTime.now()));
        //create thread to send messages, including the above one
        new OutgoingThread(socket.getOutputStream()).start();
        //create thread to receive messages (should be created later otherwise it will be blocked when creating ObjectInputStream)
        new IncomingThread(socket.getInputStream()).start();
    }

    /**
     * Sends a message to the server
     */
    public void sendMessage(String text) {
        outgoingMessagesToServer.offer(new Message(MessageType.Message, text, "", LocalDateTime.now()));
    }

    /**
     * Quits chat
     */
    public void quit() {
        outgoingMessagesToServer.offer(new Message(MessageType.ByeBye, "", "", LocalDateTime.now()));
    }

    //Display a message received from the server
    private void showMessage(final String user, final String message, final LocalDateTime time) {
        System.out.format(String.format("%s %s [%3$ty/%3$tm/%3$td %3$tH:%3$tM]: %4$s%n",
                Thread.currentThread().getName(), user, time, message));
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        Client c1 = new Client("user 1", "127.0.0.1", 8888);
        Thread.sleep(500);
        Client c2 = new Client("user 2", "127.0.0.1", 8888);
        Thread.sleep(500);
        Client c3 = new Client("user 3", "127.0.0.1", 8888);
        Thread.sleep(500);
        c1.sendMessage("Hi");
        Thread.sleep(500);
        c2.sendMessage("Hello");
        Thread.sleep(500);
        c3.sendMessage("Hullo");
        Thread.sleep(500);
        c1.sendMessage("How are you guys?");
        Thread.sleep(500);
        c3.sendMessage("Fine");
        Thread.sleep(500);
        c2.sendMessage("Good");
        Thread.sleep(500);
        new Client("user 1", "127.0.0.1", 8888);
        Thread.sleep(500);
        c2.quit();
        Thread.sleep(500);
        c3.quit();
        Thread.sleep(500);
        c1.quit();
    }

}
