package Chess_Business.Boards;

import Chess_Business.Pieces.Coordinate;
import Chess_Business.Pieces.Piece;
import lombok.*;

/**
 * Tile represents a square on the table, where each piece sits.
 * It has its coordinate(x,y) on the table, its Piece which sits on it (or null if it is empty),
 * and a parameter @isNextMove which represents the state if the current tile represents a next
 * position for a move.
 * */

@Setter
@Getter
public class Tile implements java.io.Serializable{

    private Coordinate coordinate;

    private Piece piece;

    private boolean isNextMove = false;

    public Tile(Coordinate coordinate, Piece piece) {
        this.coordinate = coordinate;
        this.piece = piece;
    }


    public boolean hasPiece()
    {
        return this.piece != null;
    }

    @Override
    public String toString() {
        return coordinate.toString() + " Piece "+ ((hasPiece() ? piece.toString() : "Empty"));
    }
    
    
}
