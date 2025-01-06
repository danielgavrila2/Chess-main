package Server;

import Messages.Message;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * We use a Server using TCP Connection.
 * Basically we match for every thread a connection with an available socket and then we lock that connection.
 * We also have implemented here the sending of messages between clients.
 * */

public class Server {

    public ServerSocket socket;
    public int port;
    public ListenConnectionRequestThread listenConnectionRequestThread;
    public ClientRemovingControlThread removingControlThread;
    public static ArrayList<ServerClient> clients;

    //We have to lock the connection where we matched 2 clients
    public static Semaphore pairingLockForTwoPair = new Semaphore(1, true);

    public Server(int port) {
        try {
            this.port = port;
            this.socket = new ServerSocket(this.port);
            this.listenConnectionRequestThread = new ListenConnectionRequestThread(this);
            removingControlThread = new ClientRemovingControlThread(this);
            this.clients = new ArrayList<ServerClient>();
            
        } catch (IOException ex) {
            System.out.println("There is an error occured when opening the server on port:" + this.port);

        }
    }


    public void ListenClientConnectionRequests() {
        this.listenConnectionRequestThread.start();
    }

    public static void SendMessage(ServerClient client, Message message) {
        try {
            client.cOutput.writeObject(message);
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
