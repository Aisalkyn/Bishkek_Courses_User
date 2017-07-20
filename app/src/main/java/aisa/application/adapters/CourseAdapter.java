package aisa.application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import aisa.application.models.Course;
import aisa.application.models.Model2;
import aisa.application.R;
import aisa.application.models.SimplifiedCourse;
import aisa.application.models.SubCategories;

/**
 * Created by admin on 7/15/17.
 */

public class CourseAdapter extends ArrayAdapter<Course> {

    private List<Course> listC;

    public CourseAdapter(Context context, List<Course> listC)
    {
        super(context, 0, listC);
    }


    public View getView (int position, View convertView, ViewGroup parent)
    {
        Course m = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell2, parent, false);


            TextView textView1 = (TextView) convertView.findViewById(R.id.text);
            ///ImageView imageView1 = (ImageView) convertView.findViewById(R.id.imageView);
            textView1.setText(listC.get(position).getName());
            //Picasso.with(getContext()).load(listC.get(position).getImages().get(0).getImagePath()).into(imageView1);


        }

        return convertView;
    }

}