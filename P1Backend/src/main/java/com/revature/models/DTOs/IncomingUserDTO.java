package com.revature.models.DTOs;


public class IncomingUserDTO {

    private String firstname;
    private String lastname;
    private String username;
    private String password;

    public IncomingUserDTO() {
    }


    public IncomingUserDTO(String firstname, String lastname, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    @Override
    public String toString() {
        return "IncomingUserDTO{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
