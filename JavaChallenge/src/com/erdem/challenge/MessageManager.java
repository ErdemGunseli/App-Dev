package com.erdem.challenge;

import java.util.ArrayList;

public class MessageManager {

   private ArrayList<Message> messages;

   public MessageManager(ArrayList<Message> messages) {
      this.messages = messages;
   }

   public void addMessage(Message message) {messages.add(message);}

      public ArrayList<Message> getMessages(){return messages;}

}
