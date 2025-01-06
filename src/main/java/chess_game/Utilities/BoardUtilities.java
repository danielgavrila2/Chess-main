package Chess_Business.Utilities;

import Chess_Business.Boards.Tile;
import Chess_Business.Pieces.*;
import Chess_Business.Constants.BOARD_Const;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * This class bundles all important features we need for the board.
 * */

public class BoardUtilities {

    public static boolean isValidCoordinate(Coordinate coord) {
        return (coord.getX() >= BOARD_Const.BOARD_LOWER_BOUND && coord.getX() <= BOARD_Const.BOARD_UPPER_BOUND && coord.getY() >= BOARD_Const.BOARD_LOWER_BOUND && coord.getY() <= BOARD_Const.BOARD_UPPER_BOUND);
    }

    public static ImageIcon getImageOfTeamPiece(Color color, PieceTypes pieceType) {


        /*
         * IMPORTANT!!! Replace here with your root folder
         */
        String imagePath = "C:\\Users\\danie\\IdeaProjects\\Chess-main\\ChessProject\\src\\main\\java\\chess_game\\Img\\";
        if (color == null || pieceType == null) {
            imagePath += "transparent.png";
        } else {
            if (color == Color.BLACK) {
                imagePath += "black";
            } else {
                imagePath += "white";
            }
            if (pieceType == PieceTypes.BISHOP) {
                imagePath += "_bishop.png";
            } else if (pieceType == PieceTypes.KING) {
                imagePath += "_king.png";
            } else if (pieceType == PieceTypes.QUEEN) {
                imagePath += "_queen.png";
            } else if (pieceType == PieceTypes.KNIGHT) {
                imagePath += "_knight.png";
            } else if (pieceType == PieceTypes.PAWN) {
                imagePath += "_pawn.png";
            } else if (pieceType == PieceTypes.ROOK) {
                imagePath += "_rook.png";
            }
        }
        try {
            File img = new File(imagePath);
            BufferedImage bufferedImage = ImageIO.read(img);
            return new ImageIcon(bufferedImage);

        } catch (IOException ex) {
            Logger.getLogger(BOARD_Const.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Tile[][] createStandartBoardTiles() {

        Tile[][] tiles = new Tile[BOARD_Const.ROW_COUNT][BOARD_Const.ROW_TILE_COUNT];

        // Black player definition
        tiles[0][0] = new Tile(new Coordinate(0, 0), new Rook(Color.BLACK));
        tiles[1][0] = new Tile(new Coordinate(1, 0), new Knight(Color.BLACK));
        tiles[2][0] = new Tile(new Coordinate(2, 0), new Bishop(Color.BLACK));
        tiles[3][0] = new Tile(new Coordinate(3, 0), new Queen(Color.BLACK));
        tiles[4][0] = new Tile(new Coordinate(4, 0), new King(Color.BLACK));
        tiles[5][0] = new Tile(new Coordinate(5, 0), new Bishop(Color.BLACK));
        tiles[6][0] = new Tile(new Coordinate(6, 0), new Knight(Color.BLACK));
        tiles[7][0] = new Tile(new Coordinate(7, 0), new Rook(Color.BLACK));

        for (int i = 0; i < 8; i++) {
            tiles[i][1] = new Tile(new Coordinate(i, 1), new Pawn(Color.BLACK));
            tiles[i][6] = new Tile(new Coordinate(i, 6), new Pawn(Color.WHITE));
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                tiles[j][i] = new Tile(new Coordinate(j, i), null);
            }
        }

        //White player definition
        tiles[0][7] = new Tile(new Coordinate(0, 7), new Rook(Color.WHITE));
        tiles[1][7] = new Tile(new Coordinate(1, 7), new Knight(Color.WHITE));
        tiles[2][7] = new Tile(new Coordinate(2, 7), new Bishop(Color.WHITE));
        tiles[3][7] = new Tile(new Coordinate(3, 7), new King(Color.WHITE));
        tiles[4][7] = new Tile(new Coordinate(4, 7), new Queen(Color.WHITE));
        tiles[5][7] = new Tile(new Coordinate(5, 7), new Bishop(Color.WHITE));
        tiles[6][7] = new Tile(new Coordinate(6, 7), new Knight(Color.WHITE));
        tiles[7][7] = new Tile(new Coordinate(7, 7), new Rook(Color.WHITE));
        return tiles;
    }
}
