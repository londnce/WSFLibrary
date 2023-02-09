package wsf.service.impl;

import wsf.domain.User;
import wsf.mapper.UserMapper;
import wsf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // 注入UserMapper对象
    @Autowired
    private UserMapper userMapper;

    // 通过User的账号和密码查询用户
    @Override
    public User login(User user) {
        return userMapper.login(user);
    }
}
