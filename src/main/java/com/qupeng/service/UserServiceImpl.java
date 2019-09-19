package com.qupeng.service;

import com.qupeng.model.User;
import com.qupeng.mybatis.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserById(Integer id) {
        return userMapper.selectByPrimaryKey(Long.valueOf(id));
    }

    @Transactional(transactionManager="transactionManager", readOnly = false,
            isolation = Isolation.REPEATABLE_READ, propagation = Propagation.REQUIRED,
            noRollbackFor = FileNotFoundException.class, rollbackFor = Exception.class, timeout = -1)
    @Override
    public int updateUserById(Integer id) {
        User user=new User();
        user.setId(Long.valueOf(id));
        user.setName("seven");
        return userMapper.updateByPrimaryKeySelective(user);
    }
}