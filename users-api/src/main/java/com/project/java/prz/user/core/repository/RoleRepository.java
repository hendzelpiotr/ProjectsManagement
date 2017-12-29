package com.project.java.prz.user.core.repository;

import com.project.java.prz.common.core.domain.security.Role;
import com.project.java.prz.common.core.domain.security.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr on 03.06.2017.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(RoleType roleType);

}
