package com.drpeng.service;

import com.drpeng.persistence.entity.UserEntity;

import java.util.List;

/**
 * Created by liurl3 on 2016/11/2.
 */
public interface IUserService {
    public void saveUsers(UserEntity test1User);
    public List<UserEntity> getUsersByName(String name) throws Exception;
}
