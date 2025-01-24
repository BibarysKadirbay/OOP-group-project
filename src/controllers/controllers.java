package controllers;

import Repository.UserRepository;
//import controllers.interface,Icontrollers;
import Database.connection;
import users.User;

import java.sql.Connection;
import java.util.List;

public class Controllers implements Icontrollers {

    private final UserRepository userRepository;

    public Controllers() {
        this.userRepository = new UserRepository();
    }

    @Override
    public String createAccount(String name, String surname, String telegramTag) {
        try (Connection conn = connection.getConnection()) {
            User user = new User(0, name, surname, telegramTag, true, 0.0f);
            userRepository.UpdateStudentByBarcode(conn, user);
            return "Account successfully created.";
        } catch (Exception e) {
            return "Failed to create account: " + e.getMessage();
        }
    }

    @Override
    public String findUserById(int id) {
        try (Connection conn = connection.getConnection()) {
            User user = userRepository.FindStudentByBarcode(conn, id);
            if (user != null) {
                return user.toString();
            }
            return "No user found with the provided ID.";
        } catch (Exception e) {
            return "Error while fetching user: " + e.getMessage();
        }
    }

    @Override
    public String getListAccount() {
        try (Connection conn = connection.getConnection()) {
            List<User> userList = userRepository.FindAllStudents(conn);
            StringBuilder response = new StringBuilder();
            for (User user : userList) {
                response.append(user.toString()).append("\n");
            }
            return response.toString();
        } catch (Exception e) {
            return "Error retrieving account list: " + e.getMessage();
        }
    }

    public String registerUserWithDetails(int barcode, String name, String surname, String tgNickname, String group, String city, String gender, int age) {
        boolean male = gender.equalsIgnoreCase("male");
        try (Connection conn = connection.getConnection()) {
            User newUser = new User(barcode, name, surname, tgNickname, male, 0.0f, group, city, age);
            userRepository.UpdateStudentByBarcode(conn, newUser);
            return "User registered successfully with detailed information.";
        } catch (Exception e) {
            return "Error registering user: " + e.getMessage();
        }
    }
}



