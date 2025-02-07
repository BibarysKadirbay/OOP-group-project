package controllers;
import controllers.interfaces.IAttendanceController;
import models.Attendance;
import repository.AttendanceRepository;
import repository.interfaces.IAttendanceRepository;
import repository.interfaces.iGradesRepository;

import java.time.LocalDate;
import java.util.List;

public class AttendanceController implements IAttendanceController {
    private final IAttendanceRepository repo;
    public AttendanceController(IAttendanceRepository repo) {
        this.repo = repo;
    }

    @Override
    public void markAttendance(int studentId, int courseId, LocalDate date, boolean status) {
        Attendance attendance = new Attendance(studentId, courseId, date, status);
        boolean success = repo.insertAttendance(attendance);
        if (success) {
            System.out.println("Attendance marked successfully.");
        } else {
            System.out.println("Failed to mark attendance.");
        }
    }
    @Override
    public List<Attendance> viewAttendanceByStudent(int studentId) {
        List<Attendance> attendanceList = repo.getAttendanceByStudentId(studentId);
        if (attendanceList.isEmpty()) {
            System.out.println("No attendance records found for this student.");
        } else {
            attendanceList.forEach(System.out::println);
        }
        return attendanceList;
    }
    @Override
    public List<Attendance> viewAttendanceByCourse(int courseId) {
        List<Attendance> attendanceList = repo.getAttendanceByCourseId(courseId);
        if (attendanceList.isEmpty()) {
            System.out.println("No attendance records found for this course.");
        } else {
            attendanceList.forEach(System.out::println);
        }
        return attendanceList;
    }
}