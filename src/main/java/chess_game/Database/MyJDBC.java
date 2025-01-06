package Chess_Business.Database;

import java.sql.*;

public class MyJDBC {
    //configuratia bazei de date
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    //daca conexiunea e valida returneaza un obiect cu date despre utilizator
    public static User validateLogic(String username, String password){
        try{
            Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            //create sql query
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE username = ? AND password = ?"
            );

            //inlocuim ? cu valori
            preparedStatement.setString(1,username);
            preparedStatement.setString(2,password);

            ResultSet resultSet = preparedStatement.executeQuery();

            //next() -> true(returneaza date si rezulttate), false null
            if (resultSet.next()){
                //succes
                int userId = resultSet.getInt("id");

                //balanta curenta
                //BigDecimal currentBalance = resultSet.getBigDecimal("current_balance");

                return new User(userId, username, password);
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    //true = register succesfully / false = registeration failed
    public static boolean register(String username, String password){
        try{
            //prima oara trebuie sa verificam daca userul exista deja sau nu in BD
            if (!checkUser(username)){
                Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

                PreparedStatement preparedStatement = conn.prepareStatement(
                        "INSERT INTO users(username, password) " + "VALUES(?,?)"
                );

                preparedStatement.setString(1,username);
                preparedStatement.setString(2,password);

                preparedStatement.executeUpdate();

                return true;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

        return false;
    }

    //true = user exists, false  = user doesnt exist
    private static boolean checkUser(String username){
        try{
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "SELECT * FROM users WHERE username = ?"
            );

            preparedStatement.setString(1,username);
            ResultSet resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()){
                return false;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return true;
    }

}
