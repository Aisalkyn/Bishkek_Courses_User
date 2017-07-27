package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import aisa.application.adapters.AdapterGrid;
import aisa.application.models.Category;
import aisa.application.models.SubCategory;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import io.realm.Realm;

public class SubCategoriesActivity extends AppCompatActivity {
    @BindView(R.id.gridView) GridView gridView;
    private Realm realm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();

        String categoryName = getIntent().getStringExtra("category_name");
        if(categoryName != null && !categoryName.isEmpty()){
            setTitle(categoryName);
            gridView.setAdapter(new AdapterGrid(getSubCategory(categoryName).getSubCategories()));
        }
    }

    private Category getSubCategory(String name) {
        return realm.copyFromRealm(realm.where(Category.class)
                .equalTo("name", name).findFirst());
    }

    @OnItemClick(R.id.gridView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        SubCategory subC = (SubCategory) gridView.getItemAtPosition(position);
        Intent intent = new Intent(SubCategoriesActivity.this, CoursesActivity.class);
        intent.putExtra("subcategory_name", subC.getName());
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
