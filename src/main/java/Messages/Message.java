package Messages;


/**
 * Definition of the Message object we use to communicate with the opponent through Server.
 * */

public class Message implements java.io.Serializable {

    public enum MessageTypes {
        MATCHED, START, MOVE, END, PAIRING, CHECK, LEAVE, CHAT
    }

    public MessageTypes type;
    public Object content;

    public Message(MessageTypes type) {
        this.type = type;
    }
}
