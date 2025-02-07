package controllers;


import controllers.interfaces.iGradesController;
import dostup.RoleManager;
import models.Role;
import models.grades;
import repository.interfaces.iGradesRepository;

import java.util.List;
import java.util.stream.Collectors;

public class GradesController implements iGradesController {
    private final iGradesRepository repo;
    public GradesController(iGradesRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createGrade(int studentBarcode, int courseId, int percentage) {
        if (percentage < 0 || percentage > 100) {
            return "Invalid percentage value (must be 0-100)";
        }

        grades grade = new grades(0, studentBarcode, courseId, percentage);  // Assuming 0 as gradeId as it's auto-generated
        boolean created = repo.createGrade(grade);
        return created ? "Grade was created" : "Grade creation failed";
    }

    @Override
    public String getGradesByStudentBarcode(int studentBarcode) {
        List<grades> gradesList = repo.getGradesByStudentBarcode(studentBarcode);
        if (gradesList.isEmpty()) return "No grades found for this student";

        StringBuilder response = new StringBuilder();
        for (grades grade : gradesList) {
            response.append(grade.toString()).append("\n");
        }
        return response.toString();
    }
    @Override
    public String getStudentGradesWithCourses(int studentBarcode) {
        List<String> studentGrades = repo.getStudentGradesWithCourses(studentBarcode);

        return studentGrades.isEmpty()
                ? "No grades found for this student."
                : studentGrades.stream()
                .collect(Collectors.joining("\n"));
    }
    private double convertGPA(int percentage) {
        return switch (percentage / 5) {
            case 20, 19 -> 4.0;
            case 18 -> 3.67;
            case 17 -> 3.33;
            case 16 -> 3.0;
            case 15 -> 2.67;
            case 14 -> 2.33;
            case 13 -> 2.0;
            case 12 -> 1.67;
            case 11 -> 1.33;
            case 10 -> 1.0;
            default -> 0.0;
        };
    }

    @Override
    public String calculateGPA(int studentBarcode) {
        List<grades> gradesList = repo.getGradesByStudentBarcode(studentBarcode);
        if (gradesList.isEmpty()) return "No grades available for GPA calculation";

        double totalGPA = 0;
        int gradeCount = 0;
        for (grades grade : gradesList) {
            totalGPA += convertGPA(grade.getPercentage());
            gradeCount++;
        }
        double gpa = totalGPA / gradeCount;
        return String.format("GPA for student %d: %.2f", studentBarcode, gpa);
    }

    @Override
    public String getAllGrades(Role userRole) {
        if (!RoleManager.hasAccess(userRole, "VIEW_GRADES")) {
            return "Access Denied! You don't have permission to view all grades.";
        }
        List<grades> gradesList = repo.getAllGrades(userRole);
        if (gradesList.isEmpty()) return "No grades found in system";

        StringBuilder response = new StringBuilder();
        for (grades grade : gradesList) {
            response.append(grade.toString()).append("\n");
        }
        return response.toString();
    }
}