package com.sayed.anthropology.ui.concepts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.sayed.anthropology.data.repository.ConceptRepository;
import com.sayed.anthropology.databinding.FragmentConceptBinding;
import com.sayed.anthropology.model.Concept;

import java.util.List;
import java.util.stream.Collectors;

public class ConceptFragment extends Fragment {
    private ConceptViewModel conceptViewModel;
    ConceptRepository conceptRepository;
    ConceptAdapter conceptAdapter;
    FragmentConceptBinding binding;
    List<Concept> conceptList;

    public ConceptFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        conceptRepository = new ConceptRepository(requireActivity());
        this.conceptList = conceptRepository.findAll();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        conceptViewModel = new ViewModelProvider(this).get(ConceptViewModel.class);
        conceptAdapter = new ConceptAdapter(this);
        binding = FragmentConceptBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.conceptRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.conceptRv.setAdapter(conceptAdapter);
        if (conceptList != null) {
            conceptAdapter.setDataList(conceptList);
        }
        setOnClickListeners();
        return root;
    }

    private void setOnClickListeners() {
        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                conceptAdapter.setDataList(filteredConcepts(query));
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                conceptAdapter.setDataList(filteredConcepts(query));
                return false;
            }
        });
    }

    List<Concept> filteredConcepts(String query) {
        return this.conceptList.stream().filter(concept -> (concept.getTitle().contains(query) || concept.getText().contains(query))).collect(Collectors.toList());
    }

    @Override
    public void onPause() {
        super.onPause();
    }


}