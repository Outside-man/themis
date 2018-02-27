package dangod.themis.service;

import dangod.themis.model.vo.score.record.*;

import java.util.List;

public interface StudentRecordService {
<#list entityList as entity>
    List<${entity}Vo> get${entity}ByUserIdAndTerm(long userId, String term, Integer page, Integer size);

    List<${entity}Vo> get${entity}ByStuIdAndTerm(String stuId, String term, Integer page, Integer size);

</#list>




}
