package com.example.sharingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayOutputStream; // This class is helpful when you need to capture data written to a stream in memory as a byte array. 
import java.util.UUID;

/**
* Item class
*/
public class Item extends Observable {

   private String title;
   private String maker;
   private String description;
   private Dimensions dimensions;
   private String status;
   private Contact borrower;
   protected transient Bitmap image; // the transient keyword is used to indicate that a field should not be serialized when an object is serialized. This means that the value of the field will not be persisted when the object is written to a stream, such as a file or network connection.
   protected String image_base64;
   private String id;

   public Item(String title, String maker, String description, Bitmap image, String id) {
       this.title = title;
       this.maker = maker;
       this.description = description;
       this.dimensions = null;
       this.status = "Available";
       this.borrower = null;
       addImage(image);

       if (id == null){
           setId();
       } else {
           updateId(id);
       }
   }

   public String getId(){
       return this.id;
   }

   public void setId() {
       this.id = UUID.randomUUID().toString();
       notifyObservers();
   }

   public void updateId(String id){
       this.id = id;
       notifyObservers();
   }

   public void setTitle(String title) {
       this.title = title;
       notifyObservers();
   }

   public String getTitle() {
       return title;
   }

   public void setMaker(String maker) {
       this.maker = maker;
       notifyObservers();
   }

   public String getMaker() {
       return maker;
   }

   public void setDescription(String description) {
       this.description = description;
       notifyObservers();
   }

   public String getDescription() {
       return description;
   }

   public void setDimensions(String length, String width, String height) {
       this.dimensions = new Dimensions(length, width, height);
       notifyObservers();
   }

   public String getLength(){
       return dimensions.getLength();
   }

   public String getWidth(){
       return dimensions.getWidth();
   }

   public String getHeight(){
       return dimensions.getHeight();
   }

   public void setStatus(String status) {
       this.status = status;
       notifyObservers();
   }

   public String getStatus() {
       return status;
   }

   public void setBorrower(Contact borrower) {
       this.borrower = borrower;
       notifyObservers();
   }

   public Contact getBorrower() {
       return borrower;
   }

 // By converting the Bitmap to a Base64 string, the image can be easily stored, transmitted (over http request), or embedded within other data formats or stored on file or database.
   public void addImage(Bitmap new_image){
       if (new_image != null) {
           image = new_image; // creates a new local variable named image within the current method instead of modifying the instance variable this.image
           ByteArrayOutputStream byteArrayBitmapStream = new ByteArrayOutputStream(); // ByteArrayOutputStream is a versatile tool for capturing and manipulating data in memory as bytes.
           new_image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayBitmapStream); //  Compresses the Bitmap into a PNG format with 100% quality and writes it to the ByteArrayOutputStream.

           byte[] b = byteArrayBitmapStream.toByteArray(); //  Converts the compressed image data to a byte array.
           image_base64 = Base64.encodeToString(b, Base64.DEFAULT); // Encodes the byte array into a Base64 string using the default encoding.
       }
       notifyObservers();
   }
// The getImage() method is designed to efficiently retrieve a Bitmap object. It prioritizes using an already loaded Bitmap if available, otherwise it decodes the Base64-encoded image data and creates a new Bitmap.
   public Bitmap getImage(){
       if (image == null && image_base64 != null) {
           byte[] decodeString = Base64.decode(image_base64, Base64.DEFAULT); // Decodes the Base64-encoded string into a byte array.
           image = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length); // Creates a Bitmap object from the decoded byte array.
           notifyObservers();
       }
       return image;
   }
}
