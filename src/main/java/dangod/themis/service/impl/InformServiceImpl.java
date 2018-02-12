package dangod.themis.service.impl;

import dangod.themis.dao.InformRepo;
import dangod.themis.model.po.Inform;
import dangod.themis.service.InformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InformServiceImpl implements InformService {
    @Autowired
    private InformRepo informRepo;

    @Override
    public Inform getById(long id){
        return informRepo.getOne(id);
    }


}
