package com.lei.dao.impl;
import org.springframework.stereotype.Repository;

import com.lei.dao.UserDaoI;
import com.lei.entity.TUser;

@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<TUser> implements UserDaoI{

}
