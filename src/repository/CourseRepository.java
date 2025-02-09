package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Database.interfaces.IDB;
import models.Course;
import repository.interfaces.ICourseRepository;
public class CourseRepository implements ICourseRepository {
    private final IDB db;
    public CourseRepository(IDB db) {
        this.db = db;
    }

    public boolean addCourse(Course course) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String query = "INSERT INTO courses (name, instructor) VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query);{
                stmt.setString(1, course.getName());
                stmt.setString(2, course.getInstructor());
                stmt.executeUpdate();
                return stmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM courses";
        Connection connection = null;
        try {
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                courses.add(new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("instructor")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return courses;
    }

    public Course getCourseById(int id) {
        String query = "SELECT * FROM courses WHERE id = ?";
        Connection connection = null;
        try {
            connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Course(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("instructor")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public boolean updateCourse(Course course) {
        String query = "UPDATE courses SET name = ?, instructor = ? WHERE id = ?";
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, course.getName());
            stmt.setString(2, course.getInstructor());
            stmt.setInt(3, course.getId());

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public boolean deleteCourse(int id) {
        String query = "DELETE FROM courses WHERE id = ?";
        Connection connection = null;

        try {
            connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setInt(1, id);

            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}