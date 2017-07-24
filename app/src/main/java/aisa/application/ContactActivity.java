package aisa.application;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import aisa.application.adapter.AdapterContact;
import aisa.application.model.Contact;
import aisa.application.model.Course;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by Alier on 7/22/2017.
 */

public class ContactActivity extends AppCompatActivity {

    @BindView(R.id.listContact) ListView listview;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        String courseName = getIntent().getStringExtra("course_name");
        if(courseName != null && !courseName.isEmpty()){
            listview.setAdapter(new AdapterContact(getApplicationContext(), getFromDatabase(courseName)));
        }
    }

    private RealmList<Contact> getFromDatabase(String courseName) {
        return realm.copyFromRealm(realm.where(Course.class)
                .equalTo("name", courseName).findFirst()).getContacts();
    }

    @OnItemClick(R.id.listContact)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}