package controllers.interfaces;

import models.User;

public interface IUserController {
    String addUser(User user);
    String showAllUsers();
    String getInformation(int barcode);
    String deleteUser(int barcode);
}