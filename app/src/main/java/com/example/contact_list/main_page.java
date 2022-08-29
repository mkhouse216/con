package com.example.contact_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;

public class main_page extends AppCompatActivity {

    FloatingActionButton button;
    RecyclerView mycontactdeta;
    SearchView search;
    TextView title2;
    ArrayList<Usercontact> contactlist =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactlist.clear();

        title2=findViewById(R.id.title2);
        title2.setSelected(true);
        search=findViewById(R.id.search);
        button=findViewById(R.id.button);
        mycontactdeta=findViewById(R.id.mycontactdeta);

        Dbhelper db= new Dbhelper(main_page.this);

        Cursor cursor=db.viewdata();

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                ArrayList<Usercontact> temp =new ArrayList();

                for(int i=0;i<contactlist.size();i++)
                {
                    String name=contactlist.get(i).getName();
                    String number=contactlist.get(i).getName();

                    if(name.toLowerCase().contains(newText.toLowerCase())||number.toLowerCase().contains(newText.toLowerCase()))
                    {
                        temp.add(contactlist.get(i));
                    }
                }


                Myadapter myadapter=new Myadapter(main_page.this,temp);
                mycontactdeta.setAdapter(myadapter);

                return false;
            }
        });

        while (cursor.moveToNext()){


        int id =cursor.getInt(0);
        String name =cursor.getString(1);
        String number =cursor.getString(2);

        Usercontact usercontact =new Usercontact(id,name,number);
        contactlist.add(usercontact);

            Log.e("====", "onCreate: NAME :: "+name+ "NUMBER :"+number+"Id"+id);

        }

        Log.e("====lll", "onCreate: "+contactlist);


        Myadapter myadapter=new Myadapter(this,contactlist);
        mycontactdeta.setAdapter(myadapter
        );


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(main_page.this,list_page.class);
                startActivity(intent);

            }
        });
    }
}