package Chess_Business.Pieces;

import Chess_Business.Boards.Board;
import Chess_Business.Move.Move;
import Chess_Business.Boards.Tile;
import java.util.ArrayList;
import java.util.List;
import Chess_Business.Constants.PIECE_Moves;
import Chess_Business.Utilities.BoardUtilities;

/**
 * Calculates the available moves for Pawn.
 * */

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, PieceTypes.PAWN);
    }

    @Override
    public List<Move> availableMoves(Board board, Coordinate currentCoord) {
        List<Move> possibleMoves = new ArrayList<Move>();
        Tile currentTile = board.getTile(currentCoord);
        Tile destinationTile;


        for (Coordinate coord : (Coordinate[]) PIECE_Moves.PAWN_MOVES.get(this.getColor()).get("Normal")) {

            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue;
            }

            destinationTile = board.getTile(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                possibleMoves.add(new Move(board, currentTile, destinationTile));
            }

        }

        if (currentTile.getCoordinate().getY() == PIECE_Moves.getPawnStartPosY(this.getColor())) {

            for (Coordinate coord : (Coordinate[]) PIECE_Moves.PAWN_MOVES.get(this.getColor()).get("Start")) {

                if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                    continue;
                }

                destinationTile = board.getTile(currentCoord.plus(coord));

                if (!destinationTile.hasPiece()) {
                    possibleMoves.add(new Move(board, currentTile, destinationTile));
                }

            }
        }

        for (Coordinate coord : (Coordinate[]) PIECE_Moves.PAWN_MOVES.get(this.getColor()).get("Attack")) {

            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue;
            }

            destinationTile = board.getTile(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                continue;
            } else {
                if (destinationTile.getPiece().getColor() != this.getColor()) {
                    possibleMoves.add(new Move(board, currentTile, destinationTile));
                }
            }
        }

        return possibleMoves;
    }

}
