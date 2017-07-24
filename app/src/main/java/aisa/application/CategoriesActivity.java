package aisa.application;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

import aisa.application.adapter.AdapterRecyclerView;
import aisa.application.model.Category;
import aisa.application.model.ForumService;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmList;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.model.ForumService.builder;

public class CategoriesActivity extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initRealm();

        realm = Realm.getDefaultInstance();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(getLayoutManager());
        getAllCategories();
    }

    private void initRealm(){
        Realm.init(getApplicationContext());
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("courses")
                        .deleteRealmIfMigrationNeeded()
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private LinearLayoutManager getLayoutManager() {
        return new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }

    private void getAllCategories() {
        builder.create(ForumService.class).getAllCategories()
            .enqueue(new Callback<RealmList<Category>>() {
                @Override
                public void onResponse(Call<RealmList<Category>> call, Response<RealmList<Category>> response) {
                    if (response.isSuccessful()) {
                        recyclerView.setAdapter(new AdapterRecyclerView(response.body()));
                        saveData(response.body());
                    } else {
                        showMessage(response.message());
                        recyclerView.setAdapter(new AdapterRecyclerView(getDataFromDatabase()));
                    }
                }

                @Override
                public void onFailure(Call<RealmList<Category>> call, Throwable t) {
                    showMessage(t.getMessage());
                    recyclerView.setAdapter(new AdapterRecyclerView(getDataFromDatabase()));
                }
            });
    }

    private List<Category> getDataFromDatabase() {
        return realm.copyFromRealm(realm.where(Category.class).findAll());
    }

    private void saveData(RealmList<Category> data) {
        final RealmResults<Category> result = realm.where(Category.class).findAll();
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

    private void showMessage(String message) {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}