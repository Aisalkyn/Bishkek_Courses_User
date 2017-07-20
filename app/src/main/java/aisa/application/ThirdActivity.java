package aisa.application;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.ListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import aisa.application.adapters.CourseAdapter;
import aisa.application.adapters.CustomGrid;
import aisa.application.adapters.SimplifiedCourseAdapter;
import aisa.application.models.Categories;
import aisa.application.models.Course;
import aisa.application.models.SimplifiedCourse;
import aisa.application.models.SubCategories;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.ForumService.builder;

/**
 * Created by admin on 7/15/17.
 */


public class ThirdActivity extends AppCompatActivity {
    private ListView listView;
    private SubCategories subCategories;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        listView = (ListView) findViewById(R.id.listView);

        String category = getIntent().getStringExtra("cat");
        String subCategory = getIntent().getStringExtra("subCat");
        setTitle(subCategory);
        if (subCategory != null && !subCategory.isEmpty()) {
            getCourses(subCategory);
        }
    }

    private void getCourses(String subC) {
        ForumService service = builder.create(ForumService.class);
        service.getCoursesBySubcategory(subC).enqueue(new Callback<List<SimplifiedCourse>>() {
            @Override
            public void onResponse(Call<List<SimplifiedCourse>> call, Response<List<SimplifiedCourse>> response) {
                listView.setAdapter(new SimplifiedCourseAdapter(getApplicationContext(), R.layout.cell2,response.body()));
            }

            @Override
            public void onFailure(Call<List<SimplifiedCourse>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

}