package repository.interfaces;

import models.Attendance;

import java.util.List;

public interface IAttendanceRepository {
    boolean insertAttendance(Attendance attendance);
    List<Attendance> getAttendanceByStudentId(int studentId);
    List<Attendance> getAttendanceByCourseId(int courseId);
}
