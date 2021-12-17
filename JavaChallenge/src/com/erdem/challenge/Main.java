package com.erdem.challenge;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // SCANNER CAN BE DEFINED HERE
    static Scanner myScanner = new Scanner(System.in);

    static ContactManager contactManager = new ContactManager(new ArrayList<Contact>());
    static MessageManager messageManager = new MessageManager(new ArrayList<Message>());

    // WHEN AN INSTANCE OF THE SCANNER IS PRESENT, WE DO NOT NEED TO INITIALISE IT AGAIN

    // COULD HAVE DONE A RECURSIVE CALL IF AN INPUT IS INVALID

    public static void deleteContact() {
        Scanner myScanner = new Scanner(System.in);

        int menuChoice;
        String name;

        System.out.print("\nPlease enter the name of the contact to be deleted:\t");
        name = myScanner.nextLine().toUpperCase();

        boolean contactFound = false;
        for (Contact contact : contactManager.getContacts()) {
            if (name.equals(contact.getName())) {
                contactFound = true;
                System.out.println("Are you sure you would like to delete " + contact.getName() + "? \n(1)\tYES \n(2)\tNO");
                menuChoice = myScanner.nextInt();
                if (menuChoice == 1) {
                    contactManager.deleteContact(contact);
                    System.out.println("Successfully deleted " + contact.getName() + ".");
                } else {System.out.println("Deletion cancelled.");}
                break;
            }
        }

        if (!contactFound) {
            System.out.println("\nContact not found.");
        }
    }
    public static void addContact() {
        Scanner myScanner = new Scanner(System.in);

        boolean contactExists = false;
        System.out.print("\nPlease enter the new contact's name:\t");
        String name = myScanner.nextLine().toUpperCase();

        for (Contact contact : contactManager.getContacts())
            if (name.equals(contact.getName())) {
                contactExists = true;
                System.out.println("The contact already exists.");
            }
        if (!contactExists) {
            System.out.print("\nPlease enter the new contact's number:\t");
            String phoneNumber = myScanner.nextLine().toUpperCase();

            System.out.println("\nAre you sure you would like to add the following contact? \nName:\t" + name +
                    "\nNumber:\t" + phoneNumber + "\n(1)\tYES \n(2)\tNO");

            int menuChoice = myScanner.nextInt();

            if (menuChoice == 1) {
                Contact newContact = new Contact(name, phoneNumber);

                contactManager.addContact(newContact);

                System.out.println("\nSuccessfully added " + name + ".");
            } else {
                System.out.println("\nCancelled new contact.");
            }
        }
    }

        public static void displayContacts() {
            System.out.println("\nDisplaying Contacts:");
            for (Contact contact : contactManager.getContacts()) {
                System.out.println(contact.getName() + ":\t" + contact.getPhoneNumber());

            }
        }

        public static void sendMessage(){
            Scanner myScanner = new Scanner(System.in);

            String name;
            String content;

            System.out.print("\nWho would you like to send a message to:\t");
            name = myScanner.nextLine().toUpperCase();

            boolean contactFound = false;
            for (Contact contact: contactManager.getContacts()){
                if (name.equals(contact.getName())){
                    contactFound = true;
                    System.out.println("What would you like to send to " + contact.getName() + "?");
                    content = myScanner.nextLine();

                    messageManager.addMessage(new Message(name, content));
                    System.out.println("\nSent to  " + name + ":\t" + content);
                }
            }
            if (!contactFound) {System.out.println("\nContact not found.");}

            }

            public static void displayMessages(){
                System.out.println("\nSent Messages:");
                for(Message message: messageManager.getMessages()){
                    System.out.println("\nSent to " + message.getName() + ":\t" + message.getContent());
                }

            }



    public static void main(String[] args) {
        Scanner myScanner = new Scanner(System.in);

        int menuChoice;

        boolean done = false;

        while (!done) {
            // COULD HAVE USED \t FOR TAB INSTEAD OF LEAVING MULTIPLE SPACES
            // OPTIONS CAN BE STORED IN AN ARRAY AND ITERATED TO DISPLAY THEM
            System.out.println("\n(1)\tContacts \n(2)\tMessages \n(3)\tQuit");
            int appChoice = myScanner.nextInt();

            //INSTEAD OF HAVING NESTED SWITCH STATEMENTS, COULD HAVE DIVIDED INTO MORE FUNCTIONS
            switch (appChoice) {

                case 1:
                    System.out.println("Contacts: \n(1)\tDisplay Contacts \n(2)\tAdd Contact \n(3)\tDelete Contact" +
                            "\n(4)\tBack");
                    menuChoice = myScanner.nextInt();

                    switch (menuChoice) {
                        case 1:
                            displayContacts();
                            break;

                        case 2:
                            addContact();
                            break;
                        case 3:
                            deleteContact();
                            break;
                    }
                    break;

                case 2:
                    System.out.println("Messages: \n(1)  Display Messages \n(2) Send Message \n(3) Back");
                    menuChoice = myScanner.nextInt();

                    switch (menuChoice) {
                        case 1:
                            displayMessages();
                            break;
                        case 2:
                            sendMessage();
                            break;
                    }
                    break;

                default:
                    done = true;
                    System.out.println("Goodbye");
                    break;
            }
        }
    }
}

