package repository.interfaces;

import models.grades;

import java.util.List;

public interface iGradesRepository {
    boolean createGrade(grades grade);

    List<grades> getGradesByStudentId(int studentId);

    List<grades> getAllGrades();
}
