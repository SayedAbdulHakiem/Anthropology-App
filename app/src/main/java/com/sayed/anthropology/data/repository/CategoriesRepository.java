package com.sayed.anthropology.data.repository;

import android.content.Context;

import com.sayed.anthropology.model.ArticleCategory;
import com.sayed.anthropology.model.Categories;
import com.sayed.anthropology.utils.AssetUtils;

import java.util.List;

public class CategoriesRepository {
    static Context context;

    public CategoriesRepository(Context context) {
        CategoriesRepository.context = context;
    }

    public static Categories getCategoriesFromAsset() {
        return AssetUtils.getArticleCategoryFromJson(context);
    }

    public List<ArticleCategory> findAll() {

        return getCategoriesFromAsset().getCategoryList();
    }
}