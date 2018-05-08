package dangod.themis.service.impl.club;

import dangod.themis.model.vo.club.ClubVo;
import dangod.themis.service.club.ClubService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClubServiceImpl implements ClubService{
    @Override
    public ClubVo getClubById(Integer id) {
        return null;
    }

    @Override
    public ClubVo getClubByuserId(long userId) {
        return null;
    }

    @Override
    public List<ClubVo> getPageClub(Integer page, Integer size) {
        return null;
    }
}
