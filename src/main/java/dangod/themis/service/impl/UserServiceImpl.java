package dangod.themis.service.impl;

import dangod.themis.dao.UserRepo;
import dangod.themis.model.po.User;
import dangod.themis.service.UserService;
import dangod.themis.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public Long check(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null) return -1L;
        if (!user.getPassword().equals(MD5Util.MD5(password + user.getSalt())))
            return null;
        return user.getId();
    }

    @Override
    public Integer add(String username, String password) {
        if (userRepo.countByUsername(username) != 0)
            return -1;
        User user = userRepo.save(new User(username, password));
        return 0;
    }

    @Override
    public Integer updatePassword(Long userId, String password) {
        User user = userRepo.findOne(userId);
        user.setSalt();
        user.updatePassword(password);
        User r = userRepo.saveAndFlush(user);
        if(r == null)return -1;
        return 0;
    }

    @Cacheable(value = "30m")
    @Override
    public User getUserById(Long userId) {
        return userRepo.findOne(userId);
    }

}
