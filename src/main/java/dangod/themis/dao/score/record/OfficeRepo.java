package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Office;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepo extends JpaRepository<Office, Long>{
    List<Office> findByBaseInfo_BaseInfo_User_Id(long userId, Pageable pageable);
//    List<Office> findByBaseInfo_BaseInfo_User_IdAndTerm(long userId, Pageable pageable);

}
