package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContactActivity extends AppCompatActivity {

    private Context context = this;
    private TextView name, phone, address;
    private int id;
    private boolean updated;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact);
        name = findViewById(R.id.tv_name);
        phone = findViewById(R.id.tv_phone);
        address = findViewById(R.id.tv_address);
        Button menu = findViewById(R.id.btn_menu);

        Intent intent = getIntent();
        id = intent.getIntExtra("id", -1);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopup(view);
            }
        });

        setContactInfo(getContactInfo(id));
    }

    public Contact getContactInfo(int id) {
        DatabaseHelper db = new DatabaseHelper(context);
        return db.getContact(id);
    }

    public void setContactInfo(Contact contact) {
        name.setText(contact.getName());
        phone.setText(contact.getPhoneNumber());
        address.setText(contact.getAddress());
    }

    public void deleteContact(Contact contact) {
        DatabaseHelper db = new DatabaseHelper(context);
        db.deleteContact(contact);
    }

    public void edit() {
        Intent i = new Intent(context, EditContactActivity.class);
        i.putExtra("id", id);
        startActivityForResult(i, 1);
    }

    public void delete() {
        deleteContact(getContactInfo(id));
        Intent i = new Intent();
        setResult(RESULT_OK, i);
        finish();
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(context, v);
        popup.inflate(R.menu.menu_contact);
        popup.show();

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.edit:
                        edit();
                        return true;
                    case R.id.delete:
                        delete();
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("updated", updated);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        updated = savedInstanceState.getBoolean("updated");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            setResult(RESULT_OK, new Intent());
            updated = true;
            recreate();
        }
    }

    @Override
    public void onBackPressed() {
        if(updated){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        }
        finish();
    }
}
