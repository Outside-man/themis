package dangod.themis.service.impl;

import dangod.themis.dao.InformRepo;
import dangod.themis.dao.UserRepo;
import dangod.themis.model.po.Inform;
import dangod.themis.model.po.User;
import dangod.themis.model.vo.InformVo;
import dangod.themis.service.InformService;
import dangod.themis.service.UserInfoService;
import dangod.themis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InformServiceImpl implements InformService {

    @Autowired
    private InformRepo informRepo;
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;

    @Override
    public InformVo getById(long id) {
        Inform inform = informRepo.findOne(id);
        if(inform == null) return null;
        String author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealname();
        return new InformVo(inform, author);
    }

    @Override
    public InformVo addInform(String title, String content, long userId) {
        User user = userService.getUserById(userId);
        Inform inform= informRepo.save(new Inform(title, content, user));
        if(inform == null) return null;
        String author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealname();
        return new InformVo(inform, author);
    }

    @Override
    public Integer deleteInformById(long id) {
        return null;
    }

    @Override
    public InformVo updateInform(Inform inform) {
        return null;
    }

    @Override
    public List<InformVo> getListByUserId(long userId) {
        List<Inform> informList = informRepo.getInformByUserId(userId);
        if(informList == null) return null;
        List<InformVo> list = new ArrayList<>();
        String author = null;
        for(Inform inform : informList){
            author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealname();
            list.add(new InformVo(inform, author));
        }
        return list;
    }

    @Override
    public List<InformVo> getPage(Integer page, Integer size) {
        Pageable pageable = new PageRequest(page, size, Sort.Direction.ASC, "id");
        Page<Inform> informList =  informRepo.findAll(pageable);
        if(informList == null) return null;
        List<InformVo> list = new ArrayList<>();
        String author = null;
        for(Inform inform : informList){
            author = userInfoService.getBaseInfoByUserId(inform.getUser().getId()).getRealname();
            list.add(new InformVo(inform, author));
        }
        return list;
    }

    @Override
    public List<InformVo> getPage(Integer page) {
        return getPage(page, 5);
    }


}
