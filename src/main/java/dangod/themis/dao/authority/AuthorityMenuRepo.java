package dangod.themis.dao.authority;

import dangod.themis.model.po.authority.AuthorityMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityMenuRepo extends JpaRepository<AuthorityMenu, Long> {
    List<AuthorityMenu> findAllByIdIn(List<Long> list);
}
