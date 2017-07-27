package aisa.application.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aisa.application.R;
import aisa.application.items.ForumService;
import aisa.application.model.Categories;
import aisa.application.model.SubCategories;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.items.ForumService.builder;

public class AddCategory extends AppCompatActivity {

    @BindView(R.id.catName)
    EditText catName;

    @BindView(R.id.subCat)
    EditText subCatName;
    @BindView(R.id.url)
    EditText imageUrl;

    @BindView(R.id.subCat2)
    EditText subCatName2;
    @BindView(R.id.url2)
    EditText imageUrl2;

    @BindView(R.id.subCat3)
    EditText subCatName3;
    @BindView(R.id.url3)
    EditText imageUrl3;

    private List<Categories> list;
    private List<SubCategories> listSub;

    public AddCategory() {
        listSub = new ArrayList<>();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        getCategories();
    }

    private void getCategories() {
        builder.create(ForumService.class).getAllCategories()
                .enqueue(new Callback<List<Categories>>() {
                    @Override
                    public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                        list = response.body();
                    }

                    @Override
                    public void onFailure(Call<List<Categories>> call, Throwable t) {
                        showMessage(t.getMessage());
                    }
                });
    }

    @OnClick({R.id.save, R.id.cancel})
    public void onCancelClick(View v) {
        finish();
    }

    @OnClick(R.id.save)
    public void onSaveClick(View v) {
        if (!catName.getText().toString().isEmpty()) {
            if (!checkAlreadyExists() && allSubcategoriesFilled()) {
                sendAddRequest(new Categories(catName.getText().toString(), listSub));
            }
        } else {
            showMessage("Please fill in the name of category");
        }
    }

    private boolean allSubcategoriesFilled() {
        if (!subCatName.getText().toString().isEmpty() ||
                !subCatName2.getText().toString().isEmpty() ||
                !subCatName3.getText().toString().isEmpty()) {

            if (!subCatName.getText().toString().isEmpty())
                listSub.add(new SubCategories(subCatName.getText().toString(), imageUrl.getText().toString()));
            if (!subCatName2.getText().toString().isEmpty())
                listSub.add(new SubCategories(subCatName2.getText().toString(), imageUrl2.getText().toString()));
            if (!subCatName3.getText().toString().isEmpty())
                listSub.add(new SubCategories(subCatName3.getText().toString(), imageUrl3.getText().toString()));
            return true;
        }
        showMessage("Give at least one subcategory");
        return false;

    }

    private boolean checkAlreadyExists() {
        final String categoryName = catName.getText().toString();
        for (int i = 0; i < list.size(); ++i) {
            if (list.get(i).getName().equals(categoryName)) {
                showMessage("The category already exists!");
                return true;
            }
        }
        return false;
    }

    private void sendAddRequest(Categories categories) {
        builder.create(ForumService.class).createCategory(categories)
                .enqueue(new Callback<Categories>() {
                    @Override
                    public void onResponse(Call<Categories> call, Response<Categories> response) {
                        if (response.isSuccessful()) {
                            showMessage("Added category: " + response.body().getName());
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<Categories> call, Throwable t) {
                        showMessage(t.getMessage());
                    }
                });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}