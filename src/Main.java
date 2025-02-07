import controllers.*;
import controllers.interfaces.*;
import Database.interfaces.IDB;
import Database.DB;
import repository.*;
import repository.interfaces.*;
import repository.interfaces.*;
import repository.*;
public class Main {
    public static void main(String[] args) {
        IDB idb = new DB("jdbc:postgresql://localhost:5431", "postgres", "0000", "postgres");
        IUserRepository userRepository = new UserRepository(idb);
        ICourseRepository courseRepository = new CourseRepository(idb);
        IScheduleRepository scheduleRepository = new ScheduleRepository(idb);
        iGradesRepository gradeRepository = new GradesRepository(idb);
        AttendanceRepository attendanceRepository = new AttendanceRepository(idb);
        IUserController userController = new UserController(userRepository);
        ICourseController courseController = new CourseController(courseRepository);
        IScheduleController scheduleController = new ScheduleController(scheduleRepository);
        iGradesController gradeController = new GradesController(gradeRepository);
        IAttendanceController attendanceController = new AttendanceController(attendanceRepository);
        Front project = new Front(userController, courseController, scheduleController , gradeController , attendanceController);
        project.run();
        idb.close();
    }
}
