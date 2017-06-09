package com.project.java.prz.server.core.repository;

import com.project.java.prz.common.core.domain.general.UserSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by phendzel on 6/9/2017.
 */
@Repository
public interface UserSettingRepository extends JpaRepository<UserSetting, Integer> {
}
