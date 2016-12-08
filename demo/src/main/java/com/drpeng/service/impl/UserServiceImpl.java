package com.drpeng.service.impl;

import com.drpeng.persistence.dao.UserDao;
import com.drpeng.persistence.entity.UserEntity;
import com.drpeng.persistence.entity.UserEntityManager;
import com.drpeng.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liurl3 on 2016/11/2.
 */
@Service
public class UserServiceImpl implements IUserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserDao userDao;

    @Override
    public void saveUsers(UserEntity user) {
        userDao.insert(user);
    }

    @Override
    public List<UserEntity> getUsersByName(String name) throws Exception {
        if(name != null && !name.isEmpty()) {
            UserEntityManager userEntityManager = new UserEntityManager();
            userEntityManager.or().andNameLike(name);
            List<UserEntity>  userEntities = userDao.selectByManager(userEntityManager);
            return userEntities;
        }else{
            logger.error("传入参数name不能为空！");
            throw new Exception("传入参数name不能为空！");
        }
    }
}
