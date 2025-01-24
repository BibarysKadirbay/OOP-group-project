
package Repository;

import users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository  {


    public User FindStudentByBarcode(Connection connection, int barcode) {
        String sql = "SELECT * FROM users WHERE barcode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, barcode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("telegram_tag"),
                        rs.getBoolean("gender"),
                        rs.getFloat("gpa")
                );
            }
        }
        catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }

        return null;
    }




    public List<User> FindAllStudents(Connection connection)  {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM users";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("telegram_tag"),
                        rs.getBoolean("gender"),
                        rs.getFloat("gpa")
                ));
            }
        }

        catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
        return users;
    }

    public void UpdateStudentByBarcode (Connection connection, User user) {
        String query = "UPDATE users SET name = ?, surname = ?, telegram_tag = ?, gender = ?, gpa = ? WHERE barcode = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getTelegramTag());
            ps.setBoolean(4, user.isGender());
            ps.setFloat(5, user.getGpa());
            ps.setInt(6, user.getBarcode());
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("sql error:" + e.getMessage());
        }
    }




}
