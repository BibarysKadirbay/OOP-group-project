package Repository;

import Repository.interfaces.IAttendanceRepository;
import models.Attendance;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository implements IAttendanceRepository {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/your_database_name";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";

    // Get a connection to the PostgreSQL database
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Insert attendance record
    public boolean insertAttendance(Attendance attendance) {
        String query = "INSERT INTO attendance (student_id, course_id, date, status) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, attendance.getStudentId());
            stmt.setInt(2, attendance.getCourseId());
            stmt.setDate(3, Date.valueOf(attendance.getDate()));
            stmt.setBoolean(4, attendance.isStatus());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get attendance by student ID
    public List<Attendance> getAttendanceByStudentId(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE student_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int attendanceId = rs.getInt("attendance_id");
                int courseId = rs.getInt("course_id");
                LocalDate date = rs.getDate("date").toLocalDate();
                boolean status = rs.getBoolean("status");
                Attendance attendance = new Attendance(attendanceId, studentId, courseId, date, status);
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }

    // Get attendance by course ID
    public List<Attendance> getAttendanceByCourseId(int courseId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE course_id = ?";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, courseId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int attendanceId = rs.getInt("attendance_id");
                int studentId = rs.getInt("student_id");
                LocalDate date = rs.getDate("date").toLocalDate();
                boolean status = rs.getBoolean("status");
                Attendance attendance = new Attendance(attendanceId, studentId, courseId, date, status);
                attendanceList.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendanceList;
    }
}

