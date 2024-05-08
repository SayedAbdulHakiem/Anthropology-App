package com.sayed.anthropology.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.model.Categories;
import com.sayed.anthropology.model.Concept;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

public class AssetUtils {
    public static String IMAGES_PATH = "images/";
    public static String IMAGE_EXTENSION = ".jpg";
    public static String ARTICLE_TEXT_PATH = "articles/";
    public static String ARTICLES_TEXT_FULL_PATH = "articles/articles.json";
    public static String CONCEPTS_TEXT_FULL_PATH = "concepts/concepts.json";
    public static String ARTICLE_TEXT_EXTENSION = ".json";
    public static String ARTICLE_CATEGORIES_FULL_PATH = "categories/article_categories.json";

    public static String PDF_PATH = "pdf/";
    public static String PDF_EXTENSION = ".pdf";

    private static String getImagePath(String articleId) {
        return IMAGES_PATH + articleId + IMAGE_EXTENSION;
    }

    public static String getPdfPath(String bookId) {
        return PDF_PATH + bookId + PDF_EXTENSION;
    }
    private static String getArticleTextPath(String articleId) {
        return ARTICLE_TEXT_PATH + articleId + ARTICLE_TEXT_EXTENSION;
    }

    private static String getArticlesPath() {
        return ARTICLES_TEXT_FULL_PATH;
    }

    private static String getConceptsPath() {
        return CONCEPTS_TEXT_FULL_PATH;
    }

    private static String getArticleCategoriesTextPath() {
        return ARTICLE_CATEGORIES_FULL_PATH;
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

    public static InputStream getPdf(Context context, String bookId) {
        if (!StringUtils.hasText(bookId)) {
            return null;
        }
        try {
            return context.getAssets().open(getPdfPath(bookId));
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

    public static List<Article> getAllArticlesFromJson(Context context) {

        try {
            InputStream inputStream = context.getAssets().open(getArticlesPath());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Article> articles = Arrays.asList(objectMapper.readValue(inputStream, Article[].class));
            inputStream.close();
            return articles;
        } catch (
                IOException e) {
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

    public static List<Concept> getAllConceptsFromJson(Context context) {

        try {
            InputStream inputStream = context.getAssets().open(getConceptsPath());
            ObjectMapper objectMapper = new ObjectMapper();
            List<Concept> conceptList = Arrays.asList(objectMapper.readValue(inputStream, Concept[].class));
            inputStream.close();
            return conceptList;
        } catch (
                IOException e) {
            return null;
        }

    }
}
