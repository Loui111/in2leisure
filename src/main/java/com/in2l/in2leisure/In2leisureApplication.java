package com.in2l.in2leisure;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class In2leisureApplication {

	public static void main(String[] args) {
		SpringApplication.run(In2leisureApplication.class, args);

//		User findUser = order.getUser();
//		findUser.getEmail(); 머 이런식.

//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("in2l");
//		EntityManager em = emf.createEntityManager();
//
//		EntityTransaction tx = em.getTransaction();
//		tx.begin();
//
//		try{
//			tx.commit();
//		}
//		catch (Exception e){
//			tx.rollback();
//		}
//		finally {
//			em.close();
//		}
	}
}
