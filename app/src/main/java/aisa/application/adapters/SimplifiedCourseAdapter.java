package aisa.application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import aisa.application.R;
import aisa.application.models.Course;
import aisa.application.models.Images;
import aisa.application.models.SimplifiedCourse;

/**
 * Created by admin on 7/15/17.
 */

public class SimplifiedCourseAdapter extends ArrayAdapter<SimplifiedCourse> {

    private List<SimplifiedCourse> listC;

    public SimplifiedCourseAdapter(Context context, int id, List<SimplifiedCourse> listC) {
        super(context, id, listC);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        SimplifiedCourse m = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell2, parent, false);


            TextView textView = (TextView) convertView.findViewById(R.id.text);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);
            textView.setText(m.getName());
            String imagePath = getImagePath(m.getImages());
            if (!imagePath.isEmpty())
                Picasso.with(getContext()).load(imagePath).into(imageView);
            else
                imageView.setImageResource(R.mipmap.ic_launcher);

        }

        return convertView;
    }

    private String getImagePath(List<Images> images) {
        String result = "";

        if (!images.isEmpty()) {

            result = images.get(0).getImagePath();

            for (int i = 0; i < images.size(); ++i) {
                if (images.get(i).isLogo()) {
                    result = images.get(i).getImagePath();
                }
            }

        }

        return result;
    }


}