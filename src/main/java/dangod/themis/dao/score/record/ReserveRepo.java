package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepo extends JpaRepository<Reserve, Long>{
}
