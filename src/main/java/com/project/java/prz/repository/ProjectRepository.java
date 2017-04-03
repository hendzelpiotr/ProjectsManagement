package com.project.java.prz.repository;

import com.project.java.prz.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr on 31.03.2017.
 */
@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
