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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final Context context = this;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_list);
        RecyclerView contacts = findViewById(R.id.rv_contacts);
        Button newContact = findViewById(R.id.btn_add);
        Button fillDb = findViewById(R.id.btn_fill_db);
        Button deleteAll = findViewById(R.id.btn_del_db);
        newContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AddContactActivity.class);
                startActivity(intent);
            }
        });
        fillDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fillTable();
            }
        });
        deleteAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });
        contacts.setLayoutManager(new LinearLayoutManager(this));
        LinkedHashMap<Integer, String> conts = getAllContacts();
        contacts.setAdapter(new ContactsAdapter(this, conts));
    }

    public LinkedHashMap<Integer, String> getAllContacts() {
        DatabaseHelper db = new DatabaseHelper(this);
        List<Contact> contacts = db.getAllContacts();
        LinkedHashMap<Integer, String> conts = new LinkedHashMap<>();
        ArrayList<String> names = new ArrayList<>();
        for (Contact c : contacts) {
            names.add(c.getName());
            conts.put(c.getId(), c.getName());
        }
        return conts;
    }

    public void fillTable() {
        DatabaseHelper db = new DatabaseHelper(this);
        db.addContact(new Contact("name1", "num1", "address1"));
        db.addContact(new Contact("name2", "num2", "address2"));
        db.addContact(new Contact("name3", "num3", "address3"));
        db.addContact(new Contact("name4", "num4", "address4"));
        db.addContact(new Contact("name5", "num5", "address5"));
        db.addContact(new Contact("name6", "num6", "address6"));
        db.addContact(new Contact("name7", "num7", "address7"));
        db.addContact(new Contact("name8", "num8", "address8"));
        db.addContact(new Contact("name9", "num9", "address9"));
        db.addContact(new Contact("name10", "num10", "address10"));
        db.addContact(new Contact("name11", "num11", "address11"));
        db.addContact(new Contact("name12", "num12", "address12"));
        db.addContact(new Contact("name13", "num13", "address13"));
        db.addContact(new Contact("name14", "num14", "address14"));
        db.addContact(new Contact("name15", "num15", "address15"));
        recreate();
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
