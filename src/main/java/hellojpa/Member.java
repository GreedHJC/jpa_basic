package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * description
 *
 * @author : jcHwang
 */
@Entity
public class Member extends BaseEntity{
  @Id
  @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id;

  @Column(name = "USERNAME")
  private String username;

  @ManyToOne
  @JoinColumn(name = "TEAM_ID", insertable = false, updatable = false)
  private Team team;

  @OneToOne
  @JoinColumn(name ="LOCKER_ID")
  private Locker locker;

  @OneToMany(mappedBy = "member")
  private List<MemberProduct> memberProducts = new ArrayList<>();

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}

// 4 엔티티 매핑
//@Entity(name = "Member")
////@Table(name = "USER") /*테이블이 다르면 애노테이션 추가*/
//@SequenceGenerator(
//    name = "MEMBER_SEQ_GENERATOR",
//    sequenceName = "MEMBER_SEQ", //매핑할 데이터베이스 시퀀스 이름
//    initialValue = 1, allocationSize = 1)
//public class Member {
//
//
//  //  @Column(name = "username") /*컬럼명이 다를때는 이 애노테이션추가*/
//  //  @Column(unique = true, length = 10)   // unique 필수값, length 크기
//
//  //  @Id   //기본키 매핑
//  //  @GeneratedValue(strateg = GenerationType.AUTO)
//  // IDENTITY: 데이터베이스에 위임, MYSQL
//  // SEQUENCE: 데이터베이스 시퀀스 오브젝트 사용, ORACLE
//  // @SequenceGenerator 필요
//  // TABLE: 키 생성용 테이블 사용, 모든 DB에서 사용
//  // @TableGenerator 필요
//  // AUTO: 방언에 따라 자동 지정, 기본값
//  @Id
//  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ_GENERATOR")
//  @Entity
//  public class Member {
//    @Id
//    @GeneratedValue
//    @Column(name = "MEMBER_ID")
//    private Long id;
//
//    @Column(name = "USERNAME")
//    private String name;
//
//    @Column(name = "TEAM_ID")
//    private Long teamId;
//  }

