package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddContactActivity extends AppCompatActivity {

    private final Context context = this;
    EditText name, phone, address, birthday;
    boolean ignoreChange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        address = findViewById(R.id.et_address);
        birthday = findViewById(R.id.et_birthday);
        birthday.addTextChangedListener(birthdayTextWatcher);
        Button ok = findViewById(R.id.btn_create);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()){
                    name.setError("Name is empty");
                }
                else {
                    addNewContact();
                    Intent i = new Intent();
                    setResult(RESULT_OK, i);
                    finish();
                }
            }
        });
    }

    public void addNewContact() {
        DatabaseHelper db = new DatabaseHelper(context);
        db.addContact(new Contact(
                name.getText().toString(),
                phone.getText().toString(),
                address.getText().toString(),
                birthday.getText().toString()));
    }

    TextWatcher birthdayTextWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            if (!ignoreChange) {
                if (charSequence.length() == 4) {
                    String s = charSequence.toString();
                    s += "-";
                    ignoreChange = true;
                    birthday.setText(s);
                    birthday.setSelection(birthday.getText().length());
                    ignoreChange = false;
                }
                if (charSequence.length() == 7) {
                    String s = charSequence.toString();
                    s += "-";
                    ignoreChange = true;
                    birthday.setText(s);
                    birthday.setSelection(birthday.getText().length());
                    ignoreChange = false;
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    };
}
