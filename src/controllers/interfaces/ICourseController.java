package controllers.interfaces;

import models.Course;

public interface ICourseController {
    String addCourse(Course course);
    String getCourseById(int courseId);
    String showAllCourses();
    String updateCourse(Course course);
    String deleteCourse(int courseId);
}