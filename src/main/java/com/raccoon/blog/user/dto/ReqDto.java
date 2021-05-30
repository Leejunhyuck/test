package com.raccoon.blog.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;


@AllArgsConstructor
@ToString
@Getter
public class ReqDto {
    private String name;
    private String token;
}