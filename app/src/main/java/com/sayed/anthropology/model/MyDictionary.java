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
public class MyDictionary implements Serializable {
    private String storyId;
    private String id;
    private String textEn;
    private String textAr;

}
