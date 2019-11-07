package com.example.expensetracker.models;

public class User {
    private String name;
    private String mail;
    private String gender;
    private String username;
    private int age;
    private String password;


    public User(String name, String mail, String username, String gender, String password, int age) {
        this.name = name;
        this.mail = mail;
        this.username = username;
        this.gender = gender;
        this.age = age;
        this.password = password;
    }

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
