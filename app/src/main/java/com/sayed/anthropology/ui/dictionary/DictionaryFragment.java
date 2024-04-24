package com.sayed.anthropology.ui.dictionary;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sayed.anthropology.R;
import com.sayed.anthropology.data.repository.ArticleRepository;
import com.sayed.anthropology.databinding.FragmentDictionaryBinding;
import com.sayed.anthropology.model.MyDictionary;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.ui.article.ArticleViewModel;

import java.util.List;
import java.util.Locale;

public class DictionaryFragment extends Fragment {

    private ArticleViewModel articleViewModel;
    DictionaryAdapter dictionaryAdapter;
    FragmentDictionaryBinding binding;
    Article article;
    List<MyDictionary> dictionaryList;
    Bundle bundle;
    TextToSpeech textToSpeech;


    public DictionaryFragment() {
        this.article = new Article();
    }

    public DictionaryFragment(Article article) {
        this.article = article;
    }

    public static DictionaryFragment newInstance(Article article) {
        return new DictionaryFragment(article);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundle = getArguments();
            if (bundle != null && bundle.containsKey("story")) {
                this.article = (Article) bundle.get("story");
            }
        } else {
            this.article = ArticleRepository.getArticleFromAsset("level_1_dictionary");
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        dictionaryAdapter = new DictionaryAdapter(this);
        binding = FragmentDictionaryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.dictionaryRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.dictionaryRv.setAdapter(dictionaryAdapter);
        if (article != null) {
//            dictionaryAdapter.setDataList(article.getDictionaryList());
            binding.storyTitleTv.setText(this.article.getTitle());
        } else {
            binding.storyTitleTv.setVisibility(View.GONE);
        }
        initTextToSpeechEnglish();

        return root;
    }

    private void initTextToSpeechEnglish() {
        textToSpeech = new TextToSpeech(requireActivity(), i -> {
            if (i == TextToSpeech.SUCCESS) {
                int result = textToSpeech.setLanguage(Locale.US);
                textToSpeech.setSpeechRate(0.6f);
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(requireActivity(), getString(R.string.not_supported_language), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(requireActivity(), getString(R.string.text_to_speech_initialization_failed), Toast.LENGTH_LONG).show();
            }
        });


    }

    private void initTextToSpeechArabic() {
        textToSpeech = new TextToSpeech(requireActivity(), i -> {
            if (i == TextToSpeech.SUCCESS) {
                Locale arLocale = new Locale("ar");
                int result = textToSpeech.setLanguage(arLocale);
                if (result == TextToSpeech.SUCCESS) {
                    /* over API 21 */
                    String voiceName = arLocale.toLanguageTag();
                    Voice voice = new Voice(voiceName, arLocale, Voice.QUALITY_HIGH, Voice.LATENCY_HIGH, false, null);
                    textToSpeech.setVoice(voice);
                } else if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(requireActivity(), getString(R.string.not_supported_language), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(requireActivity(), getString(R.string.text_to_speech_initialization_failed), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void startSpeakText(String text) {
        if (textToSpeech == null) {
            return;
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, "speechId");
    }


    public void speak(MyDictionary myDictionary) {
        startSpeakText(myDictionary.getTextEn());
//        startSpeakText(myDictionary.getTextAr());
    }


    @Override
    public void onPause() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onPause();
    }


}