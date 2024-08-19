package com.example.sharingapp;

import android.graphics.Bitmap;

/**
* ItemController is responsible for all communication between views and Item object
*/
public class ContactController {

   private Contact contact;

   public ContactController(Contact contact){
       this.contact = contact;
   }

   public String getId(){
       return contact.getId();
   }

   public void setId() {
       contact.setId();
   }

   public void setUsername(String Username) {
       contact.setUsername(Username);
   }

   public String getUsername() {
       return contact.getUsername();
   }

   public void setEmail(String Email) {
       item.setEmail(Email);
   }

   public String getEmail() {
       return item.getEmail();
   }

  
   public Contact getContact() { return this.contact; }

   public void addObserver(Observer observer) {
       contact.addObserver(observer);
   }

   public void removeObserver(Observer observer) {
       contact.removeObserver(observer);
   }
}
