package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import aisa.application.adapters.CustomGrid;
import aisa.application.models.Categories;
import aisa.application.models.SubCategories;

/**
 * Created by admin on 7/15/17.
 */

public class SecondActivity extends AppCompatActivity {
    private GridView gridView;
    private Categories categories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        if (savedInstanceState != null && savedInstanceState.getSerializable("category") != null)
        categories = (Categories) savedInstanceState.getSerializable("category");

        gridView = (GridView) findViewById(R.id.gridView);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("cat", categories.getName());
                intent.putExtra("subCat", categories.getSubCat().get(position).getName());
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
    public void onMessageEvent(Categories categories) {
        this.categories = categories;
        CustomGrid adapter = new CustomGrid(SecondActivity.this, categories.getSubCat());
        gridView.setAdapter(adapter);
        EventBus.getDefault().unregister(this);
    }


}
