package aisa.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.model.Service;

/**
 * Created by Alier on 7/22/2017.
 */

public class AdapterService extends ArrayAdapter<Service> {

    public AdapterService(Context context, List<Service> list) {
        super(context, 0, list);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Service service = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.cell_services, parent, false);
        }
        TextView name = (TextView) convertView.findViewById(R.id.serviceName);
        TextView description = (TextView) convertView.findViewById(R.id.serviceDescr);
        TextView price = (TextView) convertView.findViewById(R.id.servicePrice);

        name.setText(service.getName());
        description.setText(service.getDescription());
        price.setText(String.valueOf(service.getPrice()));
        return convertView;
    }

}
