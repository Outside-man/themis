package dangod.themis.service;

import dangod.themis.model.po.score.Major;

public interface ClassService {
    Integer addMajor(String majorName, Integer year);

    Integer addClass(long majorId, Integer classNum);
}
