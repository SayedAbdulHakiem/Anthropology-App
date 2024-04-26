package com.sayed.anthropology.ui.article_dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sayed.anthropology.model.ArticleCategory;

public class ArticleDashboardViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<ArticleCategory> selectedCategory;

    public ArticleDashboardViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        selectedCategory = new MutableLiveData<>();
    }

    public MutableLiveData<ArticleCategory> getSelectedCategory() {
        return selectedCategory;
    }

    public LiveData<String> getText() {
        return mText;
    }
}