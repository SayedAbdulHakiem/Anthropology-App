package com.sayed.anthropology.ui.article_dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.anthropology.R;
import com.sayed.anthropology.model.Category;

import java.util.ArrayList;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.AdapterViewHolder> {
    private List<Category> dataList = new ArrayList<>();
    private Fragment fragment;

    public CategoryAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_category, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.titleTV.setText(dataList.get(position).getTitle());
        holder.itemView.setOnClickListener(view -> {
//            ((ArticleDashboardFragment)fragment).openStoryFragment(dataList.get(position));
        });
    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public List<Category> getDataList() {
        return dataList;
    }

    public void setDataList(List<Category> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);

        }
    }
}
