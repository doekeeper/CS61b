package byog.PracticeSaveObjects;

import java.io.Serializable;

public class Student implements Serializable {
    // default serialVersion id
    private static final long serialVersionUID = 1L;
    private String first_name;
    private String last_name;
    private int age;

    // parameterized constructor
    public Student(String fname, String lname, int age) {
        this.first_name = fname;
        this.last_name = lname;
        this.age = age;
    }

    // setters and getters
    public void setFristName(String fname) {
        this.first_name = fname;
    }

    public String getFirstName() {
        return this.first_name;
    }

    public void setLastName(String lname) {
        this.last_name = lname;
    }

    public String getLastName() {
        return this.last_name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return new StringBuffer(" First Name: ").append(this.first_name).append(" Last Name: ").append(this.last_name).append(" Age: ").append(this.age).toString();
    }
}

