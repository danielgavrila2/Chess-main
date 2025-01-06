package Chess_Business.Pieces;

import Chess_Business.Boards.Board;
import Chess_Business.Move.Move;
import Chess_Business.Boards.Tile;
import Chess_Business.Constants.PIECE_Moves;
import Chess_Business.Utilities.BoardUtilities;
import java.util.ArrayList;
import java.util.List;

/**
 * Calculates the available moves for Queen.
 * */

public class Queen extends Piece {

    public Queen(Color color) {
        super(color, PieceTypes.QUEEN);
    }

    @Override
    public List<Move> availableMoves(Board board, Coordinate currentCoord) {

        List<Move> possibleMoves = new ArrayList<>();
        Tile currentTile = board.getTile(currentCoord);
        Tile destinationTile;
        Coordinate destinationCoordinate;

        for (Coordinate coord : PIECE_Moves.QUEEN_MOVES) {

            destinationCoordinate = currentCoord;

            while (BoardUtilities.isValidCoordinate(destinationCoordinate.plus(coord))) {

                destinationCoordinate = destinationCoordinate.plus(coord);
                destinationTile = board.getTile(destinationCoordinate);

                if (!destinationTile.hasPiece()) {

                    possibleMoves.add(new Move(board, currentTile, destinationTile));

                } else {

                    if (destinationTile.getPiece().getColor() != this.getColor()) {

                        possibleMoves.add(new Move(board, currentTile, destinationTile));
                        break;

                    } else {
                        break;
                    }
                }
            }
        }
        return possibleMoves;

    }

}
