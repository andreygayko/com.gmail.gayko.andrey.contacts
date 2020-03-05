package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    Context context = this;
    EditText name, phone, address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_contact);
        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        address = findViewById(R.id.et_address);
        Button ok = findViewById(R.id.btn_create);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    name.setError("Name is empty");
                }
                else {
                    saveContactInfo(id);
                    Intent i = new Intent();
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });

        updateContactInfo(getContactInfo(id));
    }

    public Contact getContactInfo(int id) {
        DatabaseHelper db = new DatabaseHelper(context);
        return db.getContact(id);
    }

    public void updateContactInfo(Contact contact) {
        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        address.setText(contact.getAddress());
    }

    public void saveContactInfo(int id) {
        DatabaseHelper db = new DatabaseHelper(context);
        Contact contact = new Contact(
                id,
                name.getText().toString(),
                phone.getText().toString(),
                address.getText().toString());
        db.updateContact(contact);
    }
}
