package controllers.interface;

public interface Icontrollers {
    String createAccount(String name, String surname, String);
    String findUserById(int id);
    String getListAccount();
}
