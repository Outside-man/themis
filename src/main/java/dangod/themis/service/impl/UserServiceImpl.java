package dangod.themis.service.impl;

import dangod.themis.dao.UserRepo;
import dangod.themis.model.po.User;
import dangod.themis.service.UserService;
import dangod.themis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;
    @Override
    public User check(String username, String password) {
        User user = userRepo.findByUsernameAndPassword(username, MD5Util.MD5(password));
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
