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
import aisa.application.model.ContactType;

public class AdapterContactTypes extends ArrayAdapter<ContactType> {
    public AdapterContactTypes(Context context, List<ContactType> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setText(getItem(position).getName());
        label.setTextSize(18.f);
        return label;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(getContext());
        label.setTextColor(Color.BLACK);
        label.setTextSize(20.f);
        label.setScrollBarSize(25);
        label.setText(getItem(position).getName());
        return label;
    }

    public int getPos(int contactType){
        for(int i = 0; i < getCount();++i){
            if(getItem(i).getType() == contactType){
                return i;
            }
        }
        return -1;
    }
}
