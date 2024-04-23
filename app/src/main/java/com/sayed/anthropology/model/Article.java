package com.sayed.anthropology.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Article implements Serializable {
    private String id;
    private String title;
    private String text;
    private List<MyDictionary> dictionaryList;
    private List<Question> questionList;

}
