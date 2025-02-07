package models;

public class Schedule {
    private int id;
    private String courseName;
    private String instructor;
    private String startTime;
    private String endTime;
    private String dayOfWeek;

    public Schedule(int id, String courseName, String instructor, String startTime, String endTime, String dayOfWeek) {
        this.id = id;
        this.courseName = courseName;
        this.instructor = instructor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeek = dayOfWeek;
    }

    public int getId() { return id; }
    public String getCourseName() { return courseName; }
    public String getInstructor() { return instructor; }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
    public String getDayOfWeek() { return dayOfWeek; }

    @Override
    public String toString() {
        return "Schedule [ID: " + id + ", Course: " + courseName + ", Instructor: " + instructor +
                ", Start Time: " + startTime + ", End Time: " + endTime + ", Day: " + dayOfWeek + "]";
    }
}