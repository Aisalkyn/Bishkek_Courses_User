package aisa.application;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import java.util.List;

import aisa.application.models.SimplifiedCourse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.ForumService.builder;

/**
 * Created by admin on 7/15/17.
 */

public class FourthActivity extends AppCompatActivity {

    CarouselView customCarouselView;
    TextView textView;
    Button btnadress;
    Button btncont;
    Button btnserv;

    int[] sampleImages = {R.drawable.img2, R.drawable.img1, R.drawable.img3 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth);

        customCarouselView = (CarouselView) findViewById(R.id.customCarouselView);
        customCarouselView.setPageCount(sampleImages.length);
        customCarouselView.setSlideInterval(4000);

        customCarouselView.setViewListener(viewListener);

        textView = (TextView)findViewById(R.id.textView);

        btnadress = (Button) findViewById(R.id.addres);
        btncont = (Button) findViewById(R.id.contact);
        btnserv = (Button) findViewById(R.id.service);
        getAllSimplifiedCourses();

        btnadress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FourthActivity.this, Address.class));
            }
        });
        btncont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FourthActivity.this, Contact.class));
            }
        });
        btnserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FourthActivity.this, Services.class));
            }
        });

    }

    // To set custom views
    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {

            View customView = getLayoutInflater().inflate(R.layout.view_custom, null);
            ImageView fruitImageView = (ImageView) customView.findViewById(R.id.carouselImageView);
            fruitImageView.setImageResource(sampleImages[position]);

            return customView;
        }
    };

    private void getAllSimplifiedCourses()
    {
        ForumService service = builder.create(ForumService.class);
        service.getAllSimplifiedCourses().enqueue(new Callback<List<SimplifiedCourse>>() {
            @Override
            public void onResponse(Call<List<SimplifiedCourse>> call, Response<List<SimplifiedCourse>> response) {
                customCarouselView.setPageCount(response.body().size());

                //setImageListener(response.body().get(0).getImages());
                //textView.setText(String.valueOf(response.body().get(position).getDescription()));

            }

            @Override
            public void onFailure(Call<List<SimplifiedCourse>> call, Throwable t) {
                t.getMessage();
            }
        });
    }

}
