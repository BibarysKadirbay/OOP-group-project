package Repository;

import users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository  {





    public UserRepository() {


    }






    public  boolean createUser(User user) {
        Connection connection = null;
        try {
            connection = database.getConnection();
            String sql = "INSERT INTO users (name, surname, gender, gpa, telegramtag) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getName());
            st.setString(2, user.getSurname());
            st.setBoolean(3, user.getGender());
            st.setFloat(4, user.getGpa());
            st.setString(2, user.getTelegramtag());
            st.execute();
            return true;
        }

        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }






    @Override
    public User getUserByBarcode (int barcode){
        Connection connection = null;

        try{
            connection = database.connection;
            String sql = "SELECT * FROM users WHERE barcode = ?";

            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, barcode);
            ResultSet rs = st.executeQuery();

            if(rs.next()){
                return new User(
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getFloat("gpa"),
                        rs.getString("telegramtag"));

            }

        }
        catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;

    }







    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try{
            connection = database.getConnection();
            String sql ="SELECT  name, surname, gender, gpa, telegramtag FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while(rs.next()){
                User user = new User(
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getBoolean("gender"),
                        rs.getFloat("gpa"),
                        rs.getString("telegramtag"));
                users.add(user);
            }
            return users;
        }catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }
}











}
