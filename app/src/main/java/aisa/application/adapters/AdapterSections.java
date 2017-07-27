package aisa.application.adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import aisa.application.CoursesActivity;
import aisa.application.R;
import aisa.application.models.Category;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterSections extends RecyclerView.Adapter<AdapterSections.SingleItemRowHolder> {

    private Category categories;

    public AdapterSections(Category categories) {
        this.categories = categories;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_single_card, viewGroup, false);
        v.setTag(position);
        return new SingleItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {
        holder.tvTitle.setText(categories.getSubCategories().get(position).getName());
        Picasso.with(holder.view.getContext())
                .load(categories.getSubCategories().get(position).getImagePath())
                .error(R.drawable.img1)
                .into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return (categories != null ? categories.getSubCategories().size() : 0);
    }

    public class SingleItemRowHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvTitle) TextView tvTitle;
        @BindView(R.id.itemImage) ImageView itemImage;
        protected View view;

        public SingleItemRowHolder(final View view) {
            super(view);
            ButterKnife.bind(this, this.view = view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String subC = categories.getSubCategories().get((int) v.getTag()).getName();
                    Intent intent = new Intent(v.getContext(), CoursesActivity.class);
                    intent.putExtra("subcategory_name", subC);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}