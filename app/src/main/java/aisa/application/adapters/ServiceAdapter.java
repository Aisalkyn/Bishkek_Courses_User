package aisa.application.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.models.Model_Services;
import aisa.application.R;

/**
 * Created by admin on 7/17/17.
 */

public class ServiceAdapter extends ArrayAdapter<Model_Services> {

    private List<Model_Services> services;


    public ServiceAdapter (Context context, List<Model_Services> list)
    {
        super(context, 0, list);
    }

    public View getView (int position, View convertView, ViewGroup parent)
    {
        Model_Services m = getItem(position);

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_services, parent, false);


            TextView textView1 = (TextView) convertView.findViewById(R.id.serviceName);
            TextView textView2 = (TextView) convertView.findViewById(R.id.serviceDescr);
            TextView textView3 = (TextView) convertView.findViewById(R.id.servicePrice);



            textView1.setText(m.getName());
            textView2.setText(m.getDescription());
            textView3.setText(m.getPrice());

        }

        return convertView;
    }

}
