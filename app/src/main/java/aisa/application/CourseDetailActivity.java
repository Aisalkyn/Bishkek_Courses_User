package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import aisa.application.model.Course;
import aisa.application.model.ForumService;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.model.ForumService.builder;

public class CourseDetailActivity extends AppCompatActivity {
    @BindView(R.id.carouselView)
    CarouselView customCarouselView;
    @BindView(R.id.description)
    TextView description;
    private Realm realm;
    private Course course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        realm = Realm.getDefaultInstance();
        ButterKnife.bind(this);

        customCarouselView.setSlideInterval(4000);
        customCarouselView.setViewListener(viewListener);
        String courseName = getIntent().getStringExtra("course_name");
        if (courseName != null && !courseName.isEmpty()) {
            setTitle(courseName);
            getCourseDetails(courseName);
        }
    }

    @OnClick({R.id.address, R.id.contact, R.id.service})
    public void onClick(View view) {
        Intent intent = null;
        if(view.getId() == R.id.address) {
            intent = new Intent(this, AddressActivity.class);
        }
        else if(view.getId() == R.id.contact) {
            intent = new Intent(this, ContactActivity.class);
        }
        else if(view.getId() == R.id.service) {
            intent = new Intent(this, ServiceActivity.class);
        }
        intent.putExtra("course_name", course.getName());
        startActivity(intent);
    }

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.view_custom, null);
            AppCompatImageView imageView = (AppCompatImageView) customView.findViewById(R.id.carouselImageView);
            if (course != null) {
                Picasso.with(getApplicationContext())
                        .load(course.getImages().get(position).getImagePath())
                        .error(R.drawable.img1)
                        .into(imageView);
            }
            return customView;
        }
    };

    private void getCourseDetails(final String courseName) {
        builder.create(ForumService.class).getCourse(courseName)
                .enqueue(new Callback<Course>() {

                    @Override
                    public void onResponse(Call<Course> call, Response<Course> response) {
                        if (response.isSuccessful()) {
                            course = response.body();
                            saveCourses(course);
                        } else {
                            showMessage(response.message());
                            course = getDataFromDatabase(courseName);
                        }
                        initData();
                    }

                    @Override
                    public void onFailure(Call<Course> call, Throwable t) {
                        showMessage(t.getMessage());
                        course = getDataFromDatabase(courseName);
                        initData();
                    }
                });
    }

    private void initData() {
        customCarouselView.setPageCount(course.getImages().size());
        description.setText(course.getDescription());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void showMessage(String message) {
        View view = findViewById(android.R.id.content);
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

    private Course getDataFromDatabase(String title) {
        return realm.copyFromRealm(realm.where(Course.class)
                .equalTo("name", title).findFirst());
    }

    private void saveCourses(Course data) {
        final RealmResults<Course> result = realm
                .where(Course.class)
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
