package controllers;

import java.util.List;
import java.util.Scanner;
import repository.CourseRepository;
import models.Course;
import Database.DB;

public class CourseController {
    private final CourseRepository repository;
    private final Scanner scanner = new Scanner(System.in);

    public CourseController() {
        DB db = new DB("host", "username", "password", "dbName");
        this.repository = new CourseRepository(db);
    }

    public void run() {
        while (true) {
            System.out.println("\nCourse Management System:");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Find Course by ID");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> addCourse();
                case 2 -> viewAllCourses();
                case 3 -> findCourseById();
                case 4 -> updateCourse();
                case 5 -> deleteCourse();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void addCourse() {
        System.out.print("Enter Course ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Course Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter Instructor Name: ");
        String instructor = scanner.nextLine();

        repository.addCourse(new Course(id, name, description, instructor));
        System.out.println("Course added successfully.");
    }

    private void viewAllCourses() {
        List<Course> courses = repository.getAllCourses();
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private void findCourseById() {
        System.out.print("Enter Course ID: ");
        int id = scanner.nextInt();
        Course course = repository.getCourseById(id);
        if (course != null) {
            System.out.println(course);
        } else {
            System.out.println("Course not found.");
        }
    }

    private void updateCourse() {
        System.out.print("Enter Course ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter New Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter New Description: ");
        String description = scanner.nextLine();
        System.out.print("Enter New Instructor: ");
        String instructor = scanner.nextLine();

        repository.updateCourse(new Course(id, name, description, instructor));
        System.out.println("Course updated successfully.");
    }

    private void deleteCourse() {
        System.out.print("Enter Course ID to delete: ");
        int id = scanner.nextInt();
        repository.deleteCourse(id);
        System.out.println("Course deleted successfully.");
    }

    public static void main(String[] args) {
        new CourseController().run();
    }
}