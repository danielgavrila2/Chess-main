package Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class represents the Client in the server.
 * It is implementing the most important backend features such the Send and Listen methods, which are used
 * for communication in server.
 * */

public class ServerClient {

    public Socket socket;
    public ObjectInputStream cInput;
    public ObjectOutputStream cOutput;
    public ClientListenThread clientListenThread;
    public ServerClient pair;
    public boolean isPaired;
    public boolean isWantToPair = false;
    public ClientPairingThread pairingThread;
    
    public ServerClient(Socket socket) {

        try {
            this.socket = socket;
            this.cOutput = new ObjectOutputStream(this.socket.getOutputStream());
            this.cInput = new ObjectInputStream(this.socket.getInputStream());
            this.clientListenThread = new ClientListenThread(this);
            this.pairingThread = new ClientPairingThread(this);
            this.isPaired = false;
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Send(Object msg)
    {
        try {
            this.cOutput.writeObject(msg);
        } catch (IOException ex) {
            Logger.getLogger(ServerClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Listen() {
        this.clientListenThread.start();
    }
}
