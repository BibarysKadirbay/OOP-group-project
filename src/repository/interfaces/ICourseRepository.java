package repository.interfaces;
import java.util.List;
import models.Course;
public interface ICourseRepository {
    boolean addCourse(Course course);
    List<Course> getAllCourses();
    Course getCourseById(int id);
    boolean updateCourse(Course course);
    boolean deleteCourse(int id);
}