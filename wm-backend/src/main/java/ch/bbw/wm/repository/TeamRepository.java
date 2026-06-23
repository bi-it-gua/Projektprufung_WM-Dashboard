package ch.bbw.wm.repository;

import ch.bbw.wm.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByGroupIgnoreCase(String group);
}
