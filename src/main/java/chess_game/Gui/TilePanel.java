package Chess_Business.Gui;

import ClientSide.Client;
import Messages.Message;
import Messages.MoveMessage;
import Chess_Business.Boards.Board;
import Chess_Business.Boards.Tile;
import Chess_Business.Pieces.Coordinate;
import Chess_Business.Move.Move;
import Chess_Business.Pieces.PieceTypes;
import Chess_Business.Pieces.Color;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import Chess_Business.Constants.BOARD_Const;
import Chess_Business.Constants.GUI_Const;
import Chess_Business.Utilities.BoardUtilities;
import Chess_Business.Utilities.MoveUtilities;
import lombok.Getter;
import lombok.Setter;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * This class manages the GUI version of the board with each tile...
 * */

public class TilePanel extends JPanel {

    @Setter
    @Getter
    Coordinate coordinate;
    JLabel pieceIcon;

    public TilePanel(BoardPanel boardPanel, Coordinate coord, Board chessBoard, Client client) {

        super(new GridBagLayout());
        this.coordinate = coord;
        pieceIcon = new JLabel();
        this.add(pieceIcon);
        setPreferredSize(new Dimension(BOARD_Const.TILE_SIZE, BOARD_Const.TILE_SIZE));
        assignTileColor(chessBoard);
        assignTilePieceIcon(chessBoard);

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (client.getColor() != chessBoard.getCurrentPlayer().getColor()) {
                    return;
                }

                if (!chessBoard.hasChosenTile()) {
                    if (chessBoard.getTile(coordinate).hasPiece()) {
                        if (chessBoard.getCurrentPlayer().getColor() != chessBoard.getTile(coordinate).getPiece().getColor()) {
                            return;
                        }
                    }

                    chessBoard.setChosenTile(chessBoard.getTile(coordinate));

                    List<Move> nextMoves = chessBoard.getTile(coordinate).getPiece().availableMoves(chessBoard, coordinate);

                    for (Move move : nextMoves) {
                        move.getDestinationTile().setNextMove(true);
                    }

                } else {

                    Tile destinationTile = chessBoard.getTile(coordinate);
                    if (MoveUtilities.isValidMove(chessBoard, destinationTile)) {
                        Move move = new Move(chessBoard, chessBoard.getChosenTile(), destinationTile);
                        chessBoard.getCurrentPlayer().makeMove(chessBoard, move);

                        if (move.hasKilledPiece()) {

                            client.game.getBottomGameMenu().killedPiecesListModel.addElement(move.getKilledPiece().toString());

                        }

                        Message msg = new Message(Message.MessageTypes.MOVE);
                        MoveMessage movement = new MoveMessage();
                        movement.currentCoordinate = move.getCurrentTile().getCoordinate();
                        movement.destinationCoordinate = move.getDestinationTile().getCoordinate();

                        if (move.getKilledPiece() != null) {

                            movement.isPieceKilled = true;

                        }

                        msg.content = (Object) movement;
                        client.Send(msg);
                        chessBoard.changeCurrentPlayer();
                        client.game.getBottomGameMenu().getTurnLBL().setText("Enemy Turn");
                        client.game.getBottomGameMenu().getTurnLBL().setForeground(java.awt.Color.RED);

                        if (move.hasKilledPiece()) {

                            if (move.getKilledPiece().getType() == PieceTypes.KING) {

                                Color winnerColor;
                                winnerColor = (move.getKilledPiece().getColor() == Color.BLACK) ? Color.WHITE : Color.BLACK;
                                JOptionPane.showMessageDialog(null, "Winner: " + winnerColor.toString());
                                Message message = new Message(Message.MessageTypes.END);
                                message.content = null;
                                client.Send(message);
                            }
                        }

                    } else {
                        if (destinationTile.hasPiece()) {

                            if (chessBoard.getCurrentPlayer().getColor() != chessBoard.getTile(coordinate).getPiece().getColor()) {
                                return;
                            }

                        }
                        chessBoard.setChosenTile(destinationTile);

                    }
                    if (MoveUtilities.controlCheckState(chessBoard, Color.BLACK)) {

                        JOptionPane.showMessageDialog(null, "Check state for team : " + Color.BLACK.toString());
                        Message msg = new Message(Message.MessageTypes.CHECK);
                        msg.content = (Object) Color.BLACK;
                        client.Send(msg);

                    } else if (MoveUtilities.controlCheckState(chessBoard, Color.WHITE)) {

                        JOptionPane.showMessageDialog(null, "Check state for team : " + Color.WHITE.toString());
                        Message msg = new Message(Message.MessageTypes.CHECK);
                        msg.content = (Object) Color.WHITE;
                        client.Send(msg);
                    }
                }
                boardPanel.updateBoardGUI(chessBoard);

            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });

        validate();

    }

    public void assignTilePieceIcon(Board board) {
        //this.removeAll();
        Tile thisTile = board.getTile(this.coordinate);

        if (thisTile == null) {
            System.out.println("Tile is null");
            return;

        }

        if (thisTile.hasPiece()) {

            pieceIcon.setIcon(BoardUtilities.getImageOfTeamPiece(thisTile.getPiece().getColor(), thisTile.getPiece().getType()));
            pieceIcon.validate();

        }
        else if (!thisTile.hasPiece()) {

            pieceIcon.setIcon(null);
            pieceIcon.validate();

        }

    }

    public void assignTileColor(Board board) {

        if (this.coordinate.getX() % 2 == 0 && this.coordinate.getY() % 2 == 0) {

            this.setBackground(GUI_Const.darkColor);
        }
        else if (this.coordinate.getX() % 2 == 0 && this.coordinate.getY() % 2 == 1) {

            this.setBackground(GUI_Const.lightColor);
        }
        else if (this.coordinate.getX() % 2 == 1 && this.coordinate.getY() % 2 == 0) {

            this.setBackground(GUI_Const.lightColor);
        }
        else if (this.coordinate.getX() % 2 == 1 && this.coordinate.getY() % 2 == 1) {

            this.setBackground(GUI_Const.darkColor);

        }

        if (board.hasChosenTile()) {

            if (this.coordinate.equals(board.getChosenTile().getCoordinate())) {

                this.setBackground(GUI_Const.selectedPieceColor);

            }
        }

        if (board.getTile(coordinate.getX(), coordinate.getY()).isNextMove()) {

            this.setBackground(GUI_Const.movePieceColor);

            board.getTile(coordinate.getX(), coordinate.getY()).setNextMove(false);
        }

        this.setBorder(BorderFactory.createLineBorder(java.awt.Color.BLACK));

    }
}
