package com.sayed.anthropology.ui.pdf_view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sayed.anthropology.databinding.FragmentPdfViewBinding;
import com.sayed.anthropology.ui.about.AboutViewModel;
import com.sayed.anthropology.utils.AppUtils;
import com.sayed.anthropology.utils.AssetUtils;

import java.io.IOException;
import java.io.InputStream;

public class PdfViewFragment extends Fragment {

    private PdfViewViewModel mViewModel;

    FragmentPdfViewBinding binding;
    InputStream pdfInputStream;
    int lastPage = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AboutViewModel aboutViewModel =
                new ViewModelProvider(this).get(AboutViewModel.class);

        lastPage = AppUtils.getInstance().getLastPageViewed();
        binding = FragmentPdfViewBinding.inflate(inflater, container, false);
        pdfInputStream = AssetUtils.getPdf(requireActivity(), "graduation_book");
        binding.pdfView.useBestQuality(true);
        if (pdfInputStream != null) {
            binding.pdfView.fromStream(pdfInputStream)
                    .defaultPage(lastPage).onRender((pages, pageWidth, pageHeight) -> {
                        binding.pdfView.fitToWidth(lastPage);
                        lastPage = pages;
                    }).load();
        }


        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onDestroy() {
        try {
            AppUtils.getInstance().saveLastPageViewed(binding.pdfView.getCurrentPage());
            pdfInputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onDestroy();
    }
}