package controllers.interface;

public interface Icontrollers {
    String createAccount(String name, String surname, String TelegramTag);
    String findUserById(int id);
    String getListAccount();
    String registerUserWithDetails(int barcode, String name, String surname, String tgNickname, String group, String city, String gender, int age);
}
