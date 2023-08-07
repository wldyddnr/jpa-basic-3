package jpql;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team1 = new Team();
            team1.setName("teamA");
            Team team2 = new Team();
            team2.setName("teamB");
            Team team3 = new Team();
            team3.setName("teamC");
            em.persist(team1);
            em.persist(team2);
            em.persist(team3);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(team1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(team1);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(team2);

            Member member4 = new Member();
            member4.setUsername("회원4");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);


            em.flush();
            em.clear();

            List<Member> resultList1 = em.createNamedQuery("Member.findByUsername", Member.class).
                    setParameter("username", "회원1").
                    getResultList();

            for (Member member : resultList1) {
                System.out.println("member = " + member);
            }


            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();

    }
}
