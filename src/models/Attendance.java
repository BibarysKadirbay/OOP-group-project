package models;

import java.time.LocalDate;

public class Attendance {
    private int attendanceId;
    private int studentId;
    private int courseId;
    private LocalDate date;
    private boolean status;
    public Attendance(int attendanceId, int studentId, int courseId, LocalDate date, boolean status) {
        this.attendanceId = attendanceId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = status;
    }
    public Attendance(int studentId, int courseId, LocalDate date, boolean status) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.date = date;
        this.status = status;
    }
    public int getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId = attendanceId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Attendance [attendanceId=" + attendanceId + ", studentId=" + studentId + ", courseId=" + courseId
                + ", date=" + date + ", status=" + status + "]";
    }
}