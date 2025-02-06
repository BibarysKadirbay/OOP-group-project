package controllers.interfaces;

import models.ScheduleModule;
import java.util.List;

public interface IScheduleController {
    ScheduleModule getScheduleById(int scheduleId);
    List<ScheduleModule> getAllSchedules();
    void createSchedule(ScheduleModule schedule);
    void updateSchedule(ScheduleModule schedule);
    void deleteSchedule(int scheduleId);
}
