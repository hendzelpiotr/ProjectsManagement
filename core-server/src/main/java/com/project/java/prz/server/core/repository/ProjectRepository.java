package com.project.java.prz.server.core.repository;

import com.project.java.prz.common.core.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr on 31.03.2017.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
