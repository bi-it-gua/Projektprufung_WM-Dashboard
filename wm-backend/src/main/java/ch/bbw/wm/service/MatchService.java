package ch.bbw.wm.service;

import ch.bbw.wm.entity.Match;
import ch.bbw.wm.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public List<Match> getMatchesByGroup(String group) {
        return matchRepository.findByGroupIgnoreCase(group);
    }

    // TODO: Es findet keinerlei Validierung statt. Negative Tore oder
    // unbekannte Match-IDs werden ungeprueft uebernommen.
    public Optional<Match> updateResult(Long matchId, Integer homeGoals, Integer awayGoals) {
        Optional<Match> found = matchRepository.findById(matchId);
        if (found.isPresent()) {
            Match m = found.get();
            m.setHomeGoals(homeGoals);
            m.setAwayGoals(awayGoals);
            matchRepository.save(m);
        }
        return found;
    }
}
