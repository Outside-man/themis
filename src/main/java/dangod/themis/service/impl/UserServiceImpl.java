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
        User user = userRepo.findByUsername(username);
        if(user == null)return null;
        if(!user.getPassword().equals(MD5Util.MD5(password+user.getSalt())))
            return null;
        return user;
    }

    @Override
    public User add(String username, String password) {
        if(userRepo.countByUsername(username)!=0)
            return null;
        User user = userRepo.save(new User(username, password));
        return user;
    }

    @Override
    public User update(User user) {
        return null;
    }
}
