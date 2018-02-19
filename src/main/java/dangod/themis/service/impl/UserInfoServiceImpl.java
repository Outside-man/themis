package dangod.themis.service.impl;

import dangod.themis.dao.UserBaseInfoRepo;
import dangod.themis.model.po.UserBaseInfo;
import dangod.themis.model.vo.UserBaseInfoVo;
import dangod.themis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements UserInfoService{
    @Autowired
    private UserBaseInfoRepo baseInfoRepo;


    @Cacheable(value = "30m")
    @Override
    public UserBaseInfoVo getBaseInfoByUserId(long userid) {
        UserBaseInfo userBaseInfo = baseInfoRepo.findByUser_Id(userid);
        if(userBaseInfo == null) return null;
        return new UserBaseInfoVo(userBaseInfo);
    }

    @Override
    public Integer addUserBaseInfo(String realname, String email, String sex) {
        return null;
    }

    @CachePut(value = "30m")
    @Override
    public UserBaseInfoVo updateUserBaseInfo(UserBaseInfo baseInfo) {
        return null;
    }
}
