package Chess_Business.Gui;

import Chess_Business.Database.MyJDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RegisterGui extends BaseFrame {

    public RegisterGui(){
        super("Register");
    }

    @Override
    protected void addGuiComponents() {

        JLabel bankingAppLabel = new JLabel("Login into Chess Game!");

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

        passwordLabel.setBounds(20,220,getWidth() - 50, 24);

        passwordLabel.setFont(new Font("Dialog", Font.PLAIN, 20));

        add(passwordLabel);

        //password field
        JPasswordField passwordField = new JPasswordField();

        passwordField.setBounds(20,260,getWidth() - 50, 40);

        passwordField.setFont(new Font("Dialog", Font.PLAIN , 28));

        add(passwordField);

        //retype password label
        JLabel rePasswordLabel = new JLabel("Re-type Password");

        rePasswordLabel.setBounds(20,320,getWidth() - 50, 40);

        rePasswordLabel.setFont(new Font("Dialog", Font.PLAIN , 20));

        add(rePasswordLabel);

        //re-type password field
        JPasswordField rePasswordField = new JPasswordField();

        rePasswordField.setBounds(20,360,getWidth() - 50, 40);

        rePasswordField.setFont(new Font("Dialog", Font.PLAIN , 28));

        add(rePasswordField);

        //register button
        JButton registerButton = new JButton("Register");
        registerButton.setBounds(20,460,getWidth()-50,40);
        registerButton.setFont(new Font("Dialog", Font.PLAIN, 20));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();

                String password = String.valueOf(passwordField.getPassword());

                String rePassword = String.valueOf(rePasswordField.getPassword());

                //validate the pass
                if(validateUserInput(username, password, rePassword)){
                    if(MyJDBC.register(username, password)){

                        RegisterGui.this.dispose();

                        LoginGui loginGui = new LoginGui();
                        loginGui.setVisible(true);

                        JOptionPane.showMessageDialog(loginGui,"Your account was successfully registered!.");
                    }else{
                        JOptionPane.showMessageDialog(RegisterGui.this,"Error! The username is already taken!");

                    }
                }
                else{
                    JOptionPane.showMessageDialog(RegisterGui.this,"Error! The username must be at least 6 charachters!" +
                                                                                        "\nThe password have to match both fields!");
                }
            }
        });

        add(registerButton);

        //login label
        JLabel loginLabel = new JLabel("<html><a href=\"#\">Already registered? Login here</a></html>");
        loginLabel.setBounds(0,510,getWidth()-10,30);
        loginLabel.setFont(new Font("Dialog", Font.PLAIN, 20));
        loginLabel.setHorizontalAlignment(SwingConstants.CENTER);
        loginLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        loginLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RegisterGui.this.dispose();
                new LoginGui().setVisible(true);
            }
        });

        add(loginLabel);

    }

    private boolean validateUserInput(String username, String password, String rePassword){

        if(username.isEmpty() || password.isEmpty() || rePassword.isEmpty()){
            return false;
        }

        if(username.length() < 6 || username.length() > 20){
            return false;
        }

        if(!password.equals(rePassword)){
            return false;
        }

        return true;
    }

}
