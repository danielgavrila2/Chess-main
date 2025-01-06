package ClientSide;

import Messages.Message;
import Chess_Business.Pieces.Color;
import Chess_Business.Gui.ClientGUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.*;


/**
 * This class represents the Client.
 * Each Client has a unique identity, its own color, and it is connected to a game session sharing a socket with other client.
 * We have defined also the Connect method and the Send Message method which we use to communicate with the server.
 * */

public class Client {

    public Socket socket;
    public ObjectInputStream sInput;
    public ObjectOutputStream sOutput;

    @Getter
    @Setter
    private Color color = Color.NOCOLOR;

    public boolean isPaired = false;
    public String serverIP;
    public int serverPort;
    public ClientListenThread listenThread;
    public ClientGUI game;

    public Client(ClientGUI game) {
        this.game = game;
    }

    public void Connect(String serverIP, int port) {

        try {

            System.out.println("Connecting to the server");

            this.serverIP = serverIP;
            this.serverPort = port;
            this.socket = new Socket(this.serverIP, this.serverPort);

            System.out.println("Connecting to the server");

            sOutput = new ObjectOutputStream(this.socket.getOutputStream());
            sInput = new ObjectInputStream(this.socket.getInputStream());
            listenThread = new ClientListenThread(this);

            this.listenThread.start();

        } catch (IOException ex) {
            System.out.println("Cannot connected to the server.");
        }
    }

//    public void StopConnection() {
//        if (this.socket != null) {
//
//            try {
//                this.socket.close();
//                this.sOutput.flush();
//                this.sOutput.close();
//                this.sInput.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }


    public void Send(Object message) {
        try {
            this.sOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendChatMessage(String message) {

        if (message == null || message.trim().isEmpty()) {
            return;
        }

        Message chatMessage = new Message(Message.MessageTypes.CHAT);
        chatMessage.content = message;
        this.Send(chatMessage);
    }

}
