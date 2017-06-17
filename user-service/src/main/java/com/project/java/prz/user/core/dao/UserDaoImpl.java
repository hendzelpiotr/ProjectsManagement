package com.project.java.prz.user.core.dao;

import com.project.java.prz.common.core.domain.security.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Piotr on 05.06.2017.
 */
@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getDisabledAccounts() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u where u.enabled = :enabled", User.class);
        query.setParameter("enabled", false);
        return query.getResultList();
    }

}
