package dangod.themis.service;

import dangod.themis.model.po.Inform;
import dangod.themis.model.vo.InformVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InformService {
    /**
     * 获取通知
     * @param id
     * @return
     */
    InformVo getById(long id);

    InformVo addInform(String title, String content, long userId);

    Integer deleteInformById(long id);

    InformVo updateInform(Inform inform);

    List<InformVo> getListByUserId(long userId);

    List<InformVo> getPage(Integer page, Integer size);

    List<InformVo> getPage(Integer page);
}
