package dangod.themis.dao;

import dangod.themis.model.po.Inform;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InformRepo extends JpaRepository<Inform, Long> {
    @Query(value = "select * from common_inform where user_id = :userId ORDER BY date", nativeQuery = true)
    List<Inform> getInformByUserId(@Param("userId") Long userId);
}