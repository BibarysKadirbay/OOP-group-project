package controllers.interfaces;

import models.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface IAttendanceController {
    // Method to mark attendance for a student in a course
    void markAttendance(int studentId, int courseId, LocalDate date, boolean status);

    // Method to view attendance records for a student
    List<Attendance> viewAttendanceByStudent(int studentId);

    // Method to view attendance records for a course
    List<Attendance> viewAttendanceByCourse(int courseId);
}
