package com.project.java.prz.user.core.repository;

import com.project.java.prz.common.core.domain.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by phendzel on 5/23/2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}