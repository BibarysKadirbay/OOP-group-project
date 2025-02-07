package controllers;

import controllers.interfaces.IScheduleController;
import models.Schedule;
import repository.interfaces.IScheduleRepository;

import java.util.List;

public class ScheduleController implements IScheduleController {
    private final IScheduleRepository scheduleRepository;

    public ScheduleController(IScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public String addSchedule(Schedule schedule) {
        boolean created = scheduleRepository.createSchedule(schedule);
        return created ? "Schedule was created successfully\n" : "Schedule creation failed\n";
    }

    @Override
    public String getSchedulesByInstructor(String instructor) {
        List<Schedule> schedules = scheduleRepository.getSchedulesByInstructor(instructor);
        if (schedules.isEmpty()) {
            return "No schedules found for instructor: " + instructor + "\n";
        }
        StringBuilder response = new StringBuilder();
        for (Schedule schedule : schedules) {
            response.append(schedule.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String showAllSchedules() {
        List<Schedule> schedules = scheduleRepository.getAllSchedules();
        if (schedules.isEmpty()) {
            return "No schedules found\n";
        }
        StringBuilder response = new StringBuilder();
        for (Schedule schedule : schedules) {
            response.append(schedule.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String deleteSchedule(int scheduleId) {
        boolean deleted = scheduleRepository.deleteSchedule(scheduleId);
        return deleted ? "Schedule deleted successfully\n" : "Schedule deletion failed\n";
    }
}