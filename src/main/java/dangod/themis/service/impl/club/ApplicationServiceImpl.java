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
import dangod.themis.service.club.ApproveService;
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
    @Autowired
    private ApproveService approveService;

    @Override
    public Integer apply(Club club, String name, String place, String start, String end,
                               String people, Integer isFine, String introduce, MultipartFile file) {
        int hasFile = 0;
        if(file!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日-HH时mm分ss秒");
            long now = Calendar.getInstance().getTime().getTime();
            String fileName = String.format("[%s](%s)%s", sdf.format(now), club.getClubName(), name);
            if(BaseFile.upload(file, CLUB_PATH+club.getClubName(), fileName, true) == 0)
                hasFile = 1;
            //存文件信息进数据库
        }
        try {
            Application application = new Application(club, name, place, start, end, people, isFine, introduce, hasFile);
            applicationRepo.save(application);
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    @Override
    public ApplicationVo getApplicationVo(long applicationId) {
        ApplicationVo applicationVo = null;
        try {
            Application application = applicationRepo.findOne(applicationId);
            applicationVo = new ApplicationVo(application, approveService.getApprovalVoListById(applicationId));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return applicationVo;
    }

    @Override
    public List<StatusVo> getPageStatus(long clubId, Integer page, Integer size) {
        List<Application> applicationList = applicationRepo.findAllByClub_Id(clubId, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        List<StatusVo> statusVoList = new ArrayList<>();
        for(Application a : applicationList){
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getPageStatus(long clubId, Integer status, Integer page, Integer size) {
        List<Application> applicationList = applicationRepo.findAllByClub_IdAndStatus(clubId, status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        List<StatusVo> statusVoList = new ArrayList<>();
        for(Application a : applicationList){
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllCanSeePageStatus(ClubRole clubRole, Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        if (clubRole.getLv() >= 2) {
            List<Application> applicationList = applicationRepo.findByLvGreaterThanEqual(clubRole.getLv(), new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for(Application a : applicationList){
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllCanSeePageStatus(ClubRole clubRole, Integer status, Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        if (clubRole.getLv() >= 2) {
            List<Application> applicationList = applicationRepo.findByLvGreaterThanEqualAndStatus(clubRole.getLv(), status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for(Application a : applicationList){
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getNeedApprovePage(ClubRole clubRole, Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        if (clubRole.getLv() >= 2) {
            List<Application> applicationList = applicationRepo.findAllByLvEquals(clubRole.getLv(), new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
            for(Application a : applicationList){
                statusVoList.add(new StatusVo(a));
            }
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllPageStatus(Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        Page<Application> applicationList = applicationRepo.findAll(new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        for(Application a : applicationList){
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }

    @Override
    public List<StatusVo> getAllPageStatus(Integer status, Integer page, Integer size) {
        List<StatusVo> statusVoList= new ArrayList<>();
        Page<Application> applicationList = applicationRepo.findAllByStatus(status, new PageRequest(page, size, new Sort(Sort.Direction.DESC, "id")));
        for(Application a : applicationList){
            statusVoList.add(new StatusVo(a));
        }
        return statusVoList;
    }
}
