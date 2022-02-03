package com.company.Entity;

public class UserEntity {
    private int id;
    private String login;
    private String password;
    private String status;
    private String message;

    public UserEntity() {
    }



    public UserEntity(int id, String login, String password, String status) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
    }

    public UserEntity(String login, String password, String status, String message) {
        this.login = login;
        this.password = password;
        this.status = status;
        this.message = message;
    }

    public UserEntity(int id, String login, String password, String status, String message) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.status = status;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
