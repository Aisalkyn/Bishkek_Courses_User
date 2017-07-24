package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import aisa.application.adapter.AdapterSimplifiedCourse;
import aisa.application.model.ForumService;
import aisa.application.model.SimplifiedCourse;
import aisa.application.model.SimplifiedCourses;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.model.ForumService.builder;

public class CoursesActivity extends AppCompatActivity {
    @BindView(R.id.listView) ListView listView;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);

        String subCategory = getIntent().getStringExtra("subcategory_name");
        if (subCategory != null && !subCategory.isEmpty()) {
            setTitle(subCategory);
            getCourses(subCategory);
        }
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(CoursesActivity.this, CourseDetailActivity.class);
        intent.putExtra("course_name", ((SimplifiedCourse)listView.getItemAtPosition(position)).getName());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void getCourses(final String subC) {
        builder.create(ForumService.class).getSimplifiedCourses(subC)
            .enqueue(new Callback<RealmList<SimplifiedCourse>>() {

            @Override
            public void onResponse(Call<RealmList<SimplifiedCourse>> call,
                                   Response<RealmList<SimplifiedCourse>> response) {
                if(response.isSuccessful()) {
                    listView.setAdapter(new AdapterSimplifiedCourse(
                            getApplicationContext(), 0, response.body()));
                    saveCourses(new SimplifiedCourses(subC, response.body()));
                }else{
                    listView.setAdapter(new AdapterSimplifiedCourse(
                    getApplicationContext(), 0, getDataFromDatabase(subC).getCourses()));
                }
            }

            @Override
            public void onFailure(Call<RealmList<SimplifiedCourse>> call, Throwable t) {
                listView.setAdapter(new AdapterSimplifiedCourse(
                        getApplicationContext(), 0, getDataFromDatabase(subC).getCourses()));
                showMessage(t.getMessage());
            }
        });
    }

    private void showMessage(String message) {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    private SimplifiedCourses getDataFromDatabase(String title) {
        return realm.copyFromRealm(realm.where(SimplifiedCourses.class)
                                    .equalTo("name", title).findFirst());
    }

    private void saveCourses(SimplifiedCourses data) {
        final RealmResults<SimplifiedCourses> result = realm
                .where(SimplifiedCourses.class)
                .equalTo("name", data.getName()).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteAllFromRealm();
            }
        });
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(data);
        realm.commitTransaction();
    }
}