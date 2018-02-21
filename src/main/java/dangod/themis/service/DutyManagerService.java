package dangod.themis.service;

import dangod.themis.model.po.score.DutyManager;

public interface DutyManagerService {
    DutyManager getManagerByUserId(long userId);
}
