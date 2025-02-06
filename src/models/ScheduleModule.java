package models;

public class ScheduleModule {
    private int scheduleId;
    private int courseId;
    private String day;
    private String time;

    // Constructor
    public ScheduleModule(int scheduleId, int courseId, String day, String time) {
        this.scheduleId = scheduleId;
        this.courseId = courseId;
        this.day = day;
        this.time = time;
    }

    // Getters and Setters
    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
