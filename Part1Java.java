/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.part1java;

/**
 *
 * @author lenovo
 */
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class Part1Java {
    static Scanner input = new Scanner(System.in);
        // Global message storage
    static ArrayList<Message> messages = new ArrayList<>();

    public static void main(String[] args) {
        
        
  
   System.out.println("Welcome");
   System.out.println(" ===========================");
   System.out.println("  PLEASE ENTER YOUR DETAILS ");
   System.out.println("  ==========================  ");
     ///using string to store the name,lastname,username,Password,Cell phone number                                               
    String name;
    String Lastname;
    String Username = null;
    String Password = null;
    String CellPhoneNumber =  null;
    
    
        while (true) {
   // rules give to the user to Enter the correct Ditails for Username t
        System.out.println("==Username Rules==");
        System.out.println("Must contain an underscore(_)");
        System.out.println("Must be no more than 5 characters");
        
        System.out.print("Enter Username: ");
        Username = input.nextLine();
       
    if( Username.contains("_") && Username.length() <=5){
        System.out.print("Username Successfully added");
        break;
    }
  
    else {
        System.out.print("Enter the correct uername format");
    }
  }
    
    
  while (true) {
     
       // Rules give to User to Enter the Correct Password Ditails
         System.out.println("\n Password Rules:");  
         System.out.println("Must have a capital Letter");
         System.out.println("must have a sepecial char");
         
         System.out.print("Enter Password: ");
         Password = input.nextLine();
         
        ///if all the requirment are met the 
         boolean mustHaveUpperCase = Password.matches(".*[A-Z].*");
    boolean mustHaveANumber = Password.matches(".*\\d.*");
    boolean mustHaveSymbol = Password.matches(".*[12#$%6&*()_+=\\-\\[\\]{};':\"\\\\|,.<>/?].*");
       
       //if all the requirment are met the code will stop and display "Pasword successfully captured"
       if(mustHaveUpperCase && mustHaveANumber && mustHaveSymbol)
       if(Password.length() <=8){
           System.out.print("Pasword successfully captured");
           break;
       }        
       else{
            System.out.println(" Pasword unsuccessfully cpatured, Try Again");
       }         
          System.out.println("Registration Successfull");
  }    
  
  while (true){
      System.out.println("rulse");
      System.out.println("Must start with the country internation code");
      System.out.println("must be length of 9");
      
      System.out.println("Cell phon numbers:");
      CellPhoneNumber =  input.nextLine();
      
  
  //cell phone check
  if(CellPhoneNumber.matches("^\\+27\\d{9}$")){
  System.out.println("numbers successfully added");
  break;
  }
  else {
      System.out.println("numbers Unsuccessfully added,  enter thre correct format");
          }
  }
  
    System.out.print("Enter Name:");
    name = input.nextLine();
 
    System.out.println("Enter lastname: ");
    Lastname = input.nextLine();
 
          //After the use enter the correct ditail coe will welcom the User
     System.out.println("Welcome"  +name   + Lastname);
     System.out.println("--PLEASE ENTER YOUR LOGIN details--");
    
   
     System.out.print("Enter Username: ");
     String   LoginUsername = input.nextLine();
     
     System.out.print("Enter Password: ");
     String LoginPassword = input.nextLine();
            
     if(LoginUsername.equals(Username) && LoginPassword.equals(Password)){
         System.out.println("Login Successful");
        
     }else{
         System .out.println("Incorrect username or password, Please enter the corret Details ");
     }
    
     
     
  {

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

                System.out.println("COMING SOON");

            } else if (option == 3) {

                System.out.println("\nThank you for using QuickChat. GOODBYE!");
                break;

            } else {

                System.out.println("Invalid option.");
            }
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
            int messageCounter = messages.size() + 1;
            
            

            // Hash creation
            String firstTwo = uniqueId.substring(0, 2);

            String firstWord = messageText.split(" ")[0].toUpperCase();

            String lastWord = messageText.contains(" ")
                    ? messageText.substring(messageText.lastIndexOf(" ") + 1).toUpperCase()
                    : firstWord;
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

            int choice = Integer.parseInt(input.nextLine());

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
   }
   




