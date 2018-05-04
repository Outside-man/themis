package dangod.themis.service.impl.club;

import dangod.themis.core.util.BaseFile;
import dangod.themis.dao.club.ApplicationRepo;
import dangod.themis.dao.club.ClubRepo;
import dangod.themis.model.po.club.Application;
import dangod.themis.model.po.club.Club;
import dangod.themis.model.po.club.ClubRole;
import dangod.themis.model.vo.club.ApplicationVo;
import dangod.themis.model.vo.club.StatusVo;
import dangod.themis.service.club.ApplicationService;
import dangod.themis.service.club.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static dangod.themis.core.util.BaseFile.FOLDER;

@Service
public class ApplicationServiceImpl implements ApplicationService{
    private static String CLUB_PATH = FOLDER+"club"+ File.separator;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ApplicationRepo applicationRepo;

    @Override
    public ApplicationVo apply(long userId, String activityName, String activityPlace, String activityStart, String activityEnd, String activitypeople, Integer isFine, String introduce, MultipartFile file) {
        Club club = roleService.getClubByUserId(userId);
        if(club == null)return null;
        int hasFile = 0;
        if(file!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)%s", sdf.format(now), club.getClubName(), activityName);
            if(BaseFile.upload(file, CLUB_PATH+club.getClubName(), fileName, true) == 0)
                hasFile = 1;
            //存信息数据库
        }
        ApplicationVo applicationVo = null;
        try {
            Application application = new Application(club, activityName, activityPlace, activityStart, activityEnd, activitypeople, isFine, introduce, hasFile);
            applicationVo = new ApplicationVo(application);
        }catch (Exception e){
            return null;
        }
        return applicationVo;
    }

    @Override
    public ApplicationVo getApplicationVo(long applicationId) {
        ApplicationVo applicationVo = null;
        try {
            Application application = applicationRepo.findOne(applicationId);
            applicationVo = new ApplicationVo(application);
        }catch (Exception e){
            return null;
        }
        return applicationVo;
    }

    @Override
    public List<StatusVo> getPageStatus(long clubId, Integer page, Integer size) {
        List<Application> applicationList = applicationRepo.findById(clubId, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        List<StatusVo> statusVoList = new ArrayList<>();
        for(Application a : applicationList){
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getPageStatus(long clubId, Integer status, Integer page, Integer size) {
        List<Application> applicationList = applicationRepo.findByIdAndStatus(clubId, status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        List<StatusVo> statusVoList = new ArrayList<>();
        for(Application a : applicationList){
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllPageStatus(long userId, Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        ClubRole role = roleService.getRole(userId);
        if (role.getLv() >= 2) {
            List<Application> applicationList = applicationRepo.findByLvGreaterThanEqual(role.getLv(), new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for(Application a : applicationList){
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllPageStatus(long userId, Integer status, Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        ClubRole role = roleService.getRole(userId);
        if (role.getLv() >= 2) {
            List<Application> applicationList = applicationRepo.findByLvGreaterThanEqualAndStatus(role.getLv(), status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for(Application a : applicationList){
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }
}
