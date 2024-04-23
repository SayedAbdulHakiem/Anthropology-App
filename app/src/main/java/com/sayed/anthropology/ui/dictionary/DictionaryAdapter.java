package com.sayed.anthropology.ui.dictionary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sayed.anthropology.R;
import com.sayed.anthropology.model.MyDictionary;

import java.util.ArrayList;
import java.util.List;


public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.AdapterViewHolder> {
    private List<MyDictionary> dataList = new ArrayList<>();
    private Fragment fragment;

    public DictionaryAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_dictionary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {
        holder.englishTv.setText(dataList.get(position).getTextEn());
        holder.arabicTv.setText(dataList.get(position).getTextAr());
        holder.itemView.setOnClickListener(view -> {
            ((DictionaryFragment) fragment).speak(dataList.get(position));
        });
    }


    @Override
    public int getItemCount() {
        if (dataList == null) {
            return 0;
        }
        return dataList.size();
    }

    public List<MyDictionary> getDataList() {
        return dataList;
    }

    public void setDataList(List<MyDictionary> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }


    public class AdapterViewHolder extends RecyclerView.ViewHolder {

        TextView englishTv, arabicTv;
        ImageView speakImg;

        public AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            englishTv = itemView.findViewById(R.id.english_tv);
            arabicTv = itemView.findViewById(R.id.arabic_tv);
            speakImg = itemView.findViewById(R.id.speak_image);
        }
    }
}
