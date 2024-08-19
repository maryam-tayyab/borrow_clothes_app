package com.example.sharingapp;
import android.content.Context;

public class EditContactCommand extends Command {

    private ContactList _ContactList;
    private Contact _OldContact;
    private Contact _NewContact;
    private Context _Context;

    public EditContactCommand(ContactList contactList, Contact oldContact, Contact newContact, Context context) {
        this._ContactList = contactList;
        this._OldContact = oldContact;
        this._NewContact = newContact;
        this._Context = context;
    }

    @Override
    public void execute() {
        _ContactList.deleteContact(_OldContact);
        _ContactList.addContact(_NewContact);
        setIsExecuted(_ContactList.saveContacts(_Context));
    }
}
