package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import aisa.application.adapters.ContactAdapter;
import aisa.application.models.Model_Contact;

/**
 * Created by admin on 7/17/17.
 */

public class Contact extends AppCompatActivity {

    private Toolbar toolbar;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        listview = (ListView)findViewById(R.id.listcontact);



        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
            toolbar.setTitle("G PlayStore");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            setTitle("Contatcs");
        }


        List<Model_Contact> list = new ArrayList<>();
        list.add(new Model_Contact("Aaaaa", "http//fjdffljiujbf"));
        list.add(new Model_Contact("Bbbbb", "Rhttp//fjderwqfff"));
        list.add(new Model_Contact("Ccccc", "http//fjdehfff"));

        listview.setAdapter(new ContactAdapter(this, list));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Contact.this, FourthActivity.class);
                startActivity(intent);

            }
        });
    }
}
