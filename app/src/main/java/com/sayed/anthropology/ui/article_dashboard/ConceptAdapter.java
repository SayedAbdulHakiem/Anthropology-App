package com.sayed.anthropology.ui.article_dashboard;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.anthropology.R;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.utils.AssetUtils;

import java.util.ArrayList;
import java.util.List;


public class ConceptAdapter extends RecyclerView.Adapter<ConceptAdapter.AdapterViewHolder> {
    private List<Article> dataList = new ArrayList<>();
    private Fragment fragment;

    public ConceptAdapter(Fragment fragment) {
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
        Drawable imageDrawable = AssetUtils.getImageDrawable(fragment.getContext(), dataList.get(position).getId());
        if (imageDrawable == null) {
            holder.articleImageCardView.setVisibility(View.GONE);
        } else {
            holder.articleImageCardView.setVisibility(View.VISIBLE);
            holder.articleImage.setImageDrawable(imageDrawable);
        }
        holder.itemView.setOnClickListener(view -> {
            ((ArticleDashboardFragment) fragment).openArticleFragment(dataList.get(position));
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
        ImageView articleImage;
        CardView articleImageCardView;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            textTv = itemView.findViewById(R.id.text_tv);
            articleImage = itemView.findViewById(R.id.article_image);
            articleImageCardView = itemView.findViewById(R.id.article_image_cardview);
        }
    }
}
