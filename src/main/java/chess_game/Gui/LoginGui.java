package Chess_Business.Gui;

import Chess_Business.Database.MyJDBC;
import Chess_Business.Database.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginGui extends BaseFrame {

    public LoginGui(){
        super("Chess App Login");
    }

    @Override
    protected void addGuiComponents(){
        //bank app label
        JLabel bankingAppLabel = new JLabel("Chess Game");

        bankingAppLabel.setBounds(0,20,super.getWidth(),40);

        bankingAppLabel.setFont(new Font("Dialog", Font.BOLD, 32));

        bankingAppLabel.setHorizontalAlignment(SwingConstants.CENTER);

        add(bankingAppLabel);

        //username label
        JLabel usernameLabel = new JLabel("Username");

        usernameLabel.setBounds(20,120,getWidth() - 30, 24);

        usernameLabel.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(usernameLabel);

        //username field
        JTextField usernameField = new JTextField();

        usernameField.setBounds(20,160,getWidth() - 50, 40);

        usernameField.setFont(new Font("Dialog", Font.PLAIN , 28));

        add(usernameField);

        //password label
        JLabel passwordLabel = new JLabel("Password");

        passwordLabel.setBounds(20,280,getWidth() - 50, 24);

        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(passwordLabel);

        //password field
        JPasswordField passwordField = new JPasswordField();

        passwordField.setBounds(20,320,getWidth() - 50, 40);

        passwordField.setFont(new Font("Dialog", Font.PLAIN , 28));

        add(passwordField);

        //login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(20,460,getWidth()-50,40);
        loginButton.setFont(new Font("Dialog", Font.PLAIN, 20));

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //get username
                String username = usernameField.getText();

                //get password
                String password = String.valueOf(passwordField.getPassword());

                //validare
                User user = MyJDBC.validateLogic(username, password);

                //daca user == null atunci e invalid, altfel este un cont valid
                if (user != null){
                    //logare valida

                    //schimbam interfata, adica se intra in aplicatie
                    LoginGui.this.dispose();

                    //pornim interfata din meniul de banca
                    ClientGUI clientGUI = new ClientGUI();
                    //table.setVisible(true);

                    //afisam mesaj de succes
                    JOptionPane.showMessageDialog(clientGUI.getGameFrame(),"Logged in successfully!");
                }else{
                    JOptionPane.showMessageDialog(LoginGui.this, "Invalid username or password!");
                }
            }
        });

        add(loginButton);

        //register label
        JLabel registerLabel = new JLabel("<html><a href=\"#\">Don't have an account? Register Here</a></html>");
        registerLabel.setBounds(0,510,getWidth()-10,30);
        registerLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        registerLabel.setHorizontalAlignment(SwingConstants.CENTER);

        registerLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoginGui.this.dispose();

                //deschidem fereastra de register
                new RegisterGui().setVisible(true);
            }
        });

        add(registerLabel);

    }
}
