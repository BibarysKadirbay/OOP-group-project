package controllers;

import controllers.interfaces.IScheduleController;
import models.ScheduleModule;
import repository.interfaces.IScheduleRepository;
import java.util.List;

public class ScheduleController implements IScheduleController {
    private IScheduleRepository scheduleRepository;

    public ScheduleController(IScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public ScheduleModule getScheduleById(int scheduleId) {
        return scheduleRepository.getById(scheduleId);
    }

    @Override
    public List<ScheduleModule> getAllSchedules() {
        return scheduleRepository.getAllSchedules();
    }

    @Override
    public void createSchedule(ScheduleModule schedule) {
        scheduleRepository.save(schedule);
    }

    @Override
    public void updateSchedule(ScheduleModule schedule) {
        scheduleRepository.update(schedule);
    }

    @Override
    public void deleteSchedule(int scheduleId) {
        scheduleRepository.delete(scheduleId);
    }
}
