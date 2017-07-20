package aisa.application.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import aisa.application.R;
import aisa.application.ThirdActivity;
import aisa.application.models.Categories;

public class SectionListDataAdapter extends RecyclerView.Adapter<SectionListDataAdapter.SingleItemRowHolder> {

    private Categories categories;


    public SectionListDataAdapter(Categories categories) {
        this.categories = categories;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_single_card, viewGroup, false);

        SingleItemRowHolder holder = new SingleItemRowHolder(v);

        return holder;

    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, int i) {
        final int index = i;
        holder.tvTitle.setText(categories.getSubCat().get(i).getName());
        holder.itemImage.setImageResource(R.mipmap.ic_launcher_round);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ThirdActivity.class);
                intent.putExtra("cat", categories.getName());
                intent.putExtra("subCat", categories.getSubCat().get(index).getName());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (categories != null ? categories.getSubCat().size() : 0);
    }


    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        protected TextView tvTitle;

        protected ImageView itemImage;
        protected View view;


        public SingleItemRowHolder(final View view) {
            super(view);
            this.view = view;
            this.tvTitle = (TextView) view.findViewById(R.id.tvTitle);
            this.itemImage = (ImageView) view.findViewById(R.id.itemImage);
        }

    }

}