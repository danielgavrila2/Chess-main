package Chess_Business.Constants;

import Chess_Business.Pieces.Coordinate;
import Chess_Business.Pieces.Color;
import java.util.HashMap;
import java.util.Map;

/**
 * We have here the patterns for each type of Piece, represented by the next Coordinate of tile.
 * */

public class PIECE_Moves {

    public static Coordinate[] KNIGHT_MOVES = {
            new Coordinate(2, 1),
            new Coordinate(-2, 1),
            new Coordinate(2, -1),
            new Coordinate(-2, -1),
            new Coordinate(1, 2),
            new Coordinate(-1, 2),
            new Coordinate(1, -2),
            new Coordinate(-1, -2)
    };

    public static Coordinate[] BISHOP_MOVES = {
            new Coordinate(1, 1),
            new Coordinate(-1, 1),
            new Coordinate(1, -1),
            new Coordinate(-1, -1)
    };

    public static Coordinate[] ROOK_MOVES = {
            new Coordinate(0, 1),
            new Coordinate(0, -1),
            new Coordinate(1, 0),
            new Coordinate(-1, 0)
    };

    public static Coordinate[] QUEEN_MOVES = {
            new Coordinate(0, 1),
            new Coordinate(0, -1),
            new Coordinate(1, 0),
            new Coordinate(-1, 0),
            new Coordinate(1, 1),
            new Coordinate(-1, 1),
            new Coordinate(1, -1),
            new Coordinate(-1, -1)
    };

    public static Coordinate[] BLACK_PAWN_NORMAL_MOVES = {new Coordinate(0, 1)};

    public static Coordinate[] WHITE_PAWN_NORMAL_MOVES = {new Coordinate(0, -1)};

    public static Coordinate[] WHITE_PAWN_ATTACK_MOVES = {new Coordinate(1, -1), new Coordinate(-1, -1)};

    public static Coordinate[] BLACK_PAWN_ATTACK_MOVES = {new Coordinate(1, 1), new Coordinate(-1, 1)};

    public static Coordinate[] BLACK_PAWN_START_MOVES = {new Coordinate(0, 2)};

    public static Coordinate[] WHITE_PAWN_START_MOVES = {new Coordinate(0, -2)};

    public static int BLACK_PAWNS_START_Y_POS = 1;

    public static int WHITE_PAWNS_START_Y_POS = 6;

    public static Map<Color, Map> PAWN_MOVES;

    static {
        PAWN_MOVES = new HashMap<>();

        Map<String, Coordinate[]> whitePawnMoves = new HashMap<>();
        Map<String, Coordinate[]> blackPawnMoves = new HashMap<>();

        whitePawnMoves.put("Normal", WHITE_PAWN_NORMAL_MOVES);
        whitePawnMoves.put("Attack", WHITE_PAWN_ATTACK_MOVES);
        whitePawnMoves.put("Start", WHITE_PAWN_START_MOVES);

        blackPawnMoves.put("Normal", BLACK_PAWN_NORMAL_MOVES);
        blackPawnMoves.put("Attack", BLACK_PAWN_ATTACK_MOVES);
        blackPawnMoves.put("Start", BLACK_PAWN_START_MOVES);

        PAWN_MOVES.put(Color.WHITE, whitePawnMoves);
        PAWN_MOVES.put(Color.BLACK, blackPawnMoves);

    }

    public static int getPawnStartPosY(Color color) {

        if (color == Color.WHITE) {
            return WHITE_PAWNS_START_Y_POS;
        } else {
            return BLACK_PAWNS_START_Y_POS;
        }

    }

}
