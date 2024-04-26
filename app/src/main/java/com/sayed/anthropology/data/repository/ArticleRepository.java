package com.sayed.anthropology.data.repository;

import android.content.Context;

import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.model.ArticleCategory;
import com.sayed.anthropology.utils.AssetUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ArticleRepository {
    static Context context;

    public ArticleRepository(Context context) {
        ArticleRepository.context = context;
    }

    public static Article getArticleFromAsset(String articleId) {
        return AssetUtils.getArticleFromJson(context, articleId);
    }

    public List<Article> findAll() {
        return AssetUtils.getAllArticlesFromJson(context);
    }

    public List<Article> getArticlesByCategory(ArticleCategory articleCategory) {
        return findAll().stream().filter(article -> Objects.equals(article.getCategoryId(), articleCategory.getId())).collect(Collectors.toList());
    }

}