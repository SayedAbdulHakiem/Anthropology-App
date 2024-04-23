package com.sayed.anthropology.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Answer implements Serializable {
    private String questionId;
    private String id;
    private String text;
    private boolean correct;
    private Boolean selected;
}
