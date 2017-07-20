package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import aisa.application.adapters.ContactAdapter;
import aisa.application.models.Contacts;

/**
 * Created by admin on 7/17/17.
 */

public class Contact extends AppCompatActivity {

    private ListView listview;
    private List<Contacts> contacts;

    public Contact(){
        contacts = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        listview = (ListView)findViewById(R.id.listcontact);
        setTitle("Contatcs");





        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Contact.this, FourthActivity.class);
                startActivity(intent);

            }
        });
    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Contacts> contacts) {
        this.contacts = contacts;
        listview.setAdapter(new ContactAdapter(this, contacts));
        EventBus.getDefault().unregister(this);
    }

}
