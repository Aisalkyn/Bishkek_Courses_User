package aisa.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import aisa.application.R;
import aisa.application.model.SubCategory;

public class AdapterGrid extends BaseAdapter {
    private List<SubCategory> list;

    public AdapterGrid(List<SubCategory> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) parent.getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (view == null) {
            view = inflater.inflate(R.layout.list_single_card, parent, false);
        }

        TextView textView = (TextView) view.findViewById(R.id.tvTitle);
        ImageView imageView = (ImageView)view.findViewById(R.id.itemImage);
        textView.setText(list.get(position).getName());
        Picasso.with(view.getContext())
                .load(list.get(position).getImagePath())
                .error(R.drawable.img1)
                .into(imageView);
        return view;
    }
}