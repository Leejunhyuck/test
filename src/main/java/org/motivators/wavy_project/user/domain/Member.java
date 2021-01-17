package org.motivators.wavy_project.user.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Entity
@Table(name = "members")
@EqualsAndHashCode(of = "uid")
@ToString
@NoArgsConstructor
public class Member {
  @Id
  private String uid;

  private String password;

  private String uname;

  @CreationTimestamp
  private Timestamp regdate;

  @UpdateTimestamp
  private Timestamp updatedate;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "uid")
  private List<MemberRole> roles;


  @Builder
  Member(String uid, String password, String uname, List<MemberRole> roles){
    this.uid = uid;
    this.password = password;
    this.roles = roles;
  }

}