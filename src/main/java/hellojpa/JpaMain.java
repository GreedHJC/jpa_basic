package hellojpa;

import com.sun.istack.internal.NotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

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
      Member member = new Member();
      member.setCreateBy("Kim");
      member.setCreateDate(LocalDateTime.now());


      em.persist(member);

      em.flush();
      em.clear();

      Movie findMovie = em.find(Movie.class, member.getId());
      System.out.println("findMovie = " + findMovie);

      tx.commit();  // [트랜잭션] 커밋
    } catch (Exception e) {
      tx.rollback();
    } finally {
      em.close();
    }
    emf.close();

  }

  @NotNull
  private static Member saveMember(EntityManager em) {
    Member member = new Member();
    member.setUsername("member1");

    em.persist(member);
    return member;
  }

}

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
