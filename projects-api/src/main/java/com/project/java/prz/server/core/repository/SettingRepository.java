package com.project.java.prz.server.core.repository;

import com.project.java.prz.common.core.domain.general.Setting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Piotr on 09.06.2017.
 */
@Repository
public interface SettingRepository extends JpaRepository<Setting, Integer> {
}
