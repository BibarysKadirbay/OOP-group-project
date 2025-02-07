package controllers.interfaces;


import models.Role;

public interface iGradesController {
    String createGrade(int studentBarcode, int courseId, int percentage);
    String getGradesByStudentBarcode(int studentBarcode);
    String calculateGPA(int studentBarcode);
    String getAllGrades(Role userRole);
    String getStudentGradesWithCourses(int studentBarcode);
}
