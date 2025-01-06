package Chess_Business.Gui;

import ClientSide.Client;
import Chess_Business.Boards.Board;
import Chess_Business.Pieces.Coordinate;

import javax.swing.JPanel;
import Chess_Business.Constants.BOARD_Const;
import lombok.Getter;
import lombok.Setter;

import java.awt.GridLayout;


@Setter
@Getter
public class BoardPanel extends JPanel {

    private TilePanel[][] boardTiles;

    public BoardPanel(Board chessBoard,Client client) {
        super(new GridLayout(BOARD_Const.ROW_COUNT, BOARD_Const.ROW_TILE_COUNT));
        this.boardTiles = new TilePanel[BOARD_Const.ROW_COUNT][BOARD_Const.ROW_TILE_COUNT];
        for (int i = 0; i < BOARD_Const.ROW_COUNT; i++) {
            for (int j = 0; j < BOARD_Const.ROW_TILE_COUNT; j++) {
                TilePanel tilePanel = new TilePanel(this, new Coordinate(j, i), chessBoard,client);
                this.boardTiles[i][j] = tilePanel;
                add(tilePanel);
            }

        }
    }


    public void updateBoardGUI(Board board) {
        for (int i = 0; i < BOARD_Const.ROW_COUNT; i++) {
            for (int j = 0; j < BOARD_Const.ROW_TILE_COUNT; j++) {
                boardTiles[i][j].assignTileColor(board);
                boardTiles[i][j].assignTilePieceIcon(board);
                
            }

        }
    }
}
