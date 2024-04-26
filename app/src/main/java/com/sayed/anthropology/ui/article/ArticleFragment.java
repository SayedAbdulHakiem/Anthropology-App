package com.sayed.anthropology.ui.article;

import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.speech.tts.Voice;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sayed.anthropology.R;
import com.sayed.anthropology.databinding.FragmentArticleBinding;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.utils.AssetUtils;

import java.util.Locale;

public class ArticleFragment extends Fragment {

    private ArticleViewModel articleViewModel;

    FragmentArticleBinding binding;
    Article article;
    Bundle bundle;
    TextToSpeech textToSpeech;

    private int currentPosition = 0;
    private String spokenText = "";
    private int spokenLength = 0;
    SpannableString spannableString;
    ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.YELLOW);
    StyleSpan boldSpan = new StyleSpan(Typeface.BOLD_ITALIC);

    public ArticleFragment() {
        this.article = new Article();
    }

    public ArticleFragment(Article article) {
        this.article = article;
    }

    public static ArticleFragment newInstance(Article article) {
        return new ArticleFragment(article);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            bundle = getArguments();
            if (bundle != null && bundle.containsKey("article")) {
                this.article = (Article) bundle.get("article");
            }
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        articleViewModel = new ViewModelProvider(this).get(ArticleViewModel.class);
        binding = FragmentArticleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        if (article != null) {
            setArticleData(article);
        }
        setOnClickListeners();
        initTextToSpeechArabic();
//        initTextToSpeechEnglish();

        return root;
    }

    private void setOnClickListeners() {
        binding.playIcon.setOnClickListener(view -> {
            handlePlayPauseBtn();
        });
        binding.articleAudioSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    // calculate progress and start speech from special part
                    // currentPosition = ( progress * spokenLength) / maximum
                    currentPosition = (progress * spokenLength) / binding.articleAudioSeekbar.getMax();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseSpeech();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                resumeSpeech();
            }
        });

        binding.testBtn.setOnClickListener(view -> {
            openArticleTestFragment(this.article);
        });
        binding.dictionaryBtn.setOnClickListener(view -> {
            openConceptsFragment(this.article);
        });
    }

    private void setArticleData(Article article) {
        Drawable imageDrawable = AssetUtils.getImageDrawable(requireActivity(), article.getId());
        if (imageDrawable == null) {
            binding.articleImageCardview.setVisibility(View.GONE);
        } else {
            binding.articleImageCardview.setVisibility(View.VISIBLE);
            binding.articleImage.setImageDrawable(imageDrawable);
        }
        binding.titleTv.setText(article.getTitle());
        binding.textTv.setText(article.getText());
        spokenText = article.getText();
        spokenLength = spokenText.length();
        spannableString = new SpannableString(spokenText);
    }

    private void initTextToSpeechEnglish() {
        textToSpeech = new TextToSpeech(requireActivity(), i -> {
            if (i == TextToSpeech.SUCCESS) {
                Locale locale = new Locale("en");
                int result = textToSpeech.setLanguage(locale);
                textToSpeech.setSpeechRate(0.6f);
                String voiceName = locale.toLanguageTag();
                Voice voice = new Voice(voiceName, locale, Voice.QUALITY_HIGH, Voice.LATENCY_HIGH, false, null);
                textToSpeech.setVoice(voice);
                handleSpeechSeekBar();
                startSpeakText(" Sayed Text To Specch");
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
                Locale locale = Locale.forLanguageTag("ar");
                int result = textToSpeech.setLanguage(locale);
                if (result == TextToSpeech.SUCCESS) {
                    /* over API 21 */
                    String voiceName = locale.toLanguageTag();
                    Voice voice = new Voice(voiceName, locale, Voice.QUALITY_HIGH, Voice.LATENCY_HIGH, false, null);
                    textToSpeech.setVoice(voice);
                    handleSpeechSeekBar();
                } else if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    Toast.makeText(requireActivity(), getString(R.string.not_supported_language), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(requireActivity(), getString(R.string.text_to_speech_initialization_failed), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void handlePlayPauseBtn() {
        if (textToSpeech.isSpeaking()) {
            pauseSpeech();
        } else if (currentPosition == 0 || currentPosition == spokenLength - 1) {
            // startSpeakText(article.getTitle());
            startSpeakText(spokenText);
        } else {
            resumeSpeech();
        }
    }

    private void startSpeakText(String text) {
        if (textToSpeech == null) {
            return;
        }
        binding.playIcon.setImageDrawable(ContextCompat.getDrawable(requireActivity(), android.R.drawable.ic_media_pause));
        currentPosition = 0;
        textToSpeech.speak(text, TextToSpeech.QUEUE_ADD, null, "speechId");
    }

    private void pauseSpeech() {
        if (textToSpeech == null) {
            return;
        }
        binding.playIcon.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.speak));
        if (textToSpeech.isSpeaking()) {
            textToSpeech.stop();
        }
    }

    private void resumeSpeech() {
        if (textToSpeech == null) {
            return;
        }
        binding.playIcon.setImageDrawable(ContextCompat.getDrawable(requireActivity(), android.R.drawable.ic_media_pause));
        String remainingText = spokenText.substring(currentPosition);
        textToSpeech.speak(remainingText, TextToSpeech.QUEUE_FLUSH, null, "speechId");
    }

    private void handleSpeechSeekBar() {

        binding.articleAudioSeekbar.setMax(spokenLength);
        textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            @Override
            public void onStart(String s) {
                Toast.makeText(requireActivity(), "OnStart" + getString(R.string.not_supported_language), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDone(String s) {
                binding.articleAudioSeekbar.setProgress(0);
                currentPosition = 0;
            }

            @Override
            public void onError(String s) {
                Toast.makeText(requireActivity(), "OnError" + s, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onRangeStart(String utteranceId, int start, int end, int frame) {
                //super.onRangeStart(utteranceId, start, end, frame);
                // Calculate the progress based on the start and end indices
                spannableString.setSpan(boldSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                binding.textTv.setText(spannableString);
                currentPosition += (end - start);
                // progress(%) = (currentPosition / length) * maximum
                binding.articleAudioSeekbar.setProgress((int) (((float) currentPosition / (float) spokenLength) * binding.articleAudioSeekbar.getMax()));
            }
        });
    }

    public void openArticleTestFragment(Article article) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("article", article);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
//        navController.navigate(R.id.navigation_article_test, bundle);
    }

    public void openConceptsFragment(Article article) {
        Bundle bundle = new Bundle();
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_concept, bundle);
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