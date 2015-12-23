package chat;

import java.io.Serializable;
import java.time.LocalDateTime;


class Message implements Serializable {
    private final MessageType type;
    private final String text;
    private final String user;
    private final LocalDateTime time;

    public Message(final MessageType type, String text, String user, LocalDateTime time) {
        this.type = type;
        this.text = text;
        this.user = user;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public String getUser() {
        return user;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Message{" +
                "type=" + type +
                ", text='" + text + '\'' +
                ", user='" + user + '\'' +
                ", time=" + time +
                '}';
    }
}
