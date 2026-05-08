/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandlogin1;

/**
 * Login class that handles user authentication.
 * Author: Student
 * Date: 2026-04-14
 */
public class Login {

    private String enteredUsername;
    private String enteredPassword;
    private Registration registeredUser;

    /**
     * Constructor that takes the credentials the user typed at login,
     * plus the Registration object created during registration.
     */
    public Login(String enteredUsername, String enteredPassword,
                 Registration registeredUser) {
        this.enteredUsername = enteredUsername;
        this.enteredPassword = enteredPassword;
        this.registeredUser = registeredUser;
    }

    /**
     * Compares the entered credentials to the registered user details.
     * Returns true if they match, false otherwise.
     */
    public boolean loginUser() {
        if (registeredUser == null) return false;
        return registeredUser.getUsername().equals(enteredUsername)
                && registeredUser.getPassword().equals(enteredPassword);
    }

    /**
     * Returns the appropriate message for a successful or failed login.
     */
    public String returnLoginStatus() {
        if (loginUser()) {
            return "Welcome " + registeredUser.getFirstName() + ", "
                    + registeredUser.getLastName() + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}