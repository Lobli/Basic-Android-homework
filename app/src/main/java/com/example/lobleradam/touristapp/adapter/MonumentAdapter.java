package com.example.lobleradam.touristapp.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lobleradam.touristapp.R;
import com.example.lobleradam.touristapp.data.MonumentItem;

import java.util.ArrayList;
import java.util.List;

public class MonumentAdapter extends RecyclerView.Adapter<MonumentAdapter.MonumentViewHolder> {

    //list of the monuments
    private final List<MonumentItem> items;

    private MonumentItemClickListener listener;


    public MonumentAdapter(MonumentItemClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @NonNull
    @Override
    public MonumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext())
                //shows the layout of an item
                .inflate(R.layout.item_monument_list, parent, false);
        return new MonumentViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MonumentViewHolder holder, int position) {
        MonumentItem item = items.get(position);

        holder.nameTextView.setText(item.name);
        holder.countryTextView.setText(item.country);
        //holder.categoryTextView.setText(item.category.name());
        holder.cityTextView.setText(item.city+",");
        holder.CategoryIconImageView.setImageResource(getImageResource(item.category));
        holder.item = item;
    }

    private @DrawableRes
    int getImageResource(MonumentItem.Category category) {
        @DrawableRes int ret;
        switch (category) {
            case STATUE:
                ret = R.drawable.ic_statue;
                break;
            case BUILDING:
                ret = R.drawable.ic_building;
                break;
            case BRIDGE:
                ret = R.drawable.ic_bridge;
                break;
            case LANDSCAPE:
                ret = R.drawable.ic_landscape;
                break;
            default:
                ret = 0;
        }
        return ret;
    }

    public void addItem(MonumentItem item) {
        items.add(item);
        notifyItemInserted(items.size() - 1);
    }

    public void update(List<MonumentItem> MonumentItems) {
        items.clear();
        items.addAll(MonumentItems);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    public interface MonumentItemClickListener{
        void removeItem(MonumentItem item);
        void onItemSelected(MonumentItem item);
    }

    class MonumentViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;

        TextView countryTextView;
        TextView cityTextView;

        ImageView CategoryIconImageView;

        ImageButton removeButton;

        MonumentItem item;

        MonumentViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.MonumentItemNameTextView);
            cityTextView = itemView.findViewById(R.id.MonumentItemCityTextView);
            countryTextView = itemView.findViewById(R.id.MonumentItemCountryTextView);

            CategoryIconImageView = itemView.findViewById(R.id.MonumentItemCategoryImageView);

            removeButton = itemView.findViewById(R.id.MonumentItemRemoveButton);

            nameTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null){
                        listener.onItemSelected(item);
                    }
                }
            });

            removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    if (item != null){
                        listener.removeItem(item);
                        items.remove(item);
                        notifyDataSetChanged();
                    }

                }
            });

        }
    }
}


