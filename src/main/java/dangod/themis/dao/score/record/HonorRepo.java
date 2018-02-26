package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Honor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HonorRepo extends JpaRepository<Honor, Long>{
    List<Honor> findByBaseInfo_BaseInfo_User_IdOrderByTerm(long userId, Pageable pageable);
}
