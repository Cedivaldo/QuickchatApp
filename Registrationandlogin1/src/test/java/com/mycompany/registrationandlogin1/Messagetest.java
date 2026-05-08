/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandlogin1;

/**
 *
 * @author Student
 */


import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Unit tests for the Message class (Part 2).
 * Uses exact test data from the assignment.
 */
public class Messagetest {

    private Message validMessage;
    private Message invalidRecipientMessage;

    // Test data from assignment
    private final String VALID_RECIPIENT   = "+27718693002";
    private final String INVALID_RECIPIENT = "08575975889";
    private final String MESSAGE_1         = "Hi Mike, can you join us for dinner tonight?";
    private final String MESSAGE_2         = "Hi Keegan, did you receive the payment?";

    @Before
    public void setUp() {
        validMessage           = new Message(0, VALID_RECIPIENT, MESSAGE_1);
        invalidRecipientMessage = new Message(1, INVALID_RECIPIENT, MESSAGE_2);
    }

    // ─── assertEquals: Message length ──────────────────────────────────────

    @Test
    public void testMessageLengthSuccess() {
        // Message under 250 characters — should return "Message ready to send."
        assertEquals("Message ready to send.", validMessage.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        // Message over 250 characters — should return failure message with number over
        String longMessage = "A".repeat(260);
        Message msg = new Message(0, VALID_RECIPIENT, longMessage);
        int over = 260 - 250;
        assertEquals("Message exceeds 250 characters by " + over + "; please reduce the size.",
                     msg.checkMessageLength());
    }

    // ─── assertEquals: Recipient cell number ───────────────────────────────

    @Test
    public void testRecipientCellSuccess() {
        // Valid number with international code
        // Test data: +27718693002
        assertEquals("Cell phone number successfully captured.",
                     validMessage.checkRecipientCell());
    }

    @Test
    public void testRecipientCellFailure() {
        // Invalid number — no international code
        // Test data: 08575975889
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. " +
                     "Please correct the number and try again.",
                     invalidRecipientMessage.checkRecipientCell());
    }

    // ─── assertEquals: Message hash ────────────────────────────────────────

    @Test
    public void testMessageHashCorrect() {
        // Test Case 1: "Hi Mike, can you join us for dinner tonight?"
        // Expected: firstTwoOfID:0:HITONIGHT
        // Assignment shows: 00:0:HITONIGHT
        String hash = validMessage.getMessageHash();
        assertTrue("Hash should end with :HITONIGHT", hash.endsWith(":HITONIGHT"));
        assertTrue("Hash should contain :0:", hash.contains(":0:"));
    }

    // ─── Message ID tests ──────────────────────────────────────────────────

    @Test
    public void testMessageIDCreated() {
        // Message ID should be generated and not null
        assertNotNull("Message ID should not be null", validMessage.getMessageID());
        System.out.println("Message ID generated: " + validMessage.getMessageID());
    }

    @Test
    public void testMessageIDLength() {
        // Message ID should be 10 characters or less
        assertTrue("Message ID should be max 10 characters", validMessage.checkMessageID());
    }

    // ─── assertEquals: SentMessage choices ─────────────────────────────────

    @Test
    public void testSentMessageSend() {
        // User selects Send Message (1)
        // System returns: "Message successfully sent."
        Message msg = new Message(0, VALID_RECIPIENT, MESSAGE_1);
        assertEquals("Message successfully sent.", msg.SentMessage(1));
    }

    @Test
    public void testSentMessageDisregard() {
        // User selects Disregard Message (2)
        // System returns: "Press 0 to delete the message."
        Message msg = new Message(0, VALID_RECIPIENT, MESSAGE_1);
        assertEquals("Press 0 to delete the message.", msg.SentMessage(2));
    }

    @Test
    public void testSentMessageStore() {
        // User selects Store Message (3)
        // System returns: "Message successfully stored."
        Message msg = new Message(0, VALID_RECIPIENT, MESSAGE_1);
        assertEquals("Message successfully stored.", msg.SentMessage(3));
    }

    // ─── Total messages counter ─────────────────────────────────────────────

    @Test
    public void testReturnTotalMessages() {
        // Total messages should increment when a message is sent
        int before = Message.returnTotalMessages();
        Message msg = new Message(0, VALID_RECIPIENT, MESSAGE_1);
        msg.SentMessage(1);
        int after = Message.returnTotalMessages();
        assertTrue("Total messages should have increased", after > before);
    }
}