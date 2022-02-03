package com.company.Entity;

public class AdminEntity {
    private int id;
    private String name;
    private String surName;
    private String login;
    private String password;

    public AdminEntity() {
    }

    public AdminEntity(String name, String surName, String login, String password) {
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;
    }

    public AdminEntity(int id, String name, String surName, String login, String password) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
