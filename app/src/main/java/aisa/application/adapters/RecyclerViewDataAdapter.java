package aisa.application.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import aisa.application.ForumService;
import aisa.application.R;
import aisa.application.SecondActivity;
import aisa.application.models.Categories;
import aisa.application.models.SectionDataModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static aisa.application.ForumService.builder;

/**
 * Created by admin on 6/27/17.
 */


public class RecyclerViewDataAdapter extends RecyclerView.Adapter<RecyclerViewDataAdapter.ViewHolder> {

    private List<Categories> dataList;
    private LinearLayoutManager manager;

    public RecyclerViewDataAdapter(List<Categories> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        manager = new LinearLayoutManager(viewGroup.getContext(), LinearLayoutManager.HORIZONTAL, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder itemRowHolder, int i) {
        String sectionName = dataList.get(i).getName();
        itemRowHolder.itemTitle.setText(sectionName);

        SectionListDataAdapter itemAdapter = new SectionListDataAdapter(dataList.get(i));

        itemRowHolder.recycler_view_list.setHasFixedSize(true);
        itemRowHolder.recycler_view_list.setLayoutManager(manager);
        itemRowHolder.recycler_view_list.setAdapter(itemAdapter);
        itemRowHolder.btnMore.setTag(i);

        itemRowHolder.btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = (int)itemRowHolder.btnMore.getTag();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        EventBus.getDefault().post(dataList.get((int)itemRowHolder.btnMore.getTag()));
                    }
                }, 100);
                Intent intent = new Intent(v.getContext(), SecondActivity.class);
                v.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView itemTitle;

        protected RecyclerView recycler_view_list;

        protected Button btnMore;


        public ViewHolder(View view) {
            super(view);

            this.itemTitle = (TextView) view.findViewById(R.id.itemTitle);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
            this.btnMore= (Button) view.findViewById(R.id.btnMore);

        }

    }

}
