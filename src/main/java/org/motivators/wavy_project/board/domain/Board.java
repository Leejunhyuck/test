package org.motivators.wavy_project.board.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@Table(name= "webboards")
@EqualsAndHashCode(of ="bno")
@ToString
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    private String title;

    private String writer;

    private String content;

    @CreationTimestamp
    private Timestamp regdate;
    
    @UpdateTimestamp
    private Timestamp updatedate;
    
    @OneToMany(mappedBy ="board", fetch =FetchType.LAZY)
    private List<Reply> replies;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="pdsno")
    private List<PDSfile> files;


    @Builder
    Board(Long bno, String title, String writer, String content, List<Reply> replies){
        this.bno = bno;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.replies = replies;
    }

    public void modifyBoard (String title, String writer, String content){
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
    
}