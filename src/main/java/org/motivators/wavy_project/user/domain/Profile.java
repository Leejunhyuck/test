package org.motivators.wavy_project.user.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "profile")
@EqualsAndHashCode(of = "fno")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
  
    private String fname;
  
    private boolean current;
    
    @ManyToOne
    private Member member;

}