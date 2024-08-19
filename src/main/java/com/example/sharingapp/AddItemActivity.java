package com.example.sharingapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
* Add a new item
*/
public class AddItemActivity extends AppCompatActivity {

   private EditText title;
   private EditText maker;
   private EditText description;
   private EditText length;
   private EditText width;
   private EditText height;

   private ImageView photo;
   private Bitmap image;
   private int REQUEST_CODE = 1;

   private ItemList item_list = new ItemList();
   private ItemListController item_list_controller = new ItemListController(item_list);

   private Context context;

   @Override //  This annotation tells the compiler that the following method is intended to override a method in the superclass.
   protected void onCreate(Bundle savedInstanceState) { // The access modifier indicates that the method can be accessed by subclasses and within the same package.
       super.onCreate(savedInstanceState); // Bundle savedInstanceState: The parameter is used to restore the activity's state if it was previously destroyed.

       setContentView(R.layout.activity_add_item);

       title = (EditText) findViewById(R.id.title);
       maker = (EditText) findViewById(R.id.maker);
       description = (EditText) findViewById(R.id.description);
       length = (EditText) findViewById(R.id.length);
       width = (EditText) findViewById(R.id.width);
       height = (EditText) findViewById(R.id.height);
       photo = (ImageView) findViewById(R.id.image_view);

       photo.setImageResource(android.R.drawable.ic_menu_gallery);

       context = getApplicationContext();
       item_list_controller.loadItems(context);
   }

   public void saveItem (View view) {

       String title_str = title.getText().toString();
       String maker_str = maker.getText().toString();
       String description_str = description.getText().toString();
       String length_str = length.getText().toString();
       String width_str = width.getText().toString();
       String height_str = height.getText().toString();

       if (title_str.equals("")) {
           title.setError("Empty field!");
           return;
       }

       if (maker_str.equals("")) {
           maker.setError("Empty field!");
           return;
       }

       if (description_str.equals("")) {
           description.setError("Empty field!");
           return;
       }

       if (length_str.equals("")) {
           length.setError("Empty field!");
           return;
       }

       if (width_str.equals("")) {
           width.setError("Empty field!");
           return;
       }

       if (height_str.equals("")) {
           height.setError("Empty field!");
           return;
       }

       Item item = new Item(title_str, maker_str, description_str, image, null);
       ItemController item_controller = new ItemController(item);
       item_controller.setDimensions(length_str, width_str, height_str);

       // Add item
       boolean success = item_list_controller.addItem(item, context);
       if (!success) {
           return;
       }

       // Start AddItemActivity
       // FOllowing snippet is a fundamental part of Android app development, used to initiate a new activity. 
       //The first parameter, this, refers to the current activity context (the activity where this code is executed).
       // An Intent is an object that describes an action to be performed. It can be used to start activities, services, or broadcast receivers.
       // The second parameter, MainActivity.class, specifies the target activity to be launched.
       // This code essentially creates an intent to start the MainActivity and then launches it. This is a common way to navigate between different screens in an Android app.
       Intent intent = new Intent(this, MainActivity.class); 
       startActivity(intent);  // Starts the activity specified in the Intent. This method is inherited from the Context class, which Activity extends.
   }

   // This code snippet initiates the process of capturing an image using the device's default camera app.

   public void addPhoto(View view) {
       Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); // creates a new intent with the action MediaStore.ACTION_IMAGE_CAPTURE. This indicates that the system should launch a camera app to capture an image.
       if (intent.resolveActivity(getPackageManager()) != null) {
           startActivityForResult(intent, REQUEST_CODE); // The startActivityForResult method is used to start an activity and expect a result back. The returned data (e.g., the captured image) will be available in the onActivityResult method.
       }
   }

   public void deletePhoto(View view) {
       image = null;
       photo.setImageResource(android.R.drawable.ic_menu_gallery);
   }

   @Override
   protected void onActivityResult(int request_code, int result_code, Intent intent){
       if (request_code == REQUEST_CODE && result_code == RESULT_OK){
           Bundle extras = intent.getExtras();
           image = (Bitmap) extras.get("data");
           photo.setImageBitmap(image);
       }
   }
}
