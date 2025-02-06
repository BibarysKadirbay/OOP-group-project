package models;

public class grades {
    private int gradeId;
    private int studentId;
    private int courseId;
    private int percentage;

    public grades() {
    }

    public grades(int gradeId, int studentId, int courseId, int percentage) {
        this.gradeId = gradeId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.percentage = percentage;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public String toString() {
        return "grades{" +
                "gradeId=" + gradeId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", percentage=" + percentage +
                '}';
    }
}
