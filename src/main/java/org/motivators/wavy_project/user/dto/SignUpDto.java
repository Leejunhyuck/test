package org.motivators.wavy_project.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.motivators.wavy_project.user.domain.MemberRole;
import java.util.List;

@Setter
@Getter
public class SignUpDto {
    private String id;
    private String password;
    private String name;
    private List<MemberRole> roles;
}