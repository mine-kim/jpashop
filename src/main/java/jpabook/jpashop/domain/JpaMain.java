package jpabook.jpashop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args){
        //하나 생성하여 애플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // 쓰레드간에 공유X 요청이 올떄마다 생성
        EntityManager em = emf.createEntityManager();

        //JPA의 모든 데이터 변경은 트렌젝션 안에서 실행
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Order order = new Order();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
