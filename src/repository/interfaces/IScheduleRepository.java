package repository.interfaces;

import models.ScheduleModule;
import java.util.List;

public interface IScheduleRepository {
    ScheduleModule getById(int scheduleId);
    List<ScheduleModule> getAllSchedules();
    void save(ScheduleModule schedule);
    void update(ScheduleModule schedule);
    void delete(int scheduleId);
}
