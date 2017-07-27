package aisa.application.activities;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import aisa.application.R;
import aisa.application.adapter.AdapterSubCategory;
import aisa.application.items.ForumService;
import aisa.application.model.Categories;
import aisa.application.model.SubCategories;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.items.ForumService.builder;

public class DeleteSubCategory extends AppCompatActivity {
    @BindView(R.id.listView)
    ListView listView;
    Categories category;
    AdapterSubCategory adapter;

    public DeleteSubCategory() {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_subcat);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        category = (Categories) getIntent().getSerializableExtra("category");
        if(category != null)
            listView.setAdapter(adapter = new AdapterSubCategory(this, category.getSubCat()));
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final SubCategories subCategory = (SubCategories) parent.getItemAtPosition(position);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete")
                .setMessage("Do you want to delete " + subCategory.getName())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        makeDeleteRequest(position, subCategory.getName());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    private void makeDeleteRequest(final int pos, final String name) {
        builder.create(ForumService.class).deleteSubcategory(name)
            .enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        showMessage(response.message());
                        category.getSubCat().remove(pos);
                        adapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    showMessage(t.getMessage());
                }
            });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }
}