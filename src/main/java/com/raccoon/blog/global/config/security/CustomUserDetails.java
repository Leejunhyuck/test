package com.raccoon.blog.global.config.security;

import com.raccoon.blog.user.domain.Member;
import com.raccoon.blog.user.domain.MemberRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

@Getter
public class CustomUserDetails extends User {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String ROLE_PREFIX = "ROLE_";

    private Member member;

    public CustomUserDetails(Member member) {

        super(member.getUid(), member.getPassword(), makeGrantedAuthority(member.getRoles()));
        this.member = member;

    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {

        List<GrantedAuthority> list = new ArrayList<>();

        roles.forEach(role -> list.add(new SimpleGrantedAuthority(ROLE_PREFIX + role.getRoleName())));

        return list;
    }

}