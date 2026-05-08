/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.registrationandlogin1;



import java.util.ArrayList;
import java.util.Random;

/**
 * Message class for the QuickChat App (Part 2).
 * Handles message creation, validation, hashing, sending, storing and displaying.
 *
 * Reference for JSON storage:
 * Crunchify. 2023. How to write JSON object to File in Java.
 * Available at: https://crunchify.com/how-to-write-json-object-to-file-in-java/
 * [Accessed 4 May 2026].
 */
public class Message {

    private String messageID;
    private int messageNumber;
    private String recipient;
    private String message;
    private String messageHash;

    private static int totalMessages = 0;
    private static ArrayList<String> sentMessages = new ArrayList<>();

    public Message(int messageNumber, String recipient, String message) {
        this.messageID     = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient     = recipient;
        this.message       = message;
        this.messageHash   = createMessageHash();
    }

    /**
     * Generates a random 10-digit message ID.
     */
    private String generateMessageID() {
        Random rand = new Random();
        long id = (long)(rand.nextDouble() * 9000000000L) + 1000000000L;
        return String.valueOf(id);
    }

    /**
     * Checks that the message ID is not more than 10 characters long.
     */
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    /**
     * Checks that the recipient cell number contains an international code
     * and is correctly formatted.
     * Reused from Part 1 checkCellPhoneNumber logic.
     */
    public String checkRecipientCell() {
        if (recipient.matches("^\\+[0-9]{1,3}[0-9]{1,10}$") && recipient.length() <= 13) {
            return "Cell phone number successfully captured.";
        }
        return "Cell phone number is incorrectly formatted or does not contain an international code. " +
               "Please correct the number and try again.";
    }

    /**
     * Creates and returns the Message Hash.
     * Format: first 2 digits of messageID : messageNumber : firstWord + lastWord (ALL CAPS)
     * Example: 00:0:HITONIGHT
     */
    public String createMessageHash() {
        String firstTwo  = messageID.substring(0, 2);
        String[] words   = message.trim().split("\\s+");
        String firstWord = words[0].replaceAll("[^a-zA-Z0-9]", "");
        String lastWord  = words[words.length - 1].replaceAll("[^a-zA-Z0-9]", "");
        return (firstTwo + ":" + messageNumber + ":" + firstWord + lastWord).toUpperCase();
    }

    /**
     * Checks if the message is within 250 characters.
     * Returns "Message ready to send." or failure message with how many over.
     */
    public String checkMessageLength() {
        if (message.length() <= 250) {
            return "Message ready to send.";
        }
        int over = message.length() - 250;
        return "Message exceeds 250 characters by " + over + "; please reduce the size.";
    }

    /**
     * Allows the user to send, disregard, or store the message.
     * 1 = Send, 2 = Disregard, 3 = Store
     */
    public String SentMessage(int choice) {
        if (choice == 1) {
            totalMessages++;
            String details = printMessages();
            sentMessages.add(details);
            return "Message successfully sent.";
        } else if (choice == 2) {
            return "Press 0 to delete the message.";
        } else if (choice == 3) {
            storeMessage();
            return "Message successfully stored.";
        }
        return "Invalid option. Please select 1, 2, or 3.";
    }

    /**
     * Returns the full details of this message in the required order:
     * Message ID, Message Hash, Recipient, Message.
     */
    public String printMessages() {
        return "Message ID: "   + messageID   + "\n" +
               "Message Hash: " + messageHash + "\n" +
               "Recipient: "    + recipient   + "\n" +
               "Message: "      + message;
    }

    /**
     * Returns the total number of messages sent.
     */
    public static int returnTotalMessages() {
        return totalMessages;
    }

    /**
     * Returns all sent messages as a formatted string.
     */
    public static String getAllSentMessages() {
        if (sentMessages.isEmpty()) {
            return "No messages sent yet.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentMessages.size(); i++) {
            sb.append("--- Message ").append(i + 1).append(" ---\n");
            sb.append(sentMessages.get(i)).append("\n\n");
        }
        return sb.toString();
    }

    /**
     * Stores the message to a JSON file.
     *
     * Reference: Crunchify. 2023. How to write JSON object to File in Java.
     * Available at: https://crunchify.com/how-to-write-json-object-to-file-in-java/
     * [Accessed 4 May 2026].
     */
    public void storeMessage() {
        try {
            java.io.FileWriter file = new java.io.FileWriter("storedMessages.json", true);
            file.write("{\n");
            file.write("  \"messageID\": \""   + messageID   + "\",\n");
            file.write("  \"messageHash\": \"" + messageHash + "\",\n");
            file.write("  \"recipient\": \""   + recipient   + "\",\n");
            file.write("  \"message\": \""     + message     + "\"\n");
            file.write("},\n");
            file.flush();
            file.close();
        } catch (java.io.IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    public String getMessageID()   { return messageID; }
    public String getRecipient()   { return recipient; }
    public String getMessage()     { return message; }
    public String getMessageHash() { return messageHash; }
    public int getMessageNumber()  { return messageNumber; }
}