package Server;

/**
 * This thread will remove from server those clients who lost the connection or stopped the game by closing it.
 * */

public class ClientRemovingControlThread extends Thread{

    private Server server;

    public ClientRemovingControlThread(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        
        while(!this.server.socket.isClosed())
        {
            Server.clients.removeIf(client -> client.socket.isClosed());
        }
    }
    
    
}
