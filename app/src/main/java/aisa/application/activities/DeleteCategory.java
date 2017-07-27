package aisa.application.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import aisa.application.R;
import aisa.application.adapter.AdapterCategories;
import aisa.application.items.ForumService;
import aisa.application.model.Categories;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.items.ForumService.builder;

public class DeleteCategory extends AppCompatActivity {
    @BindView(R.id.listView) ListView listView;
    private ForumService service;

    private int DEL_CAT_SUB = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        service = builder.create(ForumService.class);
        DEL_CAT_SUB = getIntent().getIntExtra("delete_subcategory", -1);
        getListOfCategories();
    }

    private void getListOfCategories() {
        service.getAllCategories().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                listView.setAdapter(new AdapterCategories(getApplicationContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Categories category = (Categories) parent.getItemAtPosition(position);
        if (DEL_CAT_SUB != -1) {
            Intent intent = new Intent(this, DeleteSubCategory.class);
            intent.putExtra("category", category);
            startActivity(intent);
        } else {
            showAlertView(category);
        }
    }

    private void showAlertView(final Categories category){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete")
                .setMessage("Do you want to delete " + category.getName())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        makeDeleteRequest(category.getName());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void makeDeleteRequest(final String name) {
        service.deleteCategory(name).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    showMessage("Deleted " + name);
                    getListOfCategories();
                } else
                    showMessage(getString(R.string.error_deleting));
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}