package com.gmail.gayko.andrey.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView contacts;
    ContactsAdapter contactsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        contacts = findViewById(R.id.rv_contacts);
        contacts.setLayoutManager(new LinearLayoutManager(this));
        //fillTable();
        ArrayList<String> conts = getAllContacts();
        contactsAdapter = new ContactsAdapter(this, conts);
        contacts.setAdapter(contactsAdapter);
        //dropTable();
    }

    public ArrayList<String> getAllContacts(){
        DatabaseHelper db = new DatabaseHelper(this);
        List<Contact> contacts = db.getAllContacts();
        ArrayList<String> names = new ArrayList<>();
        for(Contact c : contacts){
            names.add(c.getName());
        }
        return names;
    }

    public void fillTable(){
        DatabaseHelper db = new DatabaseHelper(this);
        db.addContact(new Contact("name1", "num1", "address1"));
        db.addContact(new Contact("name2", "num2", "address2"));
        db.addContact(new Contact("name3", "num3", "address3"));
    }

    public void dropTable(){
        DatabaseHelper db = new DatabaseHelper(this);
        db.dropTable();
    }

}
