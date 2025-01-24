package Repository;

import users.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {


    public User findUserByBarcode(Connection connection, int barcode) {
        String sql = "SELECT * FROM Groups WHERE Barcode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, barcode);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("Barcode"),
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("TG nickname"),
                        rs.getString("Group"),
                        rs.getString("City"),
                        rs.getBoolean("Gender"),
                        rs.getInt("Age")
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }




    public List<User> findAllStudents(Connection connection) {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM Groups";

        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("Barcode"),
                        rs.getString("Name"),
                        rs.getString("Surname"),
                        rs.getString("TG nickname"),
                        rs.getString("Group"),
                        rs.getString("City"),
                        rs.getBoolean("Gender"),
                        rs.getInt("Age")
                ));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return users;
    }




    public void updateStudentByBarcode(Connection connection, User user) {
        String sql = "UPDATE Groups SET Name = ?, Surname = ?, TG nickname = ?, Group = ?, City = ?, Gender = ?, Age = ? WHERE Barcode = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getTgNickname());
            ps.setString(4, user.getGroup());
            ps.setString(5, user.getCity());
            ps.setBoolean(6, user.isGender());
            ps.setInt(7, user.getAge());
            ps.setInt(8, user.getBarcode());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
//dsad