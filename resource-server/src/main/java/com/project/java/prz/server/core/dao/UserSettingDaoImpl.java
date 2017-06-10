package com.project.java.prz.server.core.dao;

import com.project.java.prz.common.core.domain.general.SettingName;
import com.project.java.prz.common.core.domain.general.UserSetting;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public UserSetting findGlobalUserSettingBySettingName(SettingName settingName) {
        TypedQuery<UserSetting> query = entityManager.createQuery("select us from UserSetting us join us.setting s where us.userDetail is null and s.name = :settingName", UserSetting.class);
        query.setParameter("settingName", settingName);
        return query.getSingleResult();
    }

    @Override
    public UserSetting findUserSettingBySettingNameAndUserDetailLogin(SettingName settingName, String login) {
        TypedQuery<UserSetting> query = entityManager.createQuery("select us from UserSetting us join us.setting s where us.userDetail.login = :login and s.name = :settingName", UserSetting.class);
        query.setParameter("settingName", settingName);
        query.setParameter("login", login);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return findGlobalUserSettingBySettingName(SettingName.SCHEDULED_COMPLETION_DATE);
        }
    }

}
