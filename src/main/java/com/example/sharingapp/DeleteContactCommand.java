package com.example.sharingapp;
import android.content.Context;

public class DeleteContactCommand extends Command {

    private ContactList _ContactList;
    private Contact _Contact;
    private Context _Context;

    public DeleteContactCommand(ContactList contactList, Contact contact, Context context) {
        this._ContactList = contactList;
        this._Contact = contact;
        this._Context = context;
    }

    @Override
    public void execute() {
        _ContactList.deleteContact(_Contact);
        setIsExecuted(_ContactList.saveContacts(_Context));
    }
}
