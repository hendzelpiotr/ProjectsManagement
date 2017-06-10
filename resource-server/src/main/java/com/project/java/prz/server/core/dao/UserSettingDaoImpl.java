package com.project.java.prz.server.core.dao;

import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserSetting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by phendzel on 6/9/2017.
 */
@Repository
public class UserSettingDaoImpl implements UserSettingDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserSetting findGlobalUserSettingByName(SettingName settingName) {
        TypedQuery<UserSetting> query = entityManager.createQuery("select us from UserSetting us join us.setting s where us.userDetail is null and s.name = :settingName", UserSetting.class);
        query.setParameter("settingName", settingName);
        return query.getSingleResult();
    }

}
