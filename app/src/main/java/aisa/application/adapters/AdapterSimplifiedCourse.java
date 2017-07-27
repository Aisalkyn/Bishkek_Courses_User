package aisa.application.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.squareup.picasso.Picasso;

import aisa.application.R;
import aisa.application.models.Image;
import aisa.application.models.SimplifiedCourse;
import io.realm.RealmList;

/**
 * Created by Alier on 7/22/2017.
 */

public class AdapterSimplifiedCourse extends ArrayAdapter<SimplifiedCourse> {

    public AdapterSimplifiedCourse(Context context, int resource, RealmList<SimplifiedCourse> list) {
        super(context, resource, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        SimplifiedCourse model = getItem(position);
        if(view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.simplified_cell, parent, false);
        }
        AppCompatImageView imageView = (AppCompatImageView) view.findViewById(R.id.imageView);
        AppCompatTextView textView = (AppCompatTextView) view.findViewById(R.id.textView);
        textView.setText(model.getName());
        Picasso.with(getContext())
                .load(getLogo(model.getImages()))
                .error(R.drawable.img1)
                .into(imageView);

        return view;
    }

    private String getLogo(RealmList<Image> list){
        for(Image image: list){
            if(image.isLogo()){
                return image.getImagePath();
            }
        }
        return null;
    }
}
