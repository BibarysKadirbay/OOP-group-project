package repository;

import models.ScheduleModule;
import repository.interfaces.IScheduleRepository;

import java.util.ArrayList;
import java.util.List;

public class ScheduleRepository implements IScheduleRepository {
    private List<ScheduleModule> schedules = new ArrayList<>();

    @Override
    public ScheduleModule getById(int scheduleId) {
        return schedules.stream()
                .filter(s -> s.getScheduleId() == scheduleId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<ScheduleModule> getAllSchedules() {
        return schedules;
    }

    @Override
    public void save(ScheduleModule schedule) {
        schedules.add(schedule);
    }

    @Override
    public void update(ScheduleModule schedule) {
        ScheduleModule existingSchedule = getById(schedule.getScheduleId());
        if (existingSchedule != null) {
            existingSchedule.setCourseId(schedule.getCourseId());
            existingSchedule.setDay(schedule.getDay());
            existingSchedule.setTime(schedule.getTime());
        }
    }

    @Override
    public void delete(int scheduleId) {
        schedules.removeIf(s -> s.getScheduleId() == scheduleId);
    }
}
