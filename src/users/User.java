package models;

public class User {
    private int id ;
    private int barcode;
    private String name;
    private String surname;
    private String tg_name;
    private String group;
    private String city;
    private boolean gender;
    private int age;
    private double gpa
    public User(){}
    public User(int id , int barcode, String name, String surname, String tg_name, String group, String city, boolean gender , int age, double gpa) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.surname = surname;
        this.tg_name = tg_name;
        this.group = group;
        this.city = city;
        this.gender = gender;
        this.age = age;
        this.gpa = gpa;
    }
    public User(int barcode, String name, String surname, String tg_name, String group, String city, boolean gender , int age, double gpa) {
        this.id = id;
        this.barcode = barcode;
        this.name = name;
        this.surname = surname;
        this.tg_name = tg_name;
        this.group = group;
        this.city = city;
        this.gender = gender;
        this.age = age;
        this.gpa = gpa;
    }
    public int getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getTg_name() {
        return tg_name;
    }

    public String getGroup() {
        return group;
    }

    public String getCity() {
        return city;
    }

    public boolean isGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getGPA() {
        return gpa;
    }

    @Override
    public String toString() {
        return "User {" +
                "barcode=" + barcode +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", tgNickname='" + tg_name + '\'' +
                ", group='" + group + '\'' +
                ", city='" + city + '\'' +
                ", isMale=" + gender +
                ", age=" + age +
                ", gpa=" + gpa +
                '}';
    }
}