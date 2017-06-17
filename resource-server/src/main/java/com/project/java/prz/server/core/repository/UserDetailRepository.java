package com.project.java.prz.server.core.repository;

import com.project.java.prz.common.core.domain.general.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by phendzel on 6/1/2017.
 */
@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, String> {
}
