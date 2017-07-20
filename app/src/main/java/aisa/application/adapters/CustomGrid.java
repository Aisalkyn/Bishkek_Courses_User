package aisa.application.adapters;

/**
 * Created by admin on 7/15/17.
 */

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
import aisa.application.models.SubCategories;


public class CustomGrid extends BaseAdapter {
    private Context mContext;
    private List<SubCategories> list;


    public CustomGrid(Context c, List<SubCategories> list) {
        mContext = c;
        this.list = list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View view;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            view = inflater.inflate(R.layout.list_single_card, null);
        } else {
            view = (View) convertView;
        }
        TextView textView = (TextView) view.findViewById(R.id.tvTitle);
        ImageView imageView = (ImageView)view.findViewById(R.id.itemImage);
        textView.setText(list.get(position).getName());
        Picasso.with(mContext).load(list.get(position).getImagePath()).into(imageView);
        return view;
    }

}