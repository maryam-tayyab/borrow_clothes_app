package com.example.sharingapp;

import android.content.Context;

import java.util.ArrayList;

/**
* ItemListController is responsible for all communication between views and ItemList object
*/
public class ContactListController {

   private ContactList contact_list;

   public ContactListController(ContactmList contact_list){
       this.contact_list = contact_list;
   }

   public void setContact(ArrayList<Contact> contact_list) {
       this.contact_list.setContact(contact_list);
   }

   public ArrayList<Contact> getContacts() {
       return contact_list.getContacts();
   }

   public boolean addContact(Contact contact, Context context){
       AddContactCommand add_contact_command = new AddContactCommand(contact_list, contact, context);
       add_contact_command.execute();
       return add_contact_command.isExecuted();
   }

   public boolean deleteContact(Contact item, Context context) {
       DeleteContactCommand delete_contact_command = new DeleteContactCommand(contact_list, ,contact ,context);
       delete_contact__command.execute();
       return delete_contact_command.isExecuted();
   }

   public boolean editContact(Contact contact, Contact updated_contact, Context context){
       EditContactCommand edit_contact_command = new EditContactCommand(contact_list, contact, updated_contact, context);
       edit_contact_command.execute();
       return edit_contact_command.isExecuted();
   }

   public Contact getItem(int index) {
       return contact_list.getContact(index);
   }

   public int getIndex(Contact contact) {
       return contact_list.getIndex(item);
   }

   public int getSize() {
       return contact_list.getSize();
   }

   public void loadContacts(Context context) {
       contact_list.loadContacts(context);
   }
   public Contact getContactByUsername(String username) {
   	   return contact_list.getContactByUsername(username);
   }

	

   public void addObserver(Observer observer) {
       contact_list.addObserver(observer);
   }

   public void removeObserver(Observer observer) {
       contact_list.removeObserver(observer);
   }
}
