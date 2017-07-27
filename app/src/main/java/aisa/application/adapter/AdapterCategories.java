package aisa.application.adapter;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Categories;

public class AdapterCategories extends ArrayAdapter<Categories> {
    private static int padding = 10;
    public AdapterCategories(Context context, List<Categories> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_text, parent, false);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.textView);
        textView.setText(getItem(position).getName());

        return convertView;
    }

    public int getItemPosition(Categories category){
        for (int i = 0; i < getCount(); ++i) {
            if (getItem(i).getName().equals(category.getName())) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setPadding(padding, padding, padding, padding);
        label.setTextSize(20.f);
        label.setScrollBarSize(25);
        label.setText(getItem(position).getName());

        return label;
    }
}
