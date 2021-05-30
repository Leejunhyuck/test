package com.raccoon.blog.Board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
    private Long bno;
    private String title;
    private String content;
    private String writer;
}