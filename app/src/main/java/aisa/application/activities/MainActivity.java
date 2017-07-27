package aisa.application.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Arrays;

import aisa.application.R;
import aisa.application.adapter.AdapterList;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.listView)
    ListView listView;

    private Class[] array = {AddCourse.class,
            AddCategory.class,
            AddSubCategory.class,
            DeleteCourse.class,
            DeleteCategory.class,
            DeleteSubCategory.class};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        listView.setAdapter(new AdapterList(this, Arrays.asList(
                getResources().getStringArray(R.array.course_options))));
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(position == 5){
            Intent intent = new Intent(this, array[4]);
            intent.putExtra("delete_subcategory", 100);
            startActivity(intent);
        }else {
            startActivity(new Intent(this, array[position]));
        }
    }
}