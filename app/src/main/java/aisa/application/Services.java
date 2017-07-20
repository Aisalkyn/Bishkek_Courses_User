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

import aisa.application.adapters.ServiceAdapter;
import aisa.application.models.Model_Services;

/**
 * Created by admin on 7/17/17.
 */

public class Services extends AppCompatActivity {

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
            setTitle("Services");
        }


        List<Model_Services> list = new ArrayList<>();
        list.add(new Model_Services("Aaaaa", "kvbsdvbsdkvbsdkubvdsubvjfhbvjfbvjshbv", "5000"));
        list.add(new Model_Services("Bbbbb", "vckdrhvbkhvb", "621"));
        list.add(new Model_Services("Ccccc", "bgtbwrtbw)", "1000"));

        listview.setAdapter(new ServiceAdapter(this, list));

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Services.this, FourthActivity.class);
                startActivity(intent);

            }
        });
    }

}