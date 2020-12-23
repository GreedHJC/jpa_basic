package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;

/**
 * description
 *
 * @author : jcHwang
 */
public class JpaMain {
  public static void main(String[] args) {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
    //1차 캐시(영속성 컨텍스트)
    EntityManager em = emf.createEntityManager();

    EntityTransaction tx = em.getTransaction();
    // 엔티티 매니저는 데이터 변경시 트랙잭션을 시작해야한다.
    tx.begin();   // [트랜잭션] 시작

    try {
      //프록시와 연관관계 관리 - 3.즉시로딩과 지연로딩
      Child child1 = new Child();
      Child child2 = new Child();

      Parent parent = new Parent();
      parent.addChild(child1);
      parent.addChild(child2);

      em.persist(parent);

      em.flush();
      em.clear();

      Parent findParent = em.find(Parent.class, parent.getId());
      findParent.getChildList().remove(0);

      //프록시와 연관관계 관리 - 2.즉시로딩과 지연로딩
      /*Team team = new Team();
      team.setName("teamA");
      em.persist(team);

      Member member1 = new Member();
      member1.setUsername("member1");
      member1.setTeam(team);
      em.persist(member1);

      em.flush();
      em.clear();

      Member m = em.find(Member.class, member1.getId());

      System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());

      System.out.println("=====================");
      System.out.println("m.getTeam().getName(); = " + m.getTeam().getName());  //초기화 발생
      System.out.println("=====================");*/


    //프록시와 연관관계 관리 - 1.프록시
// • 프록시 인스턴스의 초기화 여부 확인 PersistenceUnitUtil.isLoaded(Object entity)
// • 프록시 클래스 확인 방법 entity.getClass().getName() 출력(..javasist.. or HibernateProxy…)
// • 프록시 강제 초기화 org.hibernate.Hibernate.initialize(entity);
// • 참고: JPA 표준은 강제 초기화 없음 강제 호출: member.getName()
      /*Member member1 = new Member();
      member1.setUsername("member1");
      em.persist(member1);

      em.flush();
      em.clear();

      Member refMember = em.getReference(Member.class, member1.getId());
      System.out.println("refMember = " + refMember.getClass());    //Proxy
//      refMember.getUsername();  // 강제 초기화
      // • 프록시 강제 초기화 org.hibernate.Hibernate.initialize(entity);
      Hibernate.initialize(refMember);  // 강제초기화*/

// • 프록시 인스턴스의 초기화 여부 확인 PersistenceUnitUtil.isLoaded(Object entity)
//      System.out.println("isLoaded = " + emf.getPersistenceUnitUtil().isLoaded(refMember));

//• 영속성 컨텍스트의 도움을 받을 수 없는 준영속 상태일 때, 프록시를 초기화하면 문제 발생
//  (하이버네이트는 org.hibernate.LazyInitializationException 예외를 터트림)
      /*Member member1 = new Member();
      member1.setUsername("member1");
      em.persist(member1);

      em.flush();
      em.clear();

      Member refMember = em.getReference(Member.class, member1.getId());
      System.out.println("refMember = " + refMember.getClass());    //Proxy

//      em.detach(refMember);
//      em.close();
      em.clear();
      
      refMember.getUsername();*/

//• 영속성 컨텍스트에 찾는 엔티티가 이미 있으면 em.getReference()를 호출해도 실제 엔티티 반환
      /*Member member1 = new Member();
      member1.setUsername("member1");
      em.persist(member1);

      em.flush();
      em.clear();

      Member refMember = em.getReference(Member.class, member1.getId());
      System.out.println("refMember = " + refMember.getClass());    //Proxy

      Member findMember = em.find(Member.class, member1.getId());
      System.out.println("findMember = " + findMember.getClass());  //Member

      System.out.println("refMember == findMember: " + (refMember == findMember));*/

// 프록시 객체는 원본 엔티티를 상속받음, 따라서 타입 체크시 주의해야함 (== 비교 실패, 대신 instance of 사용)
      /*Member member1 = new Member();
      member1.setUsername("member1");
      em.persist(member1);


      em.flush();
      em.clear();

      Member m1 = em.find(Member.class, member1.getId());
      System.out.println("m1 = " + m1.getClass());

      Member reference = em.getReference(Member.class, member1.getId());
      System.out.println("reference.getClass() = " + reference.getClass());

      System.out.println("a == a: " + (m1 == reference));*/

      //• em.find() vs em.getReference()
      //• em.find(): 데이터베이스를 통해서 실제 엔티티 객체 조회
      //• em.getReference(): 데이터베이스 조회를 미루는 가짜(프록시) 엔티티 객체 조회
      /*      Member member1 = new Member();
      member1.setUsername("member1");
      em.persist(member1);

      Member member2 = new Member();
      member2.setUsername("member2");
      em.persist(member2);

      em.flush();
      em.clear();

      // 프록시 TYPE비교 TRUE
      Member m1 = em.getReference(Member.class, member1.getId());
      Member m2 = em.getReference(Member.class, member2.getId());
      System.out.println("m1 == m2 : " + (m1.getClass() == m2.getClass()));

      //프록시 TYPE비교 FALSE 그래서 == 비교는안되고 instanceof로 비교해야함
      Member m3 = em.find(Member.class, member1.getId());
      Member m4 = em.getReference(Member.class, member2.getId());
      System.out.println("m3 == m4 : " + (m3.getClass() == m4.getClass()));
      System.out.println("m3 == m4 : " + (m3 instanceof Member));
      System.out.println("m3 == m4 : " + (m4 instanceof Member));*/

      //      System.out.println("findMember.getId() = " + findMember.getId());
      //      System.out.println("before findMember = " + findMember.getClass());
      //      System.out.println("findMember.getUsername() = " + findMember.getUsername());
      //      System.out.println("after findMember = " + findMember.getClass());



      tx.commit();  // [트랜잭션] 커밋
    } catch (Exception e) {
      tx.rollback();
      e.printStackTrace();
    } finally {
      em.close();
    }
    emf.close();

  }

  private static void pritMember(Member member) {
    System.out.println("member = " + member.getUsername());
  }

  private static void pritMemberAndTeam(Member member) {
    String username = member.getUsername();
    System.out.println("username = " + username);

    Team team = member.getTeam();
    System.out.println("team.getName() = " + team.getName());


  }

}

//7강 고급매핑
//public class JpaMain {
//  public static void main(String[] args) {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//    //1차 캐시(영속성 컨텍스트)
//    EntityManager em = emf.createEntityManager();
//
//    EntityTransaction tx = em.getTransaction();
//    // 엔티티 매니저는 데이터 변경시 트랙잭션을 시작해야한다.
//    tx.begin();   // [트랜잭션] 시작
//
//
//    try {
//      Member member = new Member();
//      member.setCreateBy("Kim");
//      member.setCreateDate(LocalDateTime.now());
//
//      em.persist(member);
//
//      em.flush();
//      em.clear();
//
//      Movie findMovie = em.find(Movie.class, member.getId());
//      System.out.println("findMovie = " + findMovie);
//
//      tx.commit();  // [트랜잭션] 커밋
//    } catch (Exception e) {
//      tx.rollback();
//    } finally {
//      em.close();
//    }
//    emf.close();
//
//  }
//
//  @NotNull
//  private static Member saveMember(EntityManager em) {
//    Member member = new Member();
//    member.setUsername("member1");
//
//    em.persist(member);
//    return member;
//  }
//
//}

// 4 엔티티 매핑
//public class JpaMain {
//  public static void main(String[] args) {
//    EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//
//    //1차 캐시(영속성 컨텍스트)
//    EntityManager em = emf.createEntityManager();
//
//    EntityTransaction tx = em.getTransaction();
//    // 엔티티 매니저는 데이터 변경시 트랙잭션을 시작해야한다.
//    tx.begin();   // [트랜잭션] 시작
//
//    try {
//
//
//      // 준영속상태로 만드는방법
//Member member = em.find(Member.class, 150L);
//
//      //특정 엔티티만 준영속 상태로 전환
//      em.detach(member);
//
//      //영속성 컨텍스트를 완전히 초기화
//      em.clear();
//
//      // 영속성 컨텍스트를 종료
//      em.close();
//
//
//      System.out.println("====================");
//
//
//
//Member member = new Member(200L, "member200");
//      em.persist(member);
//
//      // 영속성 컨텍스트(1차캐시)를 비우지는 않고 데이터베이스와 동기화(커밋됨)
//      em.flush();
//
//
//      //트랙잭션에 담아 뒀다가 한번에 저장
//Member member1 = new Member(150L, "A");
//      Member member2 = new Member(160L, "B");
//
//      em.persist(member1);
//      em.persist(member2);
//
//
//      //엔티티의 생명주기
//      //객체(엔티티)를 생성한 상태(비영속)
//Member member = new Member();
//      member.setId(101L);
//      member.setName("HelloJPA");
//
//
//      //객체(엔티티)를 저장한 상태(영속), 1차 캐시에 저장됨
//em.persist(member);
//
//
//      //회원 엔티티를 영속성 컨텍스트에서 분리, 준영속 상태
//em.detach(member);
//
//
//      //객체를 삭제한 상태(삭제)
//Member memberA = em.find(Member.class, “memberA");
//      em.remove(member);
//
//
//
//      // JPQA 조회 및 등록 수정 삭제 방법
//Member findMember = em.find(Member.class, 1L);
//      List<Member> result = em.createQuery("select m from Member as m", Member.class)
//          .setFirstResult(4)
//          .setMaxResults(8)
//          .getResultList();
//
//      for (Member member : result) {
//        System.out.println("member.getName() = " + member.getName());
//      }
//
//
//      // JPA 단순 조회
//findMember.setName("HelloJPA");
//
//
//      tx.commit();  // [트랜잭션] 커밋
//    } catch (Exception e) {
//      tx.rollback();
//    } finally {
//      em.close();
//    }
//    emf.close();
//
//  }
//
//}
