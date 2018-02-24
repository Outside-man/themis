package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Practice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PracticeRepo extends JpaRepository<Practice, Long>{
    List<Practice> findByBaseInfo_BaseInfo_User_Id(long userId, Pageable pageable);
}
