package com.sayed.anthropology.data.repository;

import static com.sayed.anthropology.utils.AssetUtils.getAllConceptsFromJson;

import android.content.Context;

import com.sayed.anthropology.model.Concept;

import java.util.List;

public class ConceptRepository {
    static Context context;

    public ConceptRepository(Context context) {
        ConceptRepository.context = context;
    }

    public List<Concept> findAll() {
        List<Concept> conceptList = getAllConceptsFromJson(context);


        return conceptList;
    }

}