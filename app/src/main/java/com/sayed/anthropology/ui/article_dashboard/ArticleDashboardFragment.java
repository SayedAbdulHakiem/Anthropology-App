package com.sayed.anthropology.ui.article_dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sayed.anthropology.R;
import com.sayed.anthropology.data.repository.CategoriesRepository;
import com.sayed.anthropology.data.repository.ArticleRepository;
import com.sayed.anthropology.databinding.FragmentArticleDashboardBinding;
import com.sayed.anthropology.model.Article;

public class ArticleDashboardFragment extends Fragment {
    ArticleAdapter articleAdapter;
    CategoryAdapter categoryAdapter;
    ArticleRepository articleRepository;
    CategoriesRepository categoriesRepository;
    private FragmentArticleDashboardBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DashboardViewModel dashboardViewModel = new ViewModelProvider(this).get(DashboardViewModel.class);

        articleRepository = new ArticleRepository(this.requireActivity());
        categoriesRepository = new CategoriesRepository(this.requireActivity());
        binding = FragmentArticleDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        articleAdapter = new ArticleAdapter(this);
        categoryAdapter = new CategoryAdapter(this);
        binding.articleRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.articleRv.setAdapter(articleAdapter);
        binding.categoryRv.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        binding.categoryRv.setAdapter(categoryAdapter);
        articleAdapter.setDataList(articleRepository.findAll());
        categoryAdapter.setDataList(categoriesRepository.findAll());
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void openStoryFragment(Article article) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("article", article);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_story, bundle);
    }
}