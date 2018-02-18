package dangod.themis.service.impl;

import dangod.themis.dao.UserBaseInfoRepo;
import dangod.themis.model.po.UserBaseInfo;
import dangod.themis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserBaseInfoRepo baseInfoRepo;


    @Override
    public UserBaseInfo getBaseInfoByUserId(long userid) {
        return baseInfoRepo.findByUserId(userid);
    }

    @Override
    public UserBaseInfo addUserBaseInfo(String realname, String email, String sex) {
        return null;
    }

    @Override
    public UserBaseInfo updateUserBaseInfo(UserBaseInfo baseInfo) {
        return null;
    }
}
