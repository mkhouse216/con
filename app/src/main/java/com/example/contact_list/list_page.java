package com.example.contact_list;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class list_page extends AppCompatActivity {

    EditText editnumber,editname;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_page);

        editname=findViewById(R.id.editname);
        editnumber=findViewById(R.id.editnumber);
        b1=findViewById(R.id.b1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editname.getText().toString().trim().equalsIgnoreCase("")){
                    editname.setError("enter name");
                }
                else if(editnumber.getText().toString().trim().equalsIgnoreCase("")){
                    editnumber.setError("enter number ");
                }
                else {

                    String Name = editname.getText().toString();
                    String Number = editnumber.getText().toString();

                    Dbhelper  dbhelper = new Dbhelper(list_page.this);

                    dbhelper.insertdata(Name,Number);

                    Toast.makeText(list_page.this, "number save", Toast.LENGTH_SHORT).show();

                    Intent intent =new Intent(list_page.this,main_page.class);
                    startActivity(intent);
                    finish();
                }
            }
        });


        editname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                editname.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editname.getText().toString().equals("")) {
                    editname.setError("Enter Name");
                } else {
                    editname.setError(null);
                }

            }
        });

        editnumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                editnumber.setError(null);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (editnumber.getText().toString().equals("")) {
                    editnumber.setError("Enter number");
                } else {
                    editnumber.setError(null);
                }
            }
        });

    }
}