package dangod.themis.service;

import dangod.themis.model.po.score.Major;

public interface ClassService {
    /**
     * 添加专业
     * @param majorName
     * @param year
     * @return
     */
    Integer addMajor(String majorName, Integer year);

    /**
     * 添加班级
     * @param majorId
     * @param classNum
     * @return
     */
    Integer addClass(long majorId, Integer classNum);
}
