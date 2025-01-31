package repository;

import Database.interfaces.IDB;
import models.User;
import repository.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addUser(User user){
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="INSERT INTO groups(barcode, name, surname, tg_nickname, group_name, city, gender , age , gpa) VALUES (?, ?, ?, ?, ?, ?, ? , ? , ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, user.getBarcode());
            st.setString(2, user.getName());
            st.setString(3, user.getSurname());
            st.setString(4, user.getTg_name());
            st.setString(5, user.getGroup());
            st.setString(6, user.getCity());
            st.setBoolean(7, user.isGender());
            st.setInt(8, user.getAge());
            st.setDouble(9 , user.getGPA());
            st.execute();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User getInformationByBarcode(int barcode) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM groups WHERE barcode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, barcode);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new User(
                        rs.getInt("id"),
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("tg_nickname"),
                        rs.getString("group_name"),
                        rs.getString("city"),
                        rs.getBoolean("gender"),
                        rs.getInt("age"),
                        rs.getDouble("gpa"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<User> showAllUsers() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM groups";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> allUsers = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("id"),
                        rs.getInt("barcode"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("tg_nickname"),
                        rs.getString("group_name"),
                        rs.getString("city"),
                        rs.getBoolean("gender"),
                        rs.getInt("age"),
                        rs.getDouble("gpa"));

                allUsers.add(user);
            }

            return allUsers;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUser(int barcode) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="DELETE FROM groups WHERE barcode = ?";PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, barcode);
            int rs = st.executeUpdate();
            if (rs>0){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public double getGPA(int barcode) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT gpa FROM groups WHERE barcode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1,barcode);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return rs.getDouble("gpa");
            }
        }catch (SQLException e){
            System.out.println("Error getting GPA: " + e.getMessage());
        }
        return -1;
    }

    @Override
    public boolean setGPA(int barcode, double gpa) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="UPDATE groups SET gpa = ? WHERE barcode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDouble(1, gpa);
            st.setInt(2, barcode);
            int rs = st.executeUpdate();
            if (rs>0){
                return true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}


