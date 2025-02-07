package controllers.interfaces;

import models.Schedule;

public interface IScheduleController {
    String addSchedule(Schedule schedule);
    String getSchedulesByInstructor(String instructor);
    String showAllSchedules();
    String deleteSchedule(int scheduleId);
}