package Chess_Business.Utilities;

import Chess_Business.Boards.Board;
import Chess_Business.Boards.Tile;
import Chess_Business.Pieces.Coordinate;
import Chess_Business.Move.Move;
import Chess_Business.Pieces.PieceTypes;
import Chess_Business.Pieces.Color;
import Chess_Business.Constants.PIECE_Moves;

/**
 * This class helps us to test if the move is valid and to generate next moves.
 * */

public class MoveUtilities {

    public static boolean isValidMove(Board board, Tile destinationTile) {

        if (!board.hasChosenTile()) {
            return false;
        }

        for (Move move : board.getChosenTile().getPiece().availableMoves(board, board.getChosenTile().getCoordinate())) {

            if (move.getDestinationTile().getCoordinate().equals(destinationTile.getCoordinate())) {
                return true;
            }

        }

        return false;

    }

    public static boolean controlCheckState(Board board, Color color) {

        Tile destinationTile;
        Coordinate currentCoord = board.getCoordOfGivenTeamPiece(color, PieceTypes.KING);

        for (Coordinate coord : PIECE_Moves.KNIGHT_MOVES) {

            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue;
            }
            destinationTile = board.getTile(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                continue;
            } else {
                if (destinationTile.getPiece().getColor() != color && destinationTile.getPiece().getType() == PieceTypes.KNIGHT) {
                    return true;
                }
            }
        }
        

        Coordinate destinationCoordinate;

        for (Coordinate coord : PIECE_Moves.ROOK_MOVES) {

            destinationCoordinate = currentCoord;

            while (BoardUtilities.isValidCoordinate(destinationCoordinate.plus(coord))) {

                destinationCoordinate = destinationCoordinate.plus(coord);
                destinationTile = board.getTile(destinationCoordinate);

                if (!destinationTile.hasPiece()) {
                    continue;
                } else {

                    if (destinationTile.getPiece().getColor() == color) {
                        break;
                    }
                    if (destinationTile.getPiece().getType() == PieceTypes.ROOK || destinationTile.getPiece().getType() == PieceTypes.QUEEN) {
                        return true;
                    } else {
                        break;
                    }

                }
            }
        }

        for (Coordinate coord : PIECE_Moves.BISHOP_MOVES) {

            destinationCoordinate = currentCoord;

            while (BoardUtilities.isValidCoordinate(destinationCoordinate.plus(coord))) {

                destinationCoordinate = destinationCoordinate.plus(coord);
                destinationTile = board.getTile(destinationCoordinate);

                if (!destinationTile.hasPiece()) {
                    continue;
                } else {
                    if (destinationTile.getPiece().getColor() == color) {
                        break;
                    }
                    if (destinationTile.getPiece().getType() == PieceTypes.BISHOP || destinationTile.getPiece().getType() == PieceTypes.QUEEN) {
                        return true;

                    } else {
                        break;
                    }
                }
            }
        }


        for (Coordinate coord : (Coordinate[]) PIECE_Moves.PAWN_MOVES.get(color).get("Attack")) {

            if (!BoardUtilities.isValidCoordinate(currentCoord.plus(coord))) {
                continue;
            }
            destinationTile = board.getTile(currentCoord.plus(coord));

            if (!destinationTile.hasPiece()) {
                continue;
            } else {
                if (destinationTile.getPiece().getColor() != color && destinationTile.getPiece().getType() == PieceTypes.PAWN) {
                    return true;
                }
            }
        }
        return false;
    }

}
