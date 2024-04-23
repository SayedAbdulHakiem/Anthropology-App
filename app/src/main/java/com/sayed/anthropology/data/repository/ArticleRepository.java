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
        return AssetUtils.getStoryFromJson(context, articleId);
    }

//    public static Story buildMockStory(String id) {
//        Story story = new Story();
//
//        story.setId(id);
//        story.setTitle("A Day at the Park");
//        story.setText("One beautiful morning, Emma decided to take her playful dog, Max, to the local park. The sun was shining brightly, making the day perfect for outdoor activities. As they entered the park, they were greeted by the vibrant colors of the flowers—roses, tulips, and daisies spread a blanket of red, yellow, blue, and white across the green landscape. Emma admired the roses the most, their fragrance filling the air. Max, with his boundless energy, started chasing butterflies, darting back and forth with excitement. He even tried to befriend a few birds, but they flew away, chirping. Emma laughed at Max's antics, feeling a sense of joy watching her dog enjoy the freedom of the park. They found a serene spot under a large oak tree, where Emma unpacked a picnic. She shared her cheese sandwich with Max, who wagged his tail in appreciation. After lunch, they played fetch with a bright blue ball, which Max retrieved tirelessly.");
//
//        List<Dictionary> dictionaryList = new ArrayList<>();
//        dictionaryList.add(new Dictionary(id, "1", "Beautiful", "جميل"));
//        dictionaryList.add(new Dictionary(id, "2", "Morning", "صباح"));
//        dictionaryList.add(new Dictionary(id, "3", "Playful", "مرح"));
//        dictionaryList.add(new Dictionary(id, "4", "Dog", "كلب"));
//        dictionaryList.add(new Dictionary(id, "5", "Local", "محلي"));
//        dictionaryList.add(new Dictionary(id, "6", "Park", "حديقة"));
//        dictionaryList.add(new Dictionary(id, "7", "Sun", "شمس"));
//        dictionaryList.add(new Dictionary(id, "8", "Shining", "مشرق"));
//        dictionaryList.add(new Dictionary(id, "9", "Flowers", "زهور"));
//        dictionaryList.add(new Dictionary(id, "10", "Roses", "ورود"));
//        dictionaryList.add(new Dictionary(id, "11", "Tulips", "زنابق"));
//        dictionaryList.add(new Dictionary(id, "12", "Daisies", "بابونج"));
//        dictionaryList.add(new Dictionary(id, "13", "Fragrance", "عطر"));
//        dictionaryList.add(new Dictionary(id, "14", "Chasing", "مطاردة"));
//        dictionaryList.add(new Dictionary(id, "15", "Butterflies", "فراشات"));
//        dictionaryList.add(new Dictionary(id, "16", "Birds", "طيور"));
//        dictionaryList.add(new Dictionary(id, "17", "Oak tree", "شجرةالبلوط"));
//        dictionaryList.add(new Dictionary(id, "18", "Cheese sandwich", "ساندويتش الجبن"));
//        dictionaryList.add(new Dictionary(id, "19", "Wagged", "هز"));
//        dictionaryList.add(new Dictionary(id, "20", "Fetch", "جلب"));
//        dictionaryList.add(new Dictionary(id, "21", "Blue ball", "كرة زرقاء"));
//        dictionaryList.add(new Dictionary(id, "22", "Afternoon", "بعد الظهر"));
//        dictionaryList.add(new Dictionary(id, "23", "Content", "راضي"));
//        dictionaryList.add(new Dictionary(id, "24", "Loyal", "وفي"));
//        dictionaryList.add(new Dictionary(id, "25", "Friendship", "صداقة"));
//        dictionaryList.add(new Dictionary(id, "26", "Vibrant", "نابض بالحياة"));
//        dictionaryList.add(new Dictionary(id, "27", "Landscape", "منظر طبيعي"));
//        dictionaryList.add(new Dictionary(id, "28", "Antics", "تصرفات مضحكة"));
//        dictionaryList.add(new Dictionary(id, "29", "Serene", "هادئ"));
//        dictionaryList.add(new Dictionary(id, "30", "Joy", "فرح"));
//        story.setDictionaryList(dictionaryList);
//
//        List<Question> questionList = new ArrayList<>();
//        questionList.add(new Question(id, "1", "What time of day does the story take place?", Arrays.asList(new Answer("1", "A", "In the evening", false), new Answer("1", "B", "At night", false), new Answer("1", "C", "In the morning", true)),false));
//
//        questionList.add(new Question(id, "2", "What did Emma decide to do with Max?", Arrays.asList(new Answer("2", "A", "Go swimming", false), new Answer("2", "B", "Visit the local park", true), new Answer("2", "C", "Watch a movie", false)),false));
//
//        questionList.add(new Question(id, "3", "How was the weather described in the story?", Arrays.asList(new Answer("3", "A", "Cloudy and dark", false), new Answer("3", "B", "Rainy and cold", false), new Answer("3", "C", "Sunny and bright", true)),false));
//
//        questionList.add(new Question(id, "4", "Which flowers are mentioned in the story?", Arrays.asList(new Answer("4", "A", "Roses, tulips, and daisies", true), new Answer("4", "B", "Lilies, orchids, and sunflowers", false), new Answer("4", "C", "Carnations, peonies, and lavender", false)),false));
//
//        questionList.add(new Question(id, "5", "What did Max do in the park?", Arrays.asList(new Answer("5", "A", "Slept under a tree", false), new Answer("5", "B", "Chased butterflies and tried to befriend birds", true), new Answer("5", "C", "Dug holes in the ground", false)),false));
//
//        questionList.add(new Question(id, "6", "Where did Emma and Max decide to relax and eat?", Arrays.asList(new Answer("6", "A", "By the lake", false), new Answer("6", "B", "Under a large oak tree", true), new Answer("6", "C", "On a park bench", false)),false));
//
//        questionList.add(new Question(id, "7", "What did Emma share with Max?", Arrays.asList(new Answer("7", "A", "A cheese sandwich", true), new Answer("7", "B", "A piece of cake", false), new Answer("7", "C", "An apple", false)),false));
//
//        questionList.add(new Question(id, "8", "What game did Emma and Max play after lunch?", Arrays.asList(new Answer("8", "A", "Tag", false), new Answer("8", "B", "Hide and seek", false), new Answer("8", "C", "Fetch with a bright blue ball", true)),false));
//
//        questionList.add(new Question(id, "9", "How did Max react to the game of fetch?", Arrays.asList(new Answer("9", "A", "He was uninterested", false), new Answer("9", "B", "He retrieved the ball tirelessly", true), new Answer("9", "C", "He fell asleep", false)),false));
//
//        questionList.add(new Question(id, "10", "What emotion did Emma feel watching Max in the park?", Arrays.asList(new Answer("10", "A", "Annoyance", false), new Answer("10", "B", "Joy", true), new Answer("10", "C", "Sadness", false)),false));
//        story.setQuestionList(questionList);
//        return story;
//    }
}