package com.sayed.anthropology.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ArticleCategory implements Serializable {
    private String id;
    private String title;
    private String text;
}
