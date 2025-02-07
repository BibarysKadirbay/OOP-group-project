package repository;

import Database.interfaces.IDB;
import models.Schedule;
import repository.interfaces.IScheduleRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository implements IScheduleRepository {
    private final IDB db;

    public ScheduleRepository(IDB db) {
        this.db = db;
    }
    @Override
    public boolean createSchedule(Schedule schedule) {
        String sql = "INSERT INTO schedule (course_name, instructor, start_time, end_time, day_of_week) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection connection = db.getConnection();
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, schedule.getCourseName());
            st.setString(2, schedule.getInstructor());
            st.setTime(3, java.sql.Time.valueOf(schedule.getStartTime()));
            st.setTime(4, java.sql.Time.valueOf(schedule.getEndTime()));
            st.setString(5, schedule.getDayOfWeek().toLowerCase());

            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error in createSchedule: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Schedule> getSchedulesByInstructor(String instructor) {
        String sql = "SELECT * FROM schedule WHERE instructor = ?";
        List<Schedule> schedulesList = new ArrayList<>();

        try (Connection connection = db.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            st.setString(1, instructor);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                schedulesList.add(mapResultSetToSchedule(rs));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getSchedulesByInstructor: " + e.getMessage());
        }
        return schedulesList;
    }

    @Override
    public List<Schedule> getAllSchedules() {
        String sql = "SELECT id, course_name, instructor, start_time, end_time, day_of_week FROM schedule";
        List<Schedule> schedulesList = new ArrayList<>();

        try (Connection connection = db.getConnection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                schedulesList.add(mapResultSetToSchedule(rs));
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getAllSchedules: " + e.getMessage());
        }
        return schedulesList;
    }

    @Override
    public boolean deleteSchedule(int id) {
        String sql = "DELETE FROM schedule WHERE id = ?";
        try (Connection connection = db.getConnection();
             PreparedStatement st = connection.prepareStatement(sql)) {

            st.setInt(1, id);
            return st.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error in deleteSchedule: " + e.getMessage());
        }
        return false;
    }

    private Schedule mapResultSetToSchedule(ResultSet rs) throws SQLException {
        return new Schedule(
                rs.getInt("id"),
                rs.getString("course_name"),
                rs.getString("instructor"),
                rs.getString("start_time"),
                rs.getString("end_time"),
                rs.getString("day_of_week")
        );
    }
}