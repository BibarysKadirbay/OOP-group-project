package controllers.interfaces;

import models.Attendance;

import java.time.LocalDate;
import java.util.List;

public interface IAttendanceController {
    void markAttendance(int studentId, int courseId, LocalDate date, boolean status);
    List<Attendance> viewAttendanceByStudent(int studentId);
    List<Attendance> viewAttendanceByCourse(int courseId);
}
