package Chess_Business.Pieces;


import Chess_Business.Boards.Board;
import Chess_Business.Move.Move;
import Chess_Business.Boards.Tile;
import Chess_Business.Constants.PIECE_Moves;
import Chess_Business.Utilities.BoardUtilities;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Calculates the available moves for King.
 * */

@Setter
@Getter
public class King extends Piece {

    private boolean castlingDone = false;

    public King(Color color) {
        super(color, PieceTypes.KING);
    }

    @Override
    public List<Move> availableMoves(Board board, Coordinate currentCoord) {
        List<Move> possibleMoves = new ArrayList<Move>();
        Tile currentTile = board.getTile(currentCoord);
        Tile destinationTile;
        Coordinate destinationCoordinate;
        for (Coordinate coord : PIECE_Moves.QUEEN_MOVES) {
            destinationCoordinate = currentCoord.plus(coord);
            if(!BoardUtilities.isValidCoordinate(destinationCoordinate))
            {
                continue;
            }
            destinationTile = board.getTile(destinationCoordinate);
            if (!destinationTile.hasPiece()) {
                possibleMoves.add(new Move(board, currentTile, destinationTile));
            } else {
                if (destinationTile.getPiece().getColor() != this.getColor()) {
                    possibleMoves.add(new Move(board, currentTile, destinationTile));
                }
            }
        }

        return possibleMoves;

    }

}
