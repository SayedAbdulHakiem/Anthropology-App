package com.sayed.anthropology.ui.article_dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.anthropology.R;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.utils.AssetUtils;

import java.util.ArrayList;
import java.util.List;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.AdapterViewHolder> {
    private List<Article> dataList = new ArrayList<>();
    private Fragment fragment;

    public ArticleAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_article, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.titleTV.setText(dataList.get(position).getTitle());
        holder.textTv.setText(StringUtils.shortenString(dataList.get(position).getText(), 100));
        holder.storyImage.setImageDrawable(AssetUtils.getImageDrawable(fragment.getContext(), dataList.get(position).getId()));
        holder.itemView.setOnClickListener(view -> {
            ((ArticleDashboardFragment)fragment).openStoryFragment(dataList.get(position));
        });
    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public List<Article> getDataList() {
        return dataList;
    }

    public void setDataList(List<Article> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV, textTv;
        ImageView storyImage;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            textTv = itemView.findViewById(R.id.text_tv);
            storyImage = itemView.findViewById(R.id.story_image);
        }
    }
}
