package com.project.java.prz.repository;

import com.project.java.prz.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr on 25.03.2017.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);

}
