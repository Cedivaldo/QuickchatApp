/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package com.mycompany.registrationandlogin1;

public class Registration {

    private String username;
    private String password;
    private String cellPhoneNumber;
    private String firstName;
    private String lastName;

    // Constructor order: username, password, firstName, lastName, cellPhoneNumber
    public Registration(String username, String password,
                        String firstName, String lastName,
                        String cellPhoneNumber) {

        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cellPhoneNumber = cellPhoneNumber;
    }

    // Username validation: must contain "_" and part BEFORE underscore is ≤ 5 characters
    public boolean checkUserName() {
        if (username == null || !username.contains("_")) {
            return false;
        }
        int underscorePos = username.indexOf('_');
        String beforeUnderscore = username.substring(0, underscorePos);
        return beforeUnderscore.length() <= 5 && beforeUnderscore.length() > 0;
    }

    // Password validation: 8+ chars, uppercase, lowercase, number, special character
    public boolean checkPasswordComplexity() {
        if (password == null || password.length() < 8) {
            return false;
        }
        
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        boolean hasSpecial = false;
        
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUpper = true;
            } else if (Character.isLowerCase(c)) {
                hasLower = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            } else {
                hasSpecial = true;
            }
        }
        
        return hasUpper && hasLower && hasNumber && hasSpecial;
    }

    // Cell phone validation: starts with +27 and has 9 digits after
    public boolean checkCellPhoneNumber() {
        if (cellPhoneNumber == null) {
            return false;
        }
        return cellPhoneNumber.startsWith("+27") && 
               cellPhoneNumber.length() == 12 && 
               cellPhoneNumber.substring(3).matches("\\d{9}");
    }

    // Registration messages - RETURNS full string with line breaks
    public String registerUser() {
        String usernameMsg;
        String passwordMsg;
        String cellMsg;
        
        // Check username
        if (checkUserName()) {
            usernameMsg = "Username successfully captured.";
        } else {
            usernameMsg = "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        
        // Check password
        if (checkPasswordComplexity()) {
            passwordMsg = "Password successfully captured.";
        } else {
            passwordMsg = "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        
        // Check cell phone
        if (checkCellPhoneNumber()) {
            cellMsg = "Cell number successfully captured.";
        } else {
            cellMsg = "Cell number is not correctly formatted; please ensure that it starts with +27 and contains 9 digits after the country code.";
        }
        
        // Return combined message with line breaks
        if (checkUserName() && checkPasswordComplexity() && checkCellPhoneNumber()) {
            return usernameMsg + "\n" + passwordMsg + "\n" + cellMsg + "\nRegistration successful!";
        } else {
            return usernameMsg + "\n" + passwordMsg + "\n" + cellMsg;
        }
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    
}