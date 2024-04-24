package com.sayed.anthropology.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.model.Categories;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {
    public static String IMAGES_PATH = "images/";
    public static String IMAGE_EXTENSION = ".jpg";

    public static String ARTICLE_TEXT_PATH = "article_text/";
    public static String ARTICLE_TEXT_EXTENSION = ".json";

    public static String ARTICLE_CATEGORIES_PATH = "article_category/categories";
    public static String CATEGORY_TEXT_EXTENSION = ".json";

    private static String getImagePath(String articleId) {
        return IMAGES_PATH + articleId + IMAGE_EXTENSION;
    }

    private static String getArticleTextPath(String articleId) {
        return ARTICLE_TEXT_PATH + articleId + ARTICLE_TEXT_EXTENSION;
    }

    private static String getArticleCategoriesTextPath() {
        return ARTICLE_CATEGORIES_PATH + CATEGORY_TEXT_EXTENSION;
    }


    public static Drawable getImageDrawable(Context context, String articleId) {
        if (!StringUtils.hasText(articleId)) {
            return null;
        }
        try {
            InputStream inputStream = context.getAssets().open(getImagePath(articleId));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            inputStream.close();
            return drawable;
        } catch (IOException e) {
            return null;
        }
    }

    public static Article getArticleFromJson(Context context, String articleId) {
        if (!StringUtils.hasText(articleId)) {
            return null;
        }
        try {
            InputStream inputStream = context.getAssets().open(getArticleTextPath(articleId));
            ObjectMapper objectMapper = new ObjectMapper();
            Article article = objectMapper.readValue(inputStream, Article.class);
            inputStream.close();
            return article;
        } catch (IOException e) {
            return null;
        }
    }

    public static Categories getArticleCategoryFromJson(Context context) {

        try {
            InputStream inputStream = context.getAssets().open(getArticleCategoriesTextPath());
            ObjectMapper objectMapper = new ObjectMapper();
            Categories categories = objectMapper.readValue(inputStream, Categories.class);
            inputStream.close();
            return categories;
        } catch (IOException e) {
            return null;
        }
    }

}
