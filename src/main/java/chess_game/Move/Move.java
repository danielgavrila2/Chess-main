package Chess_Business.Move;

import Chess_Business.Boards.Board;
import Chess_Business.Boards.Tile;
import Chess_Business.Pieces.Piece;
import lombok.Getter;
import lombok.Setter;


/**
 * This class represents a move and is represented by the board, the start and the destination, and the piece.
 * */

@Setter
@Getter
public class Move implements java.io.Serializable{

    Board board;

    Tile currentTile;

    Tile destinationTile;

    Piece movedPiece;

    Piece killedPiece;

    public Move(Board board, Tile currentTile, Tile destinationTile) {
        this.board = board;
        this.currentTile = currentTile;
        this.destinationTile = destinationTile;
        this.movedPiece = currentTile.getPiece();
        if (destinationTile.hasPiece()) {
            killedPiece = destinationTile.getPiece();
        }
    }

    
    public boolean hasKilledPiece() {
        return this.killedPiece != null;
    }
}
