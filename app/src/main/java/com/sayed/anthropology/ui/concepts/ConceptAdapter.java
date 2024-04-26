package com.sayed.anthropology.ui.concepts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.anthropology.R;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.model.Concept;

import java.util.ArrayList;
import java.util.List;


public class ConceptAdapter extends RecyclerView.Adapter<ConceptAdapter.AdapterViewHolder> {
    private List<Concept> dataList = new ArrayList<>();
    private Fragment fragment;

    public ConceptAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_concept, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.titleTV.setText(dataList.get(position).getTitle());
        holder.textTv.setText(StringUtils.shortenString(dataList.get(position).getText(), 100));
        holder.itemView.setOnClickListener(view -> {
            if (holder.textTv.getText().length() > 103) {
                holder.textTv.setText(StringUtils.shortenString(dataList.get(position).getText(), 100));
            } else {
                holder.textTv.setText(dataList.get(position).getText());
            }

        });
    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public List<Concept> getDataList() {
        return dataList;
    }

    public void setDataList(List<Concept> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView titleTV, textTv;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV = itemView.findViewById(R.id.title_tv);
            textTv = itemView.findViewById(R.id.text_tv);
        }
    }
}
