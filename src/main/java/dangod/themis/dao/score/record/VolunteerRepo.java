package dangod.themis.dao.score.record;

import dangod.themis.model.po.score.record.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VolunteerRepo extends JpaRepository<Volunteer, Long> {
}
