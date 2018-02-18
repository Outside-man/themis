package dangod.themis.service;

import dangod.themis.model.po.Inform;

public interface InformService {
    Inform getById(long id);

    Inform addInform(Inform inform);

    Integer deleteInformById(long id);

    Inform updateInform(Inform inform);
}
