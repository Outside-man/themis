package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Practice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PracticeRepo extends JpaRepository<Practice, Long>{
}
