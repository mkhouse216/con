package com.example.contact_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Update_Activity extends AppCompatActivity {

    EditText editnumber, editname;
    Button b1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        editname = findViewById(R.id.editname);
        editnumber = findViewById(R.id.editnumber);
        b1 = findViewById(R.id.b1);

        int idd = getIntent().getIntExtra("id", 0);
        String namee = getIntent().getStringExtra("name");
        String number = getIntent().getStringExtra("number");

        editname.setText(namee);
        editnumber.setText(number);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String newname  = editname.getText().toString();
                String newnumber = editnumber.getText().toString();

                Dbhelper dbhelper = new Dbhelper(Update_Activity.this);
                dbhelper.onUpgrade1(newname,newnumber,idd);
                startActivity(new Intent(Update_Activity.this,main_page.class));

            }
        });


        Log.e("======", "onCreate: " + idd);


    }
}