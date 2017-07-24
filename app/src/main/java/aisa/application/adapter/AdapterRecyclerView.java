package aisa.application.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import aisa.application.R;
import aisa.application.SubCategoriesActivity;
import aisa.application.model.Category;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRecyclerView extends RecyclerView.Adapter<AdapterRecyclerView.ViewHolder> {
    private List<Category> dataList;

    public AdapterRecyclerView(List<Category> dataList) {
        this.dataList = dataList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder itemRowHolder, int position) {
        Category category = dataList.get(position);

        AdapterSections itemAdapter = new AdapterSections(category);
        itemRowHolder.recyclerView.setAdapter(itemAdapter);
        itemRowHolder.itemTitle.setText(category.getName());
        itemRowHolder.btnMore.setTag(position);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerView) RecyclerView recyclerView;
        @BindView(R.id.itemTitle) TextView itemTitle;
        @BindView(R.id.btnMore) Button btnMore;


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(getManager(view.getContext()));
            btnMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), SubCategoriesActivity.class);
                    intent.putExtra("category_name", dataList.get((int) v.getTag()).getName());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    private LinearLayoutManager getManager(Context context){
        return new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
    }
}