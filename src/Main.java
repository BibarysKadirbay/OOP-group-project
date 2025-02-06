/*import controllers.interfaces.IScheduleController;
import controllers.ScheduleController;
import models.ScheduleModule;
import repository.ScheduleRepository;*/
import controllers.UserController;
import controllers.interfaces.IUserController;
import data.DB;
import data.interfaces.IDB;
import repository.UserRepository;
import repository.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB idb = new DB("jdbc:postgresql://localhost:5431", "postgres", "0000", "postgres");
        IUserRepository userRepository = new UserRepository(idb);
        IUserController userController = new UserController(userRepository);
        Front project = new Front(userController);
        project.run();
        idb.close();
    }
}
//Schedule Main
/*public class Main {
    public static void main(String[] args) {
        // Initialize Repository, Module, and Controller
        ScheduleRepository scheduleRepository = new ScheduleRepository();
        ScheduleModule scheduleModule = new ScheduleModule(1, 101, "Monday", "10:00 AM");
        IScheduleController scheduleController = new ScheduleController(scheduleRepository);

        // Create a new schedule
        scheduleController.createSchedule(scheduleModule);

        // Get and print all schedules
        scheduleController.getAllSchedules().forEach(s ->
                System.out.println(s.getDay() + " " + s.getTime()));

        // Update a schedule
        scheduleModule.setTime("11:00 AM");
        scheduleController.updateSchedule(scheduleModule);

        // Get schedule by ID
        ScheduleModule schedule = scheduleController.getScheduleById(1);
        System.out.println(schedule.getDay() + " " + schedule.getTime());

        // Delete a schedule
        scheduleController.deleteSchedule(1);
    }
}*/
//Attendance Main
/*import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IAttendanceController controller = new AttendanceController();

        // Mark attendance for student with ID 1 for course 101 on 2025-02-04
        controller.markAttendance(1, 101, LocalDate.of(2025, 2, 4), true); // true = present

        // View attendance for student with ID 1
        List<Attendance> studentAttendance = controller.viewAttendanceByStudent(1);

        // View attendance for course with ID 101
        List<Attendance> courseAttendance = controller.viewAttendanceByCourse(101);
    }
}*/

