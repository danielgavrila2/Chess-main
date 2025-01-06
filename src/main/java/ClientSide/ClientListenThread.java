package ClientSide;

import Messages.Message;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Messages.MoveMessage;
import Chess_Business.Boards.Board;
import Chess_Business.Move.Move;
import Chess_Business.Pieces.PieceTypes;
import Chess_Business.Pieces.Color;
import Chess_Business.Player.Player;

import javax.swing.JOptionPane;

/**
 * This class represents the backend business for a client.
 * It stands for Communication, because in this class we do the deserialization of the messages and we process them by
 * their purpose.
 * */

public class ClientListenThread extends Thread {

    Client client;

    public ClientListenThread(Client client) {
        this.client = client;
    }

    @Override
    public void run() {

        while (!this.client.socket.isClosed()) {

            try {

                Message msg = (Message) (this.client.sInput.readObject());
                switch (msg.type) {
                    case START:
                        Color serverChosenColor = (Color) msg.content;
                        this.client.setColor(serverChosenColor);
                        break;

                    case PAIRING:
                        this.client.isPaired = true;
                        this.client.game.getMainMenu().getPlayBTN().setEnabled(true);
                        this.client.game.getMainMenu().getPlayBTN().setText("Start Game");
                        this.client.game.getMainMenu().getInfoLBL().setText("Match found. Click To Start Game");
                        break;

                    case MOVE:
                        MoveMessage movement = (MoveMessage) msg.content;
                        Board board = this.client.game.getChessBoard();
                        Player player = board.getCurrentPlayer();
                        Move move = new Move(board, board.getTile(movement.currentCoordinate), board.getTile(movement.destinationCoordinate));
                        player.makeMove(board, move);
                        this.client.game.getBoardPanel().updateBoardGUI(this.client.game.getChessBoard());
                        if (move.hasKilledPiece()) {
                            if (move.getKilledPiece().getType() == PieceTypes.KING) {
                                Color winnerColor;
                                winnerColor = (move.getKilledPiece().getColor() == Color.BLACK) ? Color.WHITE : Color.BLACK;
                                JOptionPane.showMessageDialog(client.game.getGameFrame(), "Winner: " + winnerColor.toString());
                                Message message = new Message(Message.MessageTypes.END);
                                message.content = null;
                                client.Send(message);
                                break;
                            }
                        }
                        board.changeCurrentPlayer();
                        this.client.game.getBottomGameMenu().getTurnLBL().setText("Your Turn");
                        this.client.game.getBottomGameMenu().getTurnLBL().setForeground(java.awt.Color.GREEN);
                        break;

                    case CHAT:
                        //String chatMessage = String.valueOf(this.client.game.getBottomGameMenu().getSendButton());
                        String chatMessage = (String) msg.content;
                        if (client.getColor() == Color.BLACK) {
                            JOptionPane.showMessageDialog(client.game.getGameFrame(), "Message from WHITE: " + chatMessage);
                        }
                        else if (client.getColor() == Color.WHITE) {
                            JOptionPane.showMessageDialog(client.game.getGameFrame(), "Message from BLACK: " + chatMessage);
                        }

                        break;

                    case CHECK:
                        Color checkStateColor = (Color) msg.content;
                        JOptionPane.showMessageDialog(client.game.getGameFrame(), "Check state for team: " + checkStateColor.toString());
                        break;

                    case LEAVE:
                        JOptionPane.showMessageDialog(client.game.getGameFrame(), "Enemy left. Returning to the Menu.");
                        this.client.game.getGameFrame().remove(this.client.game.getBoardPanel());
                        this.client.game.createMainMenu();
                }

            } catch (IOException ex) {
                Logger.getLogger(ClientListenThread.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("The Class was not found!");
            }
        }
    }
}
