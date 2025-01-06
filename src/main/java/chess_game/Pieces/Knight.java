package Chess_Business.Pieces;

import Chess_Business.Move.Move;
import Chess_Business.Boards.Board;
import Chess_Business.Boards.Tile;
import java.util.ArrayList;
import java.util.List;
import Chess_Business.Constants.PIECE_Moves;
import Chess_Business.Utilities.BoardUtilities;

/**
 * Calculates the available moves for Knight.
*/
public class Knight extends Piece {

    public Knight(Color color) {
        super(color, PieceTypes.KNIGHT);
    }
    
    @Override
    public List<Move> availableMoves(Board board, Coordinate currentCoord) {

        List<Move> possibleMoves = new ArrayList<Move>();

        Tile destinationTile;

        for (Coordinate coord : PIECE_Moves.KNIGHT_MOVES) {
 
            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue;
            }
            destinationTile = board.getTile(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {

                possibleMoves.add(new Move(board,board.getTile(currentCoord),destinationTile));

            } else {

                if (destinationTile.getPiece().getColor() != this.getColor()) {
                    possibleMoves.add(new Move(board,board.getTile(currentCoord),destinationTile));
                }

            }
        }
        return possibleMoves;
    }
}
