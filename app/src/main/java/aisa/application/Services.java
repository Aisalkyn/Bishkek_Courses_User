package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import aisa.application.adapters.ServiceAdapter;
import aisa.application.models.Contacts;
import aisa.application.models.Model_Services;

/**
 * Created by admin on 7/17/17.
 */

public class Services extends AppCompatActivity {

    ListView listview;
    private List<Model_Services> services;

    public Services() {
        services = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        listview = (ListView) findViewById(R.id.listcontact);

        setTitle("Services");


    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(List<Model_Services> services) {
        this.services = services;
        listview.setAdapter(new ServiceAdapter(this, services));
        EventBus.getDefault().unregister(this);
    }

}