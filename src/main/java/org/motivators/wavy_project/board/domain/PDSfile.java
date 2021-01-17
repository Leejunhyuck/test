package org.motivators.wavy_project.board.domain;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Getter
@Setter
@ToString
@Entity
@Table(name = "pdsfiles")
@EqualsAndHashCode(of="fno")
public class PDSfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fno;
    
    private String pdsfile;
}