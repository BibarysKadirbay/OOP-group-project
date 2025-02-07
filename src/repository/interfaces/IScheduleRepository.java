package repository.interfaces;

import models.Schedule;
import java.util.List;

public interface IScheduleRepository {
    boolean createSchedule(Schedule schedule);
    List<Schedule> getAllSchedules();
    List<Schedule> getSchedulesByInstructor(String instructor);
    boolean deleteSchedule(int id);
}