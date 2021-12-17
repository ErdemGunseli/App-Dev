package com.erdem.challenge;

import java.util.ArrayList;

public class ContactManager {
    private ArrayList<Contact> contacts;




    public ContactManager(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public void deleteContact(Contact contact) {contacts.remove(contact);}

    public void addContact (Contact contact) {
        contacts.add(contact);
    }

    public ArrayList<Contact> getContacts(){return contacts;}

}


