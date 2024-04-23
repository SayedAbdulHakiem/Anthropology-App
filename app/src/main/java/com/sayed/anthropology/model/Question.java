package com.sayed.anthropology.model;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Question implements Serializable {
    private String storyId;
    private String id;
    private String text;
    private List<Answer> answerList;
    private Boolean answerStatus;
}
