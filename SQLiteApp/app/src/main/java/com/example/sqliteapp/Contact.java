package com.example.sqliteapp;

public class Contact {

    private int id;

    private String firstName;
    private String lastName;

    private int age;

    private boolean admin;

    public Contact(int id, String firstName, String lastName, int age, boolean admin){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.admin = admin;
    }

    public int getId(){
        return this.id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public boolean isAdmin() {
        return admin;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", admin=" + admin +
                '}';
    }
}
