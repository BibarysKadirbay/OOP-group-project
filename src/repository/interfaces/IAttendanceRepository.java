package Repository.interfaces;

import models.Attendance;

import java.util.List;

public interface IAttendanceRepository {
    // Method to insert attendance record
    boolean insertAttendance(Attendance attendance);

    // Method to get attendance records for a specific student
    List<Attendance> getAttendanceByStudentId(int studentId);

    // Method to get attendance records for a specific course
    List<Attendance> getAttendanceByCourseId(int courseId);
}
