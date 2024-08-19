package com.example.sharingapp;
import android.content.Context;

public class AddContactCommand extends Command {

    private ContactList _ContactList;
    private Contact _Contact;
    private Context _Context;

    public AddContactCommand(ContactList contactList, Contact contact, Context context) {
        this._ContactList = contactList;
        this._Contact = contact;
        this._Context = context;
    }

    @Override
    public void execute() {
        _ContactList.addContact(_Contact);
        setIsExecuted(_ContactList.saveContacts(_Context));
    }
}
