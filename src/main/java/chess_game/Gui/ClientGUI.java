package Chess_Business.Gui;

import ClientSide.Client;
import Messages.Message;
import Chess_Business.Boards.Board;

import Chess_Business.Pieces.Color;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


/**
 * This class represents the actual client-side GUI Application.
 * This class is responsible for
 * */

@Setter
@Getter
public class ClientGUI {

    private JFrame gameFrame;

    private BoardPanel boardPanel;

    private Board chessBoard;

    private MainMenu mainMenu;

    private InGameBottomMenu bottomGameMenu;

    private Client client;


    public ClientGUI() {

        this.gameFrame = new JFrame("Online Chess Game");
        this.gameFrame.setLayout(new BorderLayout());
        this.gameFrame.setSize(new Dimension(600, 350));


        ImageIcon icon = new ImageIcon("C:\\Users\\danie\\IdeaProjects\\Chess-main\\ChessProject\\src\\main\\java\\chess_game\\Img\\chesslogo.png");

        this.gameFrame.setIconImage(icon.getImage());

        this.gameFrame.setResizable(false);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.mainMenu = new MainMenu();

        this.client = new Client(this);
        this.client.Connect("127.0.0.1", 4000);

        if (this.client.socket == null) {

            JOptionPane.showMessageDialog(null, "Cannot connect to the server!");
            System.exit(0);

        }

        createMainMenu();
        this.gameFrame.setVisible(true);

    }

    public void createMainMenu() {

        this.mainMenu.getInfoLBL().setText("");
        this.mainMenu.getInfoLBL().setVisible(false);

        this.mainMenu.getPlayBTN().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (!client.isPaired) {

                    mainMenu.getInfoLBL().setVisible(true);
                    mainMenu.getInfoLBL().setText("Searching Match...");
                    mainMenu.getPlayBTN().setEnabled(false);
                    Message msg = new Message(Message.MessageTypes.PAIRING);
                    msg.content = "Searching Match";
                    client.Send(msg);
                }

                if (client.isPaired) {
                    mainMenu.getInfoLBL().setText("Match Found");
                    mainMenu.getInfoLBL().setText("Game is starting...");
                    mainMenu.getPlayBTN().setEnabled(true);
                    mainMenu.getInfoLBL().setText("");
                    mainMenu.getInfoLBL().setVisible(false);
                    createGamePanel();
                }

            }
        });

        this.gameFrame.add(mainMenu, BorderLayout.CENTER);
        this.gameFrame.setLocationRelativeTo(null);

    }


    public void createGamePanel() {

        this.gameFrame.remove(mainMenu);
        this.gameFrame.setSize(new Dimension(600, 750));
        this.chessBoard = new Board();
        this.boardPanel = new BoardPanel(this.chessBoard, this.client);
        this.bottomGameMenu = new InGameBottomMenu();
        this.bottomGameMenu.setClient(this.client);

        this.bottomGameMenu.getPlayersColorLBL().setText("Your color is " + this.client.getColor().toString());


        if(this.client.getColor() == Color.WHITE)
        {
            this.bottomGameMenu.getTurnLBL().setText("Your Turn");
            this.bottomGameMenu.getTurnLBL().setForeground(new java.awt.Color(20, 108, 0));
            this.bottomGameMenu.getPlayersColorLBL().setForeground(new java.awt.Color(255, 255, 255));
        }
        else
        {
            this.bottomGameMenu.getTurnLBL().setText("Enemy Turn");
            this.bottomGameMenu.getTurnLBL().setForeground(new java.awt.Color(143, 0, 0));
            this.bottomGameMenu.getPlayersColorLBL().setForeground(new java.awt.Color(0, 0, 0));
        }    

        this.gameFrame.add(boardPanel);
        this.gameFrame.add(this.boardPanel, BorderLayout.CENTER);
        this.gameFrame.add(this.bottomGameMenu, BorderLayout.PAGE_END);

        this.gameFrame.setVisible(true);

    }


}
