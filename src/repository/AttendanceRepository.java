package repository;

import Database.interfaces.IDB;
import models.Attendance;
import repository.interfaces.IAttendanceRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceRepository implements IAttendanceRepository {
    private final IDB db;

    public AttendanceRepository(IDB db) {
        this.db = db;
    }
    public boolean insertAttendance(Attendance attendance) {
        Connection connection = null;
        String query = "INSERT INTO attendance (student_id, course_id, date, status) VALUES (?, ?, ?, ?)";
        try{
            connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
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
    public List<Attendance> getAttendanceByStudentId(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        Connection connection = null;
        String query = "SELECT * FROM attendance WHERE student_id = ?";
        try{
            connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
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
    public List<Attendance> getAttendanceByCourseId(int courseId) {
        Connection connection = null;
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE course_id = ?";
        try{
            connection = db.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
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