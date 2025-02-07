package models;

import java.io.Serializable;

public class Course implements Serializable {
    private int id;
    private String name;
    private String instructor;

    public Course(int id, String name , String instructor) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
    }
    public Course(String name , String instructor) {
        this.name = name;
        this.instructor = instructor;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }


    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + name + "', instructor='" + instructor + "'}";
    }
}