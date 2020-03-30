package com.gmail.gayko.andrey.contacts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditContactActivity extends AppCompatActivity {

    Context context = this;
    EditText name, phone, address, birthday;
    boolean ignoreChange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_contact);
        name = findViewById(R.id.et_name);
        phone = findViewById(R.id.et_phone);
        address = findViewById(R.id.et_address);
        birthday = findViewById(R.id.et_birthday);
        birthday.addTextChangedListener(birthdayTextWatcher);
        Button ok = findViewById(R.id.btn_create);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("id", -1);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validation()) {
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
        birthday.setText(contact.getBirthDate());
    }

    public void saveContactInfo(int id) {
        DatabaseHelper db = new DatabaseHelper(context);
        Contact contact = new Contact(
                id,
                name.getText().toString(),
                phone.getText().toString(),
                address.getText().toString(),
                birthday.getText().toString());
        db.updateContact(contact);
    }

    public boolean validation() {
        String bday = birthday.getText().toString();

        if (name.getText().toString().isEmpty()) {
            name.setError("Name is empty");
            return false;
        }
        if(bday.length()!=0 && bday.length()!=10){
            birthday.setError("Format YYYY-MM-DD");
            return false;
        }
        //if(Integer.parseInt(bday.substring(0, 4)) < LocalDateTime.now().minusYears(100).getYear()){ //Requires Android api 26
        if (!bday.matches("^[1-2][0-9][0-9][0-9][-][0-1][0-9][-][0-3][0-9]")){
            birthday.setError("Incorrect date");
            return false;
        }
        if(Integer.parseInt(bday.substring(0, 4)) < 1900 || Integer.parseInt(bday.substring(0, 4)) > 2100){
            birthday.setError("Incorrect year");
            return false;
        }
        if(Integer.parseInt(bday.substring(5, 7)) < 1 || Integer.parseInt(bday.substring(5, 7)) > 12){
            birthday.setError("Incorrect month");
            return false;
        }
        if(Integer.parseInt(bday.substring(8, 10)) < 1 || Integer.parseInt(bday.substring(8, 10)) > 31){
            birthday.setError("Incorrect day");
            return false;
        }
        else {
            return true;
        }
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
