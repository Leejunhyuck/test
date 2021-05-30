package com.raccoon.blog.user.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "user_roles")
@EqualsAndHashCode(of="fno")
@ToString
public class MemberRole {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long fno;
    private String roleName;

}