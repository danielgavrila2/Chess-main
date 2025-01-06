package Server;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * This thread is managing the communication messages in the server.
 * It also does the business of managing connection with other clients, and updates the conn parameters.
 *
 * */

public class ClientListenThread extends Thread {

    ServerClient client;

    public ClientListenThread(ServerClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (!this.client.socket.isClosed()) {

            try {
                Message message = (Message) (this.client.cInput.readObject());
                switch (message.type) {
                    case PAIRING:
                        this.client.isWantToPair = true;
                        this.client.pairingThread.start();
                        break;

                    case MOVE, CHECK, CHAT:
                        this.client.pair.Send(message);
                        break;

                    case END:
                        this.client.isPaired = false;
                        this.client.isWantToPair = false;
                        this.client.pair = null;

                    case LEAVE:
                        this.client.isPaired = false;
                        this.client.isWantToPair = false;
                        assert this.client.pair != null;
                        this.client.pair.isWantToPair = false;
                        this.client.pair.isPaired = false;
                        this.client.pair.pair = null;
                        this.client.pair = null;

                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
