package repository;

import database.interfaces.IDB;
import models.grades;
import repository.interfaces.iGradesRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GradesRepository implements iGradesRepository {
    private final IDB db;

    public GradesRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createGrade(grades grade) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO grades(grade_id, student_id, course_id, percentage) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, grade.getGradeId());
            st.setInt(2, grade.getStudentId());
            st.setInt(3, grade.getCourseId());
            st.setInt(4, grade.getPercentage());

            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<grades> getGradesByStudentId(int studentId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM grades WHERE student_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, studentId);

            ResultSet rs = st.executeQuery();
            List<grades> gradesList = new ArrayList<>();
            while (rs.next()) {
                gradesList.add(new grades(
                        rs.getInt("grade_id"),
                        rs.getInt("student_id"),
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
    public List<grades> getAllGrades() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT grade_id, student_id, course_id, percentage FROM grades";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<grades> gradesList = new ArrayList<>();
            while (rs.next()) {
                gradesList.add(new grades(
                        rs.getInt("grade_id"),
                        rs.getInt("student_id"),
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
}
