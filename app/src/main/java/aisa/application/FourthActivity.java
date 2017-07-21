package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import org.greenrobot.eventbus.EventBus;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import aisa.application.models.Branches;
import aisa.application.models.Contacts;
import aisa.application.models.Course;
import aisa.application.models.Images;
import aisa.application.models.Model_Services;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.ForumService.builder;

/**
 * Created by admin on 7/15/17.
 */

public class FourthActivity extends AppCompatActivity {

    private CarouselView customCarouselView;
    private TextView textView;
    private Button btnadress;
    private Button btncont;
    private Button btnserv;

    private Course course;
    private String courseName;
    private List<Images> images;
    private String description;
    private List<Branches> branches;
    private List<Model_Services> services;
    private List<Contacts> contacts;


    public FourthActivity() {
        images = new ArrayList<>();
        branches = new ArrayList<>();
        contacts = new ArrayList<>();
        services = new ArrayList<>();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);
        getInfoFromIntent();


    }


    private void getInfoFromIntent() {
        Intent intent = getIntent();
        courseName = intent.getStringExtra("courseName");
        getCourseInfoByName();

    }

    /*
     * This method is used to initiate view
     */
    private void initiate() {
        customCarouselView = (CarouselView) findViewById(R.id.customCarouselView);
        customCarouselView.setViewListener(viewListener);
        customCarouselView.setPageCount(images.size());
        customCarouselView.setSlideInterval(4000);


        textView = (TextView) findViewById(R.id.textView);
        textView.setText(description);

        btnadress = (Button) findViewById(R.id.address);
        btncont = (Button) findViewById(R.id.contact);
        btnserv = (Button) findViewById(R.id.service);

        btnadress.setOnClickListener(viewClickListener);
        btncont.setOnClickListener(viewClickListener);
        btnserv.setOnClickListener(viewClickListener);

        setTitle(courseName);
    }


    /*
     * This is a custom clicklistener used for buttons
     */
    View.OnClickListener viewClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.address:
                    btnAddress();
                    break;
                case R.id.contact:
                    btnContact();
                    break;
                case R.id.service:
                    btnService();
                    break;
            }
        }
    };

    /*
     * This method is called after Button Address
     */
    private void btnAddress(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                EventBus.getDefault().post(new AbstractMap.SimpleEntry<List<Branches>, String>(branches, courseName));
            }
        }, 100);
        goToActivity(Address.class);
    }

    /*
     * This method is called after Button Contact
     */
    private void btnContact(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(contacts);
            }
        }, 100);
        goToActivity(Contact.class);
    }

    /*
     * This method is called after Button Service
     */
    private void btnService(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                EventBus.getDefault().post(services);
            }
        }, 100);
        goToActivity(Services.class);
    }

    private void goToActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);

    }

    // To set custom views
    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {

            View customView = getLayoutInflater().inflate(R.layout.view_custom, null);
            ImageView fruitImageView = (ImageView) customView.findViewById(R.id.carouselImageView);

            String imagePath = images.get(position).getImagePath();
            if (!imagePath.isEmpty())
                Picasso.with(getApplicationContext()).load(imagePath).into(fruitImageView);
            else
                fruitImageView.setImageResource(R.mipmap.ic_launcher);


            return customView;
        }
    };

    private String getImagePath(List<Images> images) {
        String result = "";

        if (!images.isEmpty()) {

            result = images.get(0).getImagePath();

            for (int i = 0; i < images.size(); ++i) {
                if (!images.get(i).isLogo()) {
                    result = images.get(i).getImagePath();
                }
            }

        }

        return result;
    }

    /*
     * This method gets course information
     */
    private void getCourseInfoByName() {
        final ForumService service = builder.create(ForumService.class);
        service.getCourseByName(courseName).enqueue(new Callback<Course>() {
            @Override
            public void onResponse(Call<Course> call, Response<Course> response) {
                course = response.body();
                images = course.getImages();
                description = course.getDescription();
                branches = course.getBranches();
                services = course.getServices();
                contacts = course.getContacts();

                initiate();
            }

            @Override
            public void onFailure(Call<Course> call, Throwable t) {
                t.getMessage();
            }
        });
    }



}
