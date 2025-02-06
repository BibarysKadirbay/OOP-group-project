package controllers.interfaces;

public interface iGradesController {
    String createGrade(int studentId, int courseId, int percentage);
    String getGradesByStudentId(int studentId);
    String calculateGPA(int studentId);
    String getAllGrades();
}
