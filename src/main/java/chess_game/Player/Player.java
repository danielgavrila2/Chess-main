package Chess_Business.Player;

import Chess_Business.Move.Move;
import Chess_Business.Boards.Board;
import Chess_Business.Pieces.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * This class is representing the player and its decisions on the board.
 * */

@Setter
@Getter
@AllArgsConstructor
public class Player implements java.io.Serializable{

    private Color color;
    
    public void makeMove(Board board, Move move)
    {
        board.getTile(move.getDestinationTile().getCoordinate()).setPiece(move.getCurrentTile().getPiece());
        board.getTile(move.getCurrentTile().getCoordinate()).setPiece(null);
   
    }

}
