package org.motivators.wavy_project.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyDto {
    private Long rno;
    private String replyText;
    private String replyer;
}