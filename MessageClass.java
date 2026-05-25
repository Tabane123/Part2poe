/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.part1java;

/**
 *
 * @author lenovo
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

class Message {

    String uniqueID;
    int messageNumber;
    String recipient;
    String messageText;
    String messageHash;

    // Convert message to JSON format
    public String toJSON() {
        return "{\n" +
                "  \"MessageID\": \"" + uniqueID + "\",\n" +
                "  \"MessageNumber\": " + messageNumber + ",\n" +
                "  \"Recipient\": \"" + recipient + "\",\n" +
                "  \"Message\": \"" + messageText + "\",\n" +
                "  \"MessageHash\": \"" + messageHash + "\"\n" +
                "}";
    }
}
public class MessageClass {
    // Global message storage
 public static ArrayList<Message> messages = new ArrayList<>();
    
 static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

       

        System.out.println("\n=================================================");
        System.out.println("RE YA GO AMOGELA GO QUICKCHAT (WELCOME TO QUICKCHAT)");
        System.out.println("===================================================");

        while (true) {

            System.out.println("\n....SELECT OPTION....");
            System.out.println("1: SEND MESSAGE");
            System.out.println("2: SHOW RECENT MESSAGES");
            System.out.println("3: QUIT");

            System.out.print("\nEnter your option: ");

            int option = input.nextInt();
            input.nextLine();

            if (option == 1) {

                sendMessage();

            } else if (option == 2) {

                showMessages();

            } else if (option == 3) {

                System.out.println("\nThank you for using QuickChat. GOODBYE!");
                break;

            } else {

                System.out.println("Invalid option.");
            }
        }
    }

    static void sendMessage() {

        

        System.out.print("\nHow many messages would you like to send: ");

        int numMessages;

        while (true) {

            try {

                numMessages = Integer.parseInt(input.nextLine().trim());

                if (numMessages > 0) {
                    break;
                }

                System.out.print("Please enter a positive number: ");

            } catch (NumberFormatException e) {

                System.out.print("Invalid input. Please enter a number: ");
            }
        }

        for (int i = 1; i <= numMessages; i++) {

            System.out.println("\n--- Sending Message " + i + " of " + numMessages + " ---");

            // Recipient validation
            String recipient;

            while (true) {

                System.out.print("Enter recipient cell number (+27xxxxxxxxx): ");

                recipient = input.nextLine().trim();

                if (recipient.matches("^\\+27\\d{9}$")) {
                    break;
                }

                System.out.println("Invalid number! Format must be +27 followed by 9 digits.");
            }

            // Message validation
            String messageText;

            while (true) {

                System.out.print("Enter your message (max 250 characters): ");

                messageText = input.nextLine().trim();

                if (messageText.length() > 250) {

                    System.out.println("Message exceeds 250 characters.");

                } else if (messageText.isEmpty()) {

                    System.out.println("Message cannot be empty.");

                } else {

                    break;
                }
            }

            // Generate ID
            String uniqueId = String.format("%010d",
        new Random().nextInt(1000000000));
            
            System.out.println("Message ID generated: " + uniqueId);

            int messageCounter = messages.size() + 1;

            // Hash creation
            String firstTwo = uniqueId.substring(0, 2);

            String[] words = messageText.trim().split("\\s+");
            String firstWord = words[0].toUpperCase();
             
            String lastWord = words[words.length - 1].toUpperCase();

            String hash = firstTwo + ":" + messageCounter + ":" + firstWord + lastWord;

            // Create message object
            Message msg = new Message();

            msg.uniqueID = uniqueId;
            msg.messageNumber = messageCounter;
            msg.recipient = recipient;
            msg.messageText = messageText;
            msg.messageHash = hash;

            // Display message
            System.out.println("\n--- Message Info ---");
            System.out.println("Message ID   : " + msg.uniqueID);
            System.out.println("Message Hash : " + msg.messageHash);
            System.out.println("Recipient    : " + msg.recipient);
            System.out.println("Message      : " + msg.messageText);
            System.out.println("-----------------------");

            // Post-send options
            System.out.println("\nWhat would you like to do?");
            System.out.println("1 - Send Message");
            System.out.println("2 - Delete Message");
            System.out.println("3 - Save Message to send later");

            System.out.print("Enter choice: ");

            int choice;

 try {
    choice = Integer.parseInt(input.nextLine());
} catch (Exception e) {
    System.out.println("Invalid option.");
    continue;
}

            if (choice == 1) {

                messages.add(msg);

                System.out.println("Message successfully sent!");

                System.out.println("\nJSON FORMAT:");
                System.out.println(msg.toJSON());

            } else if (choice == 2) {

                System.out.println("Message deleted.");

            } else if (choice == 3) {

                messages.add(msg);

                System.out.println("Message successfully stored!");

                System.out.println("\nJSON FORMAT:");
                System.out.println(msg.toJSON());

            } else {

                System.out.println("Invalid option.");
            }
        }
    }

    // Show all stored messages
    static void showMessages() {

        if (messages.isEmpty()) {

            System.out.println("\nNo messages stored.");

            return;
        }

        System.out.println("\n======= STORED MESSAGES =======");

        for (Message msg : messages) {

            System.out.println(msg.toJSON());
            System.out.println();
        }
    }
}

