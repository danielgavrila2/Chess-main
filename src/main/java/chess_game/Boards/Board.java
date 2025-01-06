package Chess_Business.Boards;

import Chess_Business.Pieces.*;
import Chess_Business.Player.Player;
import Chess_Business.Constants.*;
import Chess_Business.Utilities.BoardUtilities;
import lombok.*;

/**
 * The board is represented as a 8x8 matrix of tiles.
 * */

public class Board implements java.io.Serializable{

    private final Tile[][] tiles;

    @Getter
    private Player whitePlayer;

    @Getter
    private Player blackPlayer;

    @Getter
    @Setter
    private Player currentPlayer;

    @Getter
    private Tile chosenTile = null;



    public boolean hasChosenTile() {
        if(chosenTile == null)
        {
            return false;
        }
        return chosenTile.getPiece() != null;
    }

    public void setChosenTile(Tile chosenTile) {
        if (!chosenTile.hasPiece()) {
            this.chosenTile = null;
        } else {
            this.chosenTile = chosenTile;
        }
    }

    public Tile getTile(Coordinate coordinate) {
        return getTile(coordinate.getX(), coordinate.getY());
    }

    public Board() {
        whitePlayer = new Player(Color.WHITE);
        blackPlayer = new Player(Color.BLACK);
        currentPlayer = whitePlayer;
        tiles = BoardUtilities.createStandartBoardTiles();

    }

    public Tile getTile(int x, int y) {
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            System.out.println("Get Tile Index Bound Of Array");
            return null;
        }
        return tiles[x][y];
    }

    public Coordinate getCoordOfGivenTeamPiece(Color color, PieceTypes pieceType) {
        for (int i = 0; i < BOARD_Const.ROW_COUNT; i++) {
            for (int j = 0; j < BOARD_Const.ROW_TILE_COUNT; j++) {
                if (!tiles[i][j].hasPiece()) {
                    continue;
                }
                if (tiles[i][j].getPiece().getColor() == color && tiles[i][j].getPiece().getType() == pieceType) {
                    return tiles[i][j].getCoordinate();
                }
            }
        }
        return null;
    }

    public void changeCurrentPlayer() {
        if (currentPlayer == whitePlayer) {
            currentPlayer = blackPlayer;
        } else {
            currentPlayer = whitePlayer;
        }
    }

}
