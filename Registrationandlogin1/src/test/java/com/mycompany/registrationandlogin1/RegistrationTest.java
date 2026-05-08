/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */package com.mycompany.registrationandlogin1;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Registration and Login system.
 * Uses the exact test data specified in the assignment.
 * Author: Student
 * Date: 2026-04-14
 *
 * Place this file in the Test Packages folder, NOT Source Packages.
 */
public class RegistrationTest {

    // ── assertEquals tests ────────────────────────────────────────────────────

    @Test
    public void testRegisterUser_correctUsername_returnsSuccessMessage() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
                "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.\nRegistration successful!",
                reg.registerUser()
        );
    }

    @Test
    public void testRegisterUser_incorrectUsername_returnsUsernameError() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
                "Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.",
                reg.registerUser()
        );
    }

    @Test
    public void testRegisterUser_correctPassword_returnsSuccessMessage() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertEquals(
                "Username successfully captured.\nPassword successfully captured.\nCell number successfully captured.\nRegistration successful!",
                reg.registerUser()
        );
    }

    @Test
    public void testRegisterUser_incorrectPassword_returnsPasswordError() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "password", "+27838968976");
        assertEquals(
                "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.",
                reg.registerUser()
        );
    }

    @Test
    public void testCheckCellPhoneNumber_correctNumber_returnsTrue() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(reg.checkCellPhoneNumber());
    }

    @Test
    public void testCheckCellPhoneNumber_incorrectNumber_returnsFalse() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "08966553");
        assertFalse(reg.checkCellPhoneNumber());
    }

    // ── assertTrue / assertFalse tests ───────────────────────────────────────

    @Test
    public void testLoginUser_correctCredentials_returnsTrue() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", reg);
        assertTrue(login.loginUser());
    }

    @Test
    public void testLoginUser_incorrectCredentials_returnsFalse() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        Login login = new Login("wrongUser", "wrongPass", reg);
        assertFalse(login.loginUser());
    }

    @Test
    public void testCheckUserName_correctUsername_returnsTrue() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(reg.checkUserName());
    }

    @Test
    public void testCheckUserName_incorrectUsername_returnsFalse() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyle!!!!!!!", "Ch&&sec@ke99!", "+27838968976");
        assertFalse(reg.checkUserName());
    }

    @Test
    public void testCheckPasswordComplexity_correctPassword_returnsTrue() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "Ch&&sec@ke99!", "+27838968976");
        assertTrue(reg.checkPasswordComplexity());
    }

    @Test
    public void testCheckPasswordComplexity_incorrectPassword_returnsFalse() {
        Registration reg = new Registration("Kyle", "Smith",
                "kyl_1", "password", "+27838968976");
        assertFalse(reg.checkPasswordComplexity());
    }
}

