package dangod.themis.service.impl.common;

import dangod.themis.dao.common.UserBaseInfoRepo;
import dangod.themis.model.po.common.UserBaseInfo;
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


    @Cacheable(value = "30m", key = "'user_base_'+#userId")
    @Override
    public UserBaseInfoVo getBaseInfoByUserId(long userId) {
        UserBaseInfo userBaseInfo = baseInfoRepo.findByUser_Id(userId);
        if(userBaseInfo == null) return null;
        return new UserBaseInfoVo(userBaseInfo);
    }



    @CachePut(value = "30m", key = "'user_base_'+#userId")
    @Override
    public UserBaseInfoVo updateUserBaseInfo(long userId, String realName, String email, String sex) {
        UserBaseInfo baseInfo = getBaseInfoPoByUserId(userId);
        if(realName != null)
            baseInfo.setRealName(realName);
        if(sex != null)
            baseInfo.setSex(sex);
        baseInfo.setEmail(email);
        baseInfoRepo.save(baseInfo);
        if(baseInfo == null) return null;
        return new UserBaseInfoVo(baseInfo);
    }

    private UserBaseInfo getBaseInfoPoByUserId(long userId){
        return baseInfoRepo.findByUser_Id(userId);
    }
}
