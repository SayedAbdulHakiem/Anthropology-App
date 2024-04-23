package com.sayed.anthropology.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.model.Article;

import java.io.IOException;
import java.io.InputStream;

public class AssetUtils {
    public static String LEVEL_1_IMAGES_PATH = "level_1/images/";
    public static String IMAGE_EXTENSION = ".jpg";

    public static String LEVEL_1_STORY_TEXT_PATH = "level_1/story_text/";
    public static String STORY_TEXT_EXTENSION = ".json";

    private static String getImagePath(String storyId) {
        return LEVEL_1_IMAGES_PATH + storyId + IMAGE_EXTENSION;
    }

    private static String getStoryTextPath(String storyId) {
        return LEVEL_1_STORY_TEXT_PATH + storyId + STORY_TEXT_EXTENSION;
    }

    public static Drawable getImageDrawable(Context context, String storyId) {
        if (!StringUtils.hasText(storyId)) {
            return null;
        }
        try {
            InputStream inputStream = context.getAssets().open(getImagePath(storyId));
            Drawable drawable = Drawable.createFromStream(inputStream, null);
            inputStream.close();
            return drawable;
        } catch (IOException e) {
            return null;
        }
    }

    public static Article getStoryFromJson(Context context, String storyId) {
        if (!StringUtils.hasText(storyId)) {
            return null;
        }
        try {
            InputStream inputStream = context.getAssets().open(getStoryTextPath(storyId));
            ObjectMapper objectMapper = new ObjectMapper();
            Article article = objectMapper.readValue(inputStream, Article.class);
            inputStream.close();
            return article;
        } catch (IOException e) {
            return null;
        }
    }

}
