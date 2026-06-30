package ch.bbw.wm.service;

import ch.bbw.wm.dto.TableRow;
import ch.bbw.wm.entity.Match;
import ch.bbw.wm.entity.Team;
import ch.bbw.wm.repository.MatchRepository;
import ch.bbw.wm.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandingsService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public StandingsService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<TableRow> getTable(String groupName, RankingStrategy strategy) {
        List<Team> teams = teamRepository.findByGroupIgnoreCase(groupName);
        List<TableRow> rows = buildInitialRows(teams);
        applyMatchResults(rows, groupName);
        rows.sort(strategy.comparator());
        return rows;
    }

    private List<TableRow> buildInitialRows(List<Team> teams) {
        List<TableRow> rows = new ArrayList<>();
        for (Team team : teams) {
            rows.add(new TableRow(team.getId(), team.getName(), 0, 0, 0, 0, 0, 0, 0, 0));
        }
        return rows;
    }

    private void applyMatchResults(List<TableRow> rows, String groupName) {
        List<Match> matches = matchRepository.findByGroupIgnoreCase(groupName);
        for (Match match : matches) {
            if (match.getHomeGoals() != null && match.getAwayGoals() != null) {
                updateRowsForMatch(rows, match);
            }
        }
    }

    private void updateRowsForMatch(List<TableRow> rows, Match match) {
        int homeIndex = findRowIndex(rows, match.getHomeTeamId());
        int awayIndex = findRowIndex(rows, match.getAwayTeamId());
        if (homeIndex < 0 || awayIndex < 0) return;

        rows.set(homeIndex, applyResult(rows.get(homeIndex), match.getHomeGoals(), match.getAwayGoals(), match.getFairPlayHome()));
        rows.set(awayIndex, applyResult(rows.get(awayIndex), match.getAwayGoals(), match.getHomeGoals(), match.getFairPlayAway()));
    }

    private int findRowIndex(List<TableRow> rows, Long teamId) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).teamId().equals(teamId)) return i;
        }
        return -1;
    }

    private TableRow applyResult(TableRow row, int goalsScored, int goalsConceded, int fairPlay) {
        boolean won = goalsScored > goalsConceded;
        boolean lost = goalsScored < goalsConceded;
        return new TableRow(row.teamId(), row.teamName(), row.played() + 1,
                row.won() + (won ? 1 : 0),
                row.drawn() + (!won && !lost ? 1 : 0),
                row.lost() + (lost ? 1 : 0),
                row.goalsFor() + goalsScored,
                row.goalsAgainst() + goalsConceded,
                row.points() + (won ? 3 : (!lost ? 1 : 0)),
                row.fairPlayPoints() + fairPlay);
    }
}
