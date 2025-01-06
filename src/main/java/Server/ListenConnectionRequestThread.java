package Server;

import java.io.IOException;
import java.net.Socket;

/**
 * This thread is managing the connection requests of clients and it handles the requests to accept the sockets.
 * It runs continuously to scan for new connections.
 * */

public class ListenConnectionRequestThread extends Thread {

    private Server server;

    public ListenConnectionRequestThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (!this.server.socket.isClosed()) {
            try {
                Socket nSocket = this.server.socket.accept();
                ServerClient nClient = new ServerClient(nSocket);
                nClient.Listen();
                server.clients.add(nClient);
                
            } catch (IOException ex) {
                System.out.println("Error accepting socket!");
            }
        }
    }
}
