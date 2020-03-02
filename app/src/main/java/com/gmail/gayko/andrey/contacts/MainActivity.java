package com.gmail.gayko.andrey.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final Context context = this;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        RecyclerView contacts = findViewById(R.id.rv_contacts);
        Button newContact = findViewById(R.id.btn_add);
        Button deleteAll = findViewById(R.id.btn_del_db);
        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddContactActivity.class);
                startActivity(intent);
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
        contacts.setLayoutManager(new LinearLayoutManager(this));
        //fillTable();
        ArrayList<String> conts = getAllContacts();
        contacts.setAdapter(new ContactsAdapter(this, conts));
    }

    public ArrayList<String> getAllContacts() {
        DatabaseHelper db = new DatabaseHelper(this);
        List<Contact> contacts = db.getAllContacts();
        ArrayList<String> names = new ArrayList<>();
        for (Contact c : contacts) {
            names.add(c.getName());
        }
        return names;
    }

    public void fillTable() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.addContact(new Contact("name1", "num1", "address1"));
        db.addContact(new Contact("name2", "num2", "address2"));
        db.addContact(new Contact("name3", "num3", "address3"));
    }

    public void dropTable() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.dropTable();
    }

    public void dialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.confirm_delete);
        Button ok = dialog.findViewById(R.id.btn_del_y);
        Button cancel = dialog.findViewById(R.id.btn_del_n);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dropTable();
                recreate();
                dialog.cancel();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
