package models.interfaces;

public interface IScheduleModule {
    int getScheduleId();
    void setScheduleId(int scheduleId);

    int getCourseId();
    void setCourseId(int courseId);

    String getDay();
    void setDay(String day);

    String getTime();
    void setTime(String time);
}
