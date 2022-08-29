package com.example.contact_list;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;


public class Myadapter extends RecyclerView.Adapter<Myadapter.myclass> {

    main_page main_page;
    ArrayList<Usercontact> contactlist;

    public Myadapter(com.example.contact_list.main_page main_page, ArrayList<Usercontact> contactlist) {
        this.main_page = main_page;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public myclass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(main_page).inflate(R.layout.contact,parent,false);

        return new myclass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myclass holder, @SuppressLint("RecyclerView") int position) {

        Usercontact cnt=contactlist.get(position);
        holder.t1.setText(cnt.getName());
        holder.t2.setText(cnt.getNumber());


        holder.im1.setText(cnt.getNumber().toUpperCase());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu popmenu = new PopupMenu(main_page, v);
                popmenu.getMenu().add(Menu.NONE, 1, 1, "edit");
                popmenu.getMenu().add(Menu.NONE, 2, 2, "delete");
                popmenu.show();

                popmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        int i = item.getItemId();
                        if (i == 1) {

                            Intent intent = new Intent(main_page,Update_Activity.class);
                            intent.putExtra("name",cnt.getName());
                            intent.putExtra("number",cnt.getNumber());
                            intent.putExtra("id",cnt.getId());

                            main_page.startActivity(intent);

                            Toast.makeText(main_page, "edit complet", Toast.LENGTH_SHORT).show();
                        }
                        if (i == 2) {

                            Dbhelper dbhelper =new Dbhelper(main_page);

                            dbhelper.deletedeta(cnt.getId());
                            contactlist.remove(position);

                            notifyDataSetChanged();

                            Toast.makeText(main_page, "delet complet", Toast.LENGTH_SHORT).show();

                        }
                        return true;

                    }
                });

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }

    public class myclass extends RecyclerView.ViewHolder {

        TextView t1,t2,im1;

        public myclass(@NonNull View itemView) {
            super(itemView);

            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);
            im1=itemView.findViewById(R.id.im1);
        }
    }
}
