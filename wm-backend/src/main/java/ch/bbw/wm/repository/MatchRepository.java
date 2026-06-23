package ch.bbw.wm.repository;

import ch.bbw.wm.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, Long> {

    List<Match> findByGroupIgnoreCase(String group);
}
