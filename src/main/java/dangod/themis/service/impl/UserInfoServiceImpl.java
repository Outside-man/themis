package dangod.themis.service.impl;

import dangod.themis.model.po.UserBaseInfo;
import dangod.themis.service.UserInfoService;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    @Override
    public UserBaseInfo getBaseInfoByToken(String token) {
        return null;
    }

    @Override
    public UserBaseInfo addBaseInfo(String realname, String email, String sex) {
        return null;
    }
}
