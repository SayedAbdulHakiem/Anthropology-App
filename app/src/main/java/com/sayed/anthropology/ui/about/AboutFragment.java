package com.sayed.anthropology.ui.about;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sayed.anthropology.databinding.FragmentAboutBinding;

public class AboutFragment extends Fragment {

    private FragmentAboutBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        AboutViewModel aboutViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);

        binding = FragmentAboutBinding.inflate(inflater, container, false);
        binding.pdfUrl.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/file/d/1f8XLU1IhHCxTWjO4wNwPXPM6XSy_ok4c/view?usp=sharing"));
            startActivity(browserIntent);
        });

        StringBuilder supervision = new StringBuilder();
        supervision.append("تحت إشراف");
        supervision.append("\n");
        supervision.append("د/ نادية عبد القادر محمد");
        supervision.append("\n");
        supervision.append("مدرس الأنثروبولوجيا بالقسم");
        supervision.append("\n");
        supervision.append("2023 - 2024 ");
        binding.supervisionTv.setText(supervision.toString());

        StringBuilder names = new StringBuilder();

        names.append("إعداد كلاُ من :-");
        names.append("\n");
        names.append("آلاء حمدي حمدان حسن");
        names.append("\n");
        names.append("إسراء ماهر عبد الحميد عبيد");
        names.append("\n");
        names.append("آية أسامة إبراهيم محمود");
        names.append("\n");
        names.append("أمنية حسام الدين محمد السيد");
        names.append("\n");
        names.append("حبيبة تامر سليمان عبد العظيم");
        names.append("\n");
        names.append("حبيبة زكريا محمد طبانة");
        names.append("\n");
        names.append("داليا أحمد محمد عبده");
        names.append("\n");
        names.append("رحاب صابر يماني عبد الرسول");
        names.append("\n");
        names.append("رحمة عصمت أحمد عجمي");
        names.append("\n");
        names.append("رشا عماد راغب حسين");
        names.append("\n");
        names.append("ريهام عبد النبي سعد عبد النبي");
        names.append("\n");
        names.append("سماء أشرف عبد المنعم حافظ");
        names.append("\n");
        names.append("شهد أشرف العزب عبد الرشيد");
        names.append("\n");
        names.append("شمس حامد محمود محمد");
        names.append("\n");
        names.append("لبني عبد العزيز محمد عبد الغني");
        names.append("\n");
        names.append("مريم محمد عيسي أحمد");
        names.append("\n");
        binding.namesTv.setText(names.toString());

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}