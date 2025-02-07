package controllers;

import controllers.interfaces.ICourseController;
import models.Course;
import repository.interfaces.ICourseRepository;

import java.util.List;

public class CourseController implements ICourseController {
    private final ICourseRepository courseRepository;

    public CourseController(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public String addCourse(Course course) {
        boolean created = courseRepository.addCourse(course);
        return created ? "Course was created successfully\n" : "Course creation failed\n";
    }

    @Override
    public String getCourseById(int courseId) {
        Course course = courseRepository.getCourseById(courseId);
        return (course == null) ? "Course not found\n" : course.toString();
    }

    @Override
    public String showAllCourses() {
        List<Course> courses = courseRepository.getAllCourses();
        return (courses.isEmpty()) ? "No courses found\n" : courses.toString();
    }

    @Override
    public String updateCourse(Course course) {
        boolean updated = courseRepository.updateCourse(course);
        return updated ? "Course updated successfully\n" : "Course update failed\n";
    }

    @Override
    public String deleteCourse(int courseId) {
        boolean deleted = courseRepository.deleteCourse(courseId);
        return deleted ? "Course deleted successfully\n" : "Course deletion failed\n";
    }
}