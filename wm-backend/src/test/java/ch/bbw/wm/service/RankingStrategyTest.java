package ch.bbw.wm.service;

import ch.bbw.wm.dto.TableRow;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankingStrategyTest {

    private static TableRow row(long teamId, int points, int goalsFor, int goalsAgainst, int fairPlayPoints) {
        return new TableRow(teamId, "Team" + teamId, 0, 0, 0, 0, goalsFor, goalsAgainst, points, fairPlayPoints);
    }

    private static List<Long> sortedIds(RankingStrategy strategy, List<TableRow> rows) {
        List<TableRow> copy = new ArrayList<>(rows);
        copy.sort(strategy.comparator());
        return copy.stream().map(TableRow::teamId).toList();
    }

    @Test
    void points_sortsByPointsDescending() {
        List<TableRow> rows = List.of(
                row(1, 3, 1, 1, 0),
                row(2, 6, 1, 1, 0),
                row(3, 1, 1, 1, 0)
        );

        assertThat(sortedIds(RankingStrategy.POINTS, rows)).containsExactly(2L, 1L, 3L);
    }

    @Test
    void points_tiebreaksByGoalDifferenceWhenPointsEqual() {
        List<TableRow> rows = List.of(
                row(1, 3, 2, 2, 0), // Diff 0
                row(2, 3, 4, 1, 0), // Diff +3
                row(3, 3, 1, 3, 0)  // Diff -2
        );

        assertThat(sortedIds(RankingStrategy.POINTS, rows)).containsExactly(2L, 1L, 3L);
    }

    @Test
    void points_tiebreaksByGoalsForWhenPointsAndDiffEqual() {
        List<TableRow> rows = List.of(
                row(1, 3, 3, 1, 0), // Diff +2, Tore 3
                row(2, 3, 5, 3, 0)  // Diff +2, Tore 5
        );

        assertThat(sortedIds(RankingStrategy.POINTS, rows)).containsExactly(2L, 1L);
    }

    @Test
    void goalDifference_sortsByDiffDescendingEvenWithFewerPoints() {
        List<TableRow> rows = List.of(
                row(1, 6, 2, 1, 0),  // Punkte 6, Diff +1
                row(2, 3, 5, 0, 0)   // Punkte 3, Diff +5
        );

        assertThat(sortedIds(RankingStrategy.GOAL_DIFFERENCE, rows)).containsExactly(2L, 1L);
    }

    @Test
    void goalDifference_tiebreaksByPointsWhenDiffEqual() {
        List<TableRow> rows = List.of(
                row(1, 3, 2, 1, 0), // Diff +1, Punkte 3
                row(2, 6, 3, 2, 0)  // Diff +1, Punkte 6
        );

        assertThat(sortedIds(RankingStrategy.GOAL_DIFFERENCE, rows)).containsExactly(2L, 1L);
    }

    @Test
    void fairPlay_sortsByFewestPenaltyPointsFirst() {
        List<TableRow> rows = List.of(
                row(1, 6, 0, 0, 5),
                row(2, 6, 0, 0, 1),
                row(3, 6, 0, 0, 3)
        );

        assertThat(sortedIds(RankingStrategy.FAIR_PLAY, rows)).containsExactly(2L, 3L, 1L);
    }

    @Test
    void fairPlay_tiebreaksByPointsWhenPenaltyEqual() {
        List<TableRow> rows = List.of(
                row(1, 3, 0, 0, 2),
                row(2, 6, 0, 0, 2)
        );

        assertThat(sortedIds(RankingStrategy.FAIR_PLAY, rows)).containsExactly(2L, 1L);
    }

    @Test
    void fromKey_mapsKnownKeysCaseInsensitively() {
        assertThat(RankingStrategy.fromKey("points")).isEqualTo(RankingStrategy.POINTS);
        assertThat(RankingStrategy.fromKey("GoalDifference")).isEqualTo(RankingStrategy.GOAL_DIFFERENCE);
        assertThat(RankingStrategy.fromKey("Tordifferenz")).isEqualTo(RankingStrategy.GOAL_DIFFERENCE);
        assertThat(RankingStrategy.fromKey("fairplay")).isEqualTo(RankingStrategy.FAIR_PLAY);
        assertThat(RankingStrategy.fromKey("fair-play")).isEqualTo(RankingStrategy.FAIR_PLAY);
    }

    @Test
    void fromKey_fallsBackToPointsForNullOrUnknown() {
        assertThat(RankingStrategy.fromKey(null)).isEqualTo(RankingStrategy.POINTS);
        assertThat(RankingStrategy.fromKey("unbekannt")).isEqualTo(RankingStrategy.POINTS);
    }
}
