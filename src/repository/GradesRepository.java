package repository;

import Database.interfaces.IDB;
import dostup.RoleManager;
import models.Role;
import models.grades;
import repository.interfaces.iGradesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesRepository implements iGradesRepository{
    private final IDB db;

    public GradesRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createGrade(grades grade) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO grades(student_barcode, course_id, percentage) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, grade.getStudentId());
            st.setInt(2, grade.getCourseId());
            st.setInt(3, grade.getPercentage());
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }


    @Override
    public List<grades> getGradesByStudentBarcode(int studentBarcode) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM grades WHERE student_barcode = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, studentBarcode);

            ResultSet rs = st.executeQuery();
            List<grades> gradesList = new ArrayList<>();
            while (rs.next()) {
                gradesList.add(new grades(
                        rs.getInt("grade_id"),
                        rs.getInt("student_barcode"),
                        rs.getInt("course_id"),
                        rs.getInt("percentage")
                ));
            }
            return gradesList;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<grades> getAllGrades(Role userRole) {
        if (!RoleManager.hasAccess(userRole, "VIEW_GRADES")) {
            System.out.println("Access Denied! Only Admins and Managers can view grades.");
            return null;
        }
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT grade_id, student_barcode, course_id, percentage FROM grades";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<grades> gradesList = new ArrayList<>();
            while (rs.next()) {
                gradesList.add(new grades(
                        rs.getInt("grade_id"),
                        rs.getInt("student_barcode"),
                        rs.getInt("course_id"),
                        rs.getInt("percentage")
                ));
            }
            return gradesList;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
    public List<String> getStudentGradesWithCourses(int studentId) {
        List<String> gradesList = new ArrayList<>();
        String sql = "SELECT g.grade_id, g.percentage, c.name, g.course_id " +
                "FROM grades g " +
                "JOIN courses c ON g.course_id = c.id " +
                "WHERE g.student_barcode = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, studentId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                grades grade = new grades(
                        rs.getInt("grade_id"),
                        studentId,
                        rs.getInt("course_id"),
                        rs.getInt("percentage")
                );

                gradesList.add("Course: " + rs.getString("name") + " | Grade: " + grade.getPercentage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (gradesList.isEmpty()) {
            gradesList.add("No grades found for this student.");
        }

        return gradesList;
    }
}
