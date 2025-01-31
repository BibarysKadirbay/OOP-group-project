package controllers;


import controllers.interfaces.IUserController;
import models.User;
import repository.interfaces.IUserRepository;

import java.sql.SQLOutput;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public String addUser(User user)  {
        boolean created = userRepository.addUser(user);
        return (created) ? "User was created\n" : "User creation was failed\n";
    }

    @Override
    public String getInformation(int barcode) {
        User user = userRepository.getInformationByBarcode(barcode);
        return (user == null) ? "User was not found\n" : user.toString();
    }

    @Override
    public String showAllUsers() {
        List<User> users = userRepository.showAllUsers();
        StringBuilder responce = new StringBuilder();
        for (User user : users) {
            responce.append(user.toString()).append("\n");
        }
        return responce.toString();
    }

    @Override
    public String deleteUser(int barcode) {
        boolean deleted = userRepository.deleteUser(barcode);
        return (deleted == false) ? "User deletion failed\n" : "User was successfully deleted\n";
    }
    public double  getGPA(int barcode){
        try{
            return userRepository.getGPA(barcode);
        }catch (Exception e){
            System.out.println("Error getting GPA: " + e.getMessage());
            return -1;
        }
    }
    public String setGPA(int barcode, double gpa) {
        try {
            if (userRepository.setGPA(barcode, gpa)) {
                return "GPA was updated successfully\n";
            }else{
                return "User with this barcode was not found\n";
            }
        }catch (Exception e){
            return "Error setting GPA: " + e.getMessage() + '\n';
        }
    }
}