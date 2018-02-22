package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepo extends JpaRepository<Skill, Long>{
}
