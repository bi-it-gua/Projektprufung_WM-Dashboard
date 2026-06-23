package ch.bbw.wm.service;

import ch.bbw.wm.dto.TableRow;
import ch.bbw.wm.entity.Match;
import ch.bbw.wm.entity.Team;
import ch.bbw.wm.repository.MatchRepository;
import ch.bbw.wm.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
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

    public List<TableRow> getTable(String groupName) {
        List<Team> teams = teamRepository.findByGroupIgnoreCase(groupName);
        List<TableRow> rows = buildInitialRows(teams);
        applyMatchResults(rows, groupName);
        return sortByPoints(rows);
    }

    private List<TableRow> buildInitialRows(List<Team> teams) {
        List<TableRow> rows = new ArrayList<>();
        for (Team team : teams) {
            rows.add(new TableRow(team.getId(), team.getName(), 0, 0, 0, 0, 0, 0, 0));
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

        incrementGamesPlayed(rows, homeIndex, awayIndex);
        updateGoals(rows, homeIndex, awayIndex, match);
        updatePoints(rows, homeIndex, awayIndex, match);
    }

    private int findRowIndex(List<TableRow> rows, Long teamId) {
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).teamId().equals(teamId)) return i;
        }
        return -1;
    }

    private void incrementGamesPlayed(List<TableRow> rows, int homeIndex, int awayIndex) {
        rows.set(homeIndex, addPlayed(rows.get(homeIndex)));
        rows.set(awayIndex, addPlayed(rows.get(awayIndex)));
    }

    private void updateGoals(List<TableRow> rows, int homeIndex, int awayIndex, Match match) {
        TableRow home = rows.get(homeIndex);
        TableRow away = rows.get(awayIndex);
        rows.set(homeIndex, new TableRow(home.teamId(), home.teamName(), home.played(),
                home.won(), home.drawn(), home.lost(),
                home.goalsFor() + match.getHomeGoals(),
                home.goalsAgainst() + match.getAwayGoals(), home.points()));
        rows.set(awayIndex, new TableRow(away.teamId(), away.teamName(), away.played(),
                away.won(), away.drawn(), away.lost(),
                away.goalsFor() + match.getAwayGoals(),
                away.goalsAgainst() + match.getHomeGoals(), away.points()));
    }

    private void updatePoints(List<TableRow> rows, int homeIndex, int awayIndex, Match match) {
        boolean homeWon = match.getHomeGoals() > match.getAwayGoals();
        boolean awayWon = match.getAwayGoals() > match.getHomeGoals();
        boolean isDraw = match.getHomeGoals().equals(match.getAwayGoals());

        if (homeWon) {
            rows.set(homeIndex, addWin(rows.get(homeIndex)));
            rows.set(awayIndex, addLoss(rows.get(awayIndex)));
        } else if (awayWon) {
            rows.set(awayIndex, addWin(rows.get(awayIndex)));
            rows.set(homeIndex, addLoss(rows.get(homeIndex)));
        } else if (isDraw) {
            rows.set(homeIndex, addDraw(rows.get(homeIndex)));
            rows.set(awayIndex, addDraw(rows.get(awayIndex)));
        }
    }

    private List<TableRow> sortByPoints(List<TableRow> rows) {
        rows.sort(Comparator.comparingInt(TableRow::points).reversed());
        return rows;
    }

    private TableRow addPlayed(TableRow row) {
        return new TableRow(row.teamId(), row.teamName(), row.played() + 1,
                row.won(), row.drawn(), row.lost(),
                row.goalsFor(), row.goalsAgainst(), row.points());
    }

    private TableRow addWin(TableRow row) {
        return new TableRow(row.teamId(), row.teamName(), row.played(),
                row.won() + 1, row.drawn(), row.lost(),
                row.goalsFor(), row.goalsAgainst(), row.points() + 3);
    }

    private TableRow addLoss(TableRow row) {
        return new TableRow(row.teamId(), row.teamName(), row.played(),
                row.won(), row.drawn(), row.lost() + 1,
                row.goalsFor(), row.goalsAgainst(), row.points());
    }

    private TableRow addDraw(TableRow row) {
        return new TableRow(row.teamId(), row.teamName(), row.played(),
                row.won(), row.drawn() + 1, row.lost(),
                row.goalsFor(), row.goalsAgainst(), row.points() + 1);
    }
}