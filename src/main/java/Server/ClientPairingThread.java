package Server;

import Messages.Message;
import Chess_Business.Pieces.Color;

/**
 * It handles to connection between two players.
 * It is called when a Server Client is trying to find a match, this thread is starting to search for another player,
 * who is also trying to find a match.
 * When it finds 2 players who wants to match, it does the connection between them, and assigns the colors.
 * */

public class ClientPairingThread extends Thread {

    ServerClient client;

    public ClientPairingThread(ServerClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (this.client.socket.isConnected() && this.client.isWantToPair && !this.client.isPaired) {

            try {
                //We have to let only 1 player to match us
                Server.pairingLockForTwoPair.acquire(1);

                //We start to search for available players
                ServerClient chosenPair = null;

                //While the player is connected to server and it doesn't have a matchmate
                while (this.client.socket.isConnected() && chosenPair == null) {

                    // We iterate through all available players
                    for (ServerClient client : Server.clients) {

                        //If the player is available as well
                        if (client != this.client && !client.isPaired && client.isWantToPair) {

                            chosenPair = client;

                            this.client.pair = client;
                            client.pair = this.client;

                            this.client.isWantToPair = false;
                            client.isWantToPair = false;

                            client.isPaired = true;
                            this.client.isPaired = true;

                            //We send a request of connecting to the server
                            Message message = new Message(Message.MessageTypes.PAIRING);
                            message.content = "Connected";
                            Server.SendMessage(this.client, (message));
                            Server.SendMessage(chosenPair,  (message));
                            
                            //After the connection is established we will assign a color to each player
                            //The player who has the first to search will be WHITE and the other BLACK
                            Message clientStartMessage = new Message(Message.MessageTypes.START);
                            clientStartMessage.content = (Object) Color.WHITE;

                            Message pairClientStartMessage = new Message(Message.MessageTypes.START);
                            pairClientStartMessage.content = (Object) Color.BLACK;

                            Server.SendMessage(this.client, clientStartMessage);
                            Server.SendMessage(chosenPair,pairClientStartMessage);
                            break;
                        }
                    }

                    //We repeat the searching in an interval of 1 second
                    sleep(1000);
                    
                    
                }

                //After the connection is established, we open the lock to let other players to match.
                Server.pairingLockForTwoPair.release(1);
            } catch (InterruptedException ex) {
                System.out.println("ERROR! The client pairing thread is interrupted.");
            }
        }
    }
}
