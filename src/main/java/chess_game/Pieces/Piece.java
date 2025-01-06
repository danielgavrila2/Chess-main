package Chess_Business.Pieces;

import Chess_Business.Move.Move;
import Chess_Business.Boards.Board;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * The abstract class Piece is the base of each type of piece, which will implement its possible moves.
 * */

@Setter
@Getter
public abstract class Piece implements java.io.Serializable{

    private boolean killed = false;

    private Color color;

    private PieceTypes type;

    public Piece(Color color, PieceTypes type) {
        this.setColor(color);
        this.setType(type);
    }


    @Override
    public String toString() {
        return this.color.toString() + " " + this.type.toString();
    }

    public abstract List<Move> availableMoves(Board board, Coordinate currentCoord);

}
