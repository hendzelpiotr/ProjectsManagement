package com.project.java.prz.server.core.repository;

import com.project.java.prz.common.core.domain.general.UserProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr on 31.03.2017.
 */
@Repository
public interface UserProjectRepository extends JpaRepository<UserProject, Integer> {

    UserProject findByUserLogin(String login);

}
