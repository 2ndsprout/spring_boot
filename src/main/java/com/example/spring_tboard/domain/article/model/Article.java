package com.example.spring_tboard.domain.article.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private int id;
    private String title;
    private String body;
    private int hit;
    private String regDate;

    public void increaseHit () {
        hit++;
    }
}
