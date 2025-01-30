package repository.interfaces;

import models.User;

import java.util.List;

public interface IUserRepository {
    boolean addUser(User user);
    User getInformationByBarcode(int barcode);
    List<User> showAllUsers();
    boolean deleteUser(int barcode);
    double getGPA(int barcode);
    boolean setGPA(int barcode, double gpa);
}