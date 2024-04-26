package com.sayed.anthropology.ui.article_dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.anthropology.R;
import com.sayed.anthropology.model.ArticleCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.AdapterViewHolder> {
    ArticleCategory selectedItem = null;
    private List<ArticleCategory> dataList = new ArrayList<>();
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
        if (Objects.equals(selectedItem, dataList.get(position))) {
            holder.selectedLayout.setVisibility(View.VISIBLE);
        } else {
            holder.selectedLayout.setVisibility(View.GONE);
        }
        holder.titleTV.setText(dataList.get(position).getTitle());
        holder.itemView.setOnClickListener(view -> {
            ((ArticleDashboardFragment) fragment).selectCategory(dataList.get(position));
        });
    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public List<ArticleCategory> getDataList() {
        return dataList;
    }

    public void setDataList(List<ArticleCategory> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public void setSelectedItem(ArticleCategory articleCategory) {
        this.selectedItem = articleCategory;
        notifyDataSetChanged();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV;

        LinearLayout selectedLayout;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            selectedLayout = itemView.findViewById(R.id.selected_layout);

        }
    }
}
