package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    private Context context = this;
    private TextView name, phone, address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);
        address = findViewById(R.id.tv_address);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        getContactInfo(id);
    }

    public void getContactInfo(int id){
        DatabaseHelper db = new DatabaseHelper(context);
        Contact contact = db.getContact(id);
        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        address.setText(contact.getAddress());
    }

}
