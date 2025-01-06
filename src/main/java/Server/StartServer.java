package Server;

import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This is the starting class for the server.
 * It is refreshing at 1 second and displays the number of connections.
 * */

public class StartServer {

    public static void main(String[] args) {
        Server server = new Server(4000);
        server.ListenClientConnectionRequests();

        while (!server.socket.isClosed()) {

            try {

                System.out.println("Server is running. " + Server.clients.size() + " clients connected.");
                Thread.sleep(1000);

            } catch (InterruptedException ex) {
                Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
