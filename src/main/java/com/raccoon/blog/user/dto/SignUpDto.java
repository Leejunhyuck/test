package com.raccoon.blog.user.dto;

import com.raccoon.blog.user.domain.MemberRole;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Setter
@Getter
public class SignUpDto {
    private String id;
    private String password;
    private String name;
    private List<MemberRole> roles;
}
