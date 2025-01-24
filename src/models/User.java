package models;

public class User {
    private int id;
    private int barcode;
    private String name;
    private String surname;
    private String tg_name;
    private String group;
    private String city;
    private boolean gender;
    private int age;

    public User() {}

    public User(int barcode, String name, String surname, String tg_name, String group, String city, boolean gender, int age) {
        setBarcode(barcode);
        setName(name);
        setSurname(surname);
        setTg_name(tg_name);
        setGroup(group);
        setCity(city);
        setGender(gender);
        setAge(age);
    }

    public User(int id, int barcode, String name, String surname, String tg_name, String group, String city, boolean gender, int age) {
        this(barcode, name, surname, tg_name, group, city, gender, age);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTg_name() {
        return tg_name;
    }

    public void setTg_name(String tg_name) {
        this.tg_name = tg_name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student with " +
                "barcode: " + barcode +
                ", name: " + name + '\'' +
                ", surname: " + surname + '\'' +
                ", tg_name: " + tg_name + '\'' +
                ", group: " + group + '\'' +
                ", city: " + city + '\'' +
                ", gender: " + gender +
                ", age: " + age + '\'';
    }
}
