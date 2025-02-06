package controllers;

import controllers.interfaces.iGradesController;
import models.grades;
import repository.interfaces.iGradesRepository;

import java.util.List;

public class GradesController implements iGradesController {
    private final iGradesRepository repo;

    public GradesController(iGradesRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createGrade(int studentId, int courseId, int percentage) {
        if (percentage < 0 || percentage > 100) {
            return "Invalid percentage value (must be 0-100)";
        }

        grades grade = new grades(0, studentId, courseId, percentage);  // Assuming 0 as gradeId as it's auto-generated
        boolean created = repo.createGrade(grade);
        return created ? "Grade was created" : "Grade creation failed";
    }

    @Override
    public String getGradesByStudentId(int studentId) {
        List<grades> gradesList = repo.getGradesByStudentId(studentId);
        if (gradesList.isEmpty()) return "No grades found for this student";

        StringBuilder response = new StringBuilder();
        for (grades grade : gradesList) {
            response.append(grade.toString()).append("\n");
        }
        return response.toString();
    }

    public double convertGPA(int percentage) {
        if (percentage >= 95) return 4.0;
        else if (percentage >= 90) return 3.67;
        else if (percentage >= 85) return 3.33;
        else if (percentage >= 80) return 3.0;
        else if (percentage >= 75) return 2.67;
        else if (percentage >= 70) return 2.33;
        else if (percentage >= 65) return 2.0;
        else if (percentage >= 60) return 1.67;
        else if (percentage >= 55) return 1.33;
        else if (percentage >= 50) return 1.0;
        else return 0.0;
    }

    @Override
    public String calculateGPA(int studentId) {
        List<grades> gradesList = repo.getGradesByStudentId(studentId);
        if (gradesList.isEmpty()) return "No grades available for GPA calculation";

        double totalGPA = 0;
        int gradeCount = 0;
        for (grades grade : gradesList) {
            totalGPA += convertGPA(grade.getPercentage());
            gradeCount++;
        }
        double gpa = totalGPA / gradeCount;
        return String.format("GPA for student %d: %.2f", studentId, gpa);
    }

    @Override
    public String getAllGrades() {
        List<grades> gradesList = repo.getAllGrades();
        if (gradesList.isEmpty()) return "No grades found in system";

        StringBuilder response = new StringBuilder();
        for (grades grade : gradesList) {
            response.append(grade.toString()).append("\n");
        }
        return response.toString();
    }
}
