/*import controllers.interfaces.IScheduleController;
import controllers.ScheduleController;
import models.ScheduleModule;
import repository.ScheduleRepository;*/
import controllers.UserController;
import controllers.interfaces.IUserController;
import Database.DB;
import Database.interfaces.IDB;
import repository.UserRepository;
import repository.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB idb = new DB("jdbc:postgresql://locadlhost:5431", "postgres", "0000", "postgres");
        IUserRepository userRepository = new UserRepository(idb);
        IUserController userController = new UserController(userRepository);
        Front project = new Front(userController);
        project.run();
        idb.close();
    }
}
