package dangod.themis.dao.authority;

import dangod.themis.model.po.authority.AuthorityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityTypeRepo extends JpaRepository<AuthorityType, Long> {
    List<AuthorityType> findAllByIdIn(List<Long> list);
}
