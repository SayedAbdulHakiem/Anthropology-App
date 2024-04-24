package com.sayed.anthropology.data.repository;

import android.content.Context;

import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.utils.AssetUtils;

import java.util.ArrayList;
import java.util.List;

public class ArticleRepository {
    static Context context;

    public ArticleRepository(Context context) {
        ArticleRepository.context = context;
    }

    public List<Article> findAll() {
        List<Article> articleList = new ArrayList<>();

        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));
        articleList.add(getArticleFromAsset("article_1"));


        return articleList;
    }

    public static Article getArticleFromAsset(String articleId) {
        return AssetUtils.getArticleFromJson(context, articleId);
    }

}