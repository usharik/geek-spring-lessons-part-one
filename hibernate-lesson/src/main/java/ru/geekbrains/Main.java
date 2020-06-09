package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.persist.Contact;
import ru.geekbrains.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // INSERT User
//        EntityManager em = emFactory.createEntityManager();
//        User user1 = new User(null, "alex", "password");
//        User user2 = new User(null, "ivan", "password");
//        User user3 = new User(null, "petr", "password");
//
//        em.getTransaction().begin();
//        em.persist(user1);
//        em.persist(user2);
//        em.persist(user3);
//        em.getTransaction().commit();

        // SELECT User
//        EntityManager em = emFactory.createEntityManager();
//        User user = em.find(User.class, 1L);
//        System.out.println(user);
//
//        em.find(User.class, 1L);
//        System.out.println(user);
//
//        em.find(User.class, 1L);
//        System.out.println(user);

        // UPDATE User
//        EntityManager em = emFactory.createEntityManager();
//        User user = em.find(User.class, 1L);
//        System.out.println(user);
//
//        em.getTransaction().begin();
//        user.setName("Alexey");
//        em.getTransaction().commit();
//
//        em.close();

        //DELETE User
//        EntityManager em = emFactory.createEntityManager();
//        User user = em.find(User.class, 1L);
//
//        em.getTransaction().begin();
//        em.remove(user);
//        em.getTransaction().commit();

        // SELECT by name
//        EntityManager em = emFactory.createEntityManager();
//        //List<User> users = em.createQuery("from User u where u.name = :name", User.class)
//        List<User> users = em.createNamedQuery("getByName", User.class)
//                .setParameter("name", "ivan")
//                .getResultList();
//        users.forEach(System.out::println);
//
//        User user = users.get(0);
//
//        em.getTransaction().begin();
//        em.persist(new Contact(null, "phone", "+7 123 456 789", user));
//        em.persist(new Contact(null, "email", "email@email.com", user));
//        em.persist(new Contact(null, "phone", "+7 222 33 44443", user));
//        em.getTransaction().commit();
//
//        em.refresh(user);
//
//        System.out.println(user);
//
//        em.close();

        EntityManager em = emFactory.createEntityManager();
        em.createNamedQuery("withUserName")
                .setParameter("id", 1L).getResultList()
                .forEach(System.out::println);
    }
}
