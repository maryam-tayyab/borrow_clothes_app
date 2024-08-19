
package com.example.sharingapp;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken; // the TypeToken class is used for specifying generic types when working with Gson.

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
* ItemList class
*/
public class ItemList extends Observable{

   private static ArrayList<Item> items; // static attribute belong to the class and not neccessarily to a specific instance of a class
   private String FILENAME = "items.sav";

   public ItemList() {
       items = new ArrayList<Item>();
   }

   public void setItems(ArrayList<Item> item_list) {
       items = item_list;
       notifyObservers();
   }

   public ArrayList<Item> getItems() {
       return items;
   }

   public void addItem(Item item) {
       items.add(item);
       notifyObservers();
   }

   public void deleteItem(Item item) {
       items.remove(item);
       notifyObservers();
   }

   public Item getItem(int index) {
       return items.get(index);
   }

   public int getIndex(Item item) {
       int pos = 0;
       for (Item i : items) {
           if (item.getId().equals(i.getId())) {
               return pos;
           }
           pos = pos + 1;
       }
       return -1;
   }

   public int getSize() {
       return items.size();
   }

// The loadItems method attempts to load a list of Item objects from a file named FILENAME stored in the app's internal storage. 
// If successful, it populates the items ArrayList. Otherwise, it initializes an empty items list.
   public void loadItems(Context context) {

       try {
           FileInputStream fis = context.openFileInput(FILENAME); //Opens a file input stream to read data from the specified file.
           InputStreamReader isr = new InputStreamReader(fis); // Wraps the file input stream with an input stream reader to read characters.
           Gson gson = new Gson(); // Creates a Gson object for JSON serialization/deserialization. To deserialize the JSON data into a specific type of collection (e.g., List<String>) TypeToken is used to provide Gson with the necessary type information
           Type listType = new TypeToken<ArrayList<Item>>(){}.getType(); // Creates a TypeToken object representing the type of the items list
           items = gson.fromJson(isr, listType); // temporary - Deserializes the JSON data from the file into an ArrayList<Item> and assigns it to the items variable.
           fis.close();
       } catch (FileNotFoundException e) {
           items = new ArrayList<Item>();
       } catch (IOException e) {
           items = new ArrayList<Item>();
       }
       notifyObservers();
   }

   public boolean saveItems(Context context) {
       try {
           FileOutputStream fos = context.openFileOutput(FILENAME, 0);
           OutputStreamWriter osw = new OutputStreamWriter(fos);
           Gson gson = new Gson();
           gson.toJson(items, osw);
           osw.flush();
           fos.close();
       } catch (FileNotFoundException e) { // handles the case where the file doesn't exist
           e.printStackTrace();
           return false;
       } catch (IOException e) { //Handles other potential I/O exceptions 
           e.printStackTrace();
           return false;
       }
       return true;
   }

   public ArrayList<Contact> getActiveBorrowers() {

       ArrayList<Contact> active_borrowers = new ArrayList<Contact>();
       for (Item i : items) {
           Contact borrower = i.getBorrower();
           if (borrower != null) {
               active_borrowers.add(borrower);
           }
       }
       return active_borrowers;
   }

   public ArrayList<Item> filterItemsByStatus(String status){
       ArrayList<Item> selected_items = new ArrayList<>();
       for (Item i: items) {
           if (i.getStatus().equals(status)) {
               selected_items.add(i);
           }
       }
       return selected_items;
   }
}

