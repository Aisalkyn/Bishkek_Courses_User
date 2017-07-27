package aisa.application.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aisa.application.R;
import aisa.application.adapter.AdapterCategories;
import aisa.application.adapter.AdapterSubCategory;
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

public class AddSubCategory extends AppCompatActivity {
    @BindView(R.id.spinner) Spinner catSpinner;
    @BindView(R.id.subName) EditText subName;
    @BindView(R.id.imagePath) EditText imagePath;
    @BindView(R.id.listofSub) ListView listOfSubCat;

    private List<SubCategories> subCategories;

    public AddSubCategory() {
        subCategories = new ArrayList<>();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subcategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        getCategories();
    }

    public void getCategories() {
        builder.create(ForumService.class).getAllCategories().enqueue(new Callback<List<Categories>>() {
            @Override
            public void onResponse(Call<List<Categories>> call, Response<List<Categories>> response) {
                catSpinner.setAdapter(new AdapterCategories(getApplicationContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<Categories>> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    private void Expandable(ListView listView, ArrayAdapter adapter) {
        int totalHeight = 0;
        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.AT_MOST);
        for (int i = 0; i < adapter.getCount(); i++) {
            View listItem = adapter.getView(i, null, listView);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (adapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    @OnClick(R.id.save)
    public void onSave(View v) {
        makeAddRequest(new Categories(((Categories)catSpinner.getSelectedItem()).getName(), subCategories));
    }

    @OnClick(R.id.cancel)
    public void onCancel(View view){
        finish();
    }

    @OnClick(R.id.add)
    public void onAdd(View view){
        if (!subName.getText().toString().isEmpty() && !imagePath.getText().toString().isEmpty()) {
            if (checkSubcategoryExists()) {
                return;
            }
            AdapterSubCategory adapter = ((AdapterSubCategory) listOfSubCat.getAdapter());
            subCategories.add(new SubCategories(subName.getText().toString(), imagePath.getText().toString()));
            Expandable(listOfSubCat, adapter);
            adapter.notifyDataSetChanged();
            subName.setText("");
            imagePath.setText("");
        } else {
            showMessage("no empty field allowed");
        }
    }

    private boolean checkSubcategoryExists() {
        final String subcategory = subName.getText().toString();

        for (int i = 0; i < catSpinner.getAdapter().getCount(); ++i) {
            Categories category = ((Categories)catSpinner.getAdapter().getItem(i));
            if (subcategory.equals(category.getName())) {
                showMessage("The subcategory has the same name as category: '" + category.getName() + "'");
                return true;
            }
            for (SubCategories subCategory : category.getSubCat()) {
                if (subCategory.getName().equals(subcategory)) {
                    showMessage("The subcategory exists in category: '" + subCategory.getName() + "'");
                    return true;
                }
            }
        }
        return false;
    }

    private void makeAddRequest(final Categories subCategories) {
        builder.create(ForumService.class).createSubcategory(subCategories)
        .enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful()) {
                    showMessage("Subcategories are added into category: " + subCategories.getName());
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

}