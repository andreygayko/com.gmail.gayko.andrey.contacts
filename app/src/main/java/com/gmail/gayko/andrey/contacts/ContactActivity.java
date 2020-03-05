package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
        Button edit = findViewById(R.id.btn_edit);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, EditContactActivity.class);
                i.putExtra("id", id);
                startActivityForResult(i, 1);
            }
        });

        setContactInfo(getContactInfo(id));
    }

    public Contact getContactInfo(int id){
        DatabaseHelper db = new DatabaseHelper(context);
        return db.getContact(id);
    }

    public void setContactInfo(Contact contact){
        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        address.setText(contact.getAddress());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK)
            recreate();
    }
}
