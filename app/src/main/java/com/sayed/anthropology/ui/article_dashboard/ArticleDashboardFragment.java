package com.sayed.anthropology.ui.article_dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sayed.anthropology.R;
import com.sayed.anthropology.data.StringUtils;
import com.sayed.anthropology.data.repository.ArticleRepository;
import com.sayed.anthropology.data.repository.CategoriesRepository;
import com.sayed.anthropology.databinding.FragmentArticleDashboardBinding;
import com.sayed.anthropology.model.Article;
import com.sayed.anthropology.model.ArticleCategory;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleDashboardFragment extends Fragment {
    ArticleAdapter articleAdapter;
    CategoryAdapter categoryAdapter;
    ArticleRepository articleRepository;
    CategoriesRepository categoriesRepository;
    private FragmentArticleDashboardBinding binding;
    ArticleDashboardViewModel articleDashboardViewModel;
    List<Article> articleList;
    List<ArticleCategory> articleCategoryList;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        articleDashboardViewModel = new ViewModelProvider(this).get(ArticleDashboardViewModel.class);
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
        articleCategoryList = categoriesRepository.findAll();
        categoryAdapter.setDataList(articleCategoryList);
        selectCategory(articleDashboardViewModel.getSelectedCategory().getValue());
        setOnClickListener();
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setOnClickListener() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                if (StringUtils.hasText(query)){
                    articleAdapter.setDataList(filteredArticles(query));
                }
                return false;
            }
        });
    }

    List<Article> filteredArticles(String query) {
        this.articleList = articleRepository.findAll();
        return this.articleList.stream().filter(concept -> (concept.getTitle().contains(query) || concept.getText().contains(query))).collect(Collectors.toList());
    }

    public void selectCategory(ArticleCategory articleCategory) {
        if (articleCategory == null) {
            articleCategory = articleCategoryList.get(0);
        }
        articleDashboardViewModel.getSelectedCategory().setValue(articleCategory);
        categoryAdapter.setSelectedItem(articleCategory);
        this.articleList = articleRepository.getArticlesByCategory(articleCategory);
        articleAdapter.setDataList(this.articleList);
    }

    public void openArticleFragment(Article article) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("article", article);
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.navigation_article, bundle);
    }
}