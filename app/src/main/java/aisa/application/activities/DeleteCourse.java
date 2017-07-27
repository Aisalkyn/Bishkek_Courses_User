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

import java.util.List;

import aisa.application.R;
import aisa.application.adapter.AdapterSimplifiedCourses;
import aisa.application.items.ForumService;
import aisa.application.model.SimplifiedCourse;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static aisa.application.items.ForumService.builder;

public class DeleteCourse extends AppCompatActivity{
    @BindView(R.id.listView) ListView listView;
    private ForumService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_course);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        service = builder.create(ForumService.class);
        ButterKnife.bind(this);
        getCourses();
    }

    private void getCourses() {
        service.getAllSimplifiedCourses().enqueue(new Callback<List<SimplifiedCourse>>() {
            @Override
            public void onResponse(Call<List<SimplifiedCourse>> call, Response<List<SimplifiedCourse>> response) {
                listView.setAdapter(new AdapterSimplifiedCourses(getApplicationContext(), response.body()));
            }

            @Override
            public void onFailure(Call<List<SimplifiedCourse>> call, Throwable t) {
                showMessage(t.getMessage());
            }
        });
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final SimplifiedCourse course = (SimplifiedCourse) parent.getItemAtPosition(position);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete")
                .setMessage("Do you want to delete " + course.getName())
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        makeDeleteRequest(course.getName());
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                }).setIcon(android.R.drawable.ic_dialog_alert).show();
    }

    private void makeDeleteRequest(String name) {
        service.deleteCourse(name).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                showMessage(response.message());
                getCourses();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            this.onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}