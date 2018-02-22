package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Honor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HonorRepo extends JpaRepository<Honor, Long>{

}
