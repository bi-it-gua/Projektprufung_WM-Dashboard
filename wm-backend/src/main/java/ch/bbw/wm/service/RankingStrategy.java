package ch.bbw.wm.service;

import ch.bbw.wm.dto.TableRow;

import java.util.Comparator;

/**
 * Austauschbare Wertungslogiken fuer die Tabellenberechnung.
 * Jede Strategie liefert einen Comparator, der absteigend nach "bester zuerst" sortiert
 * und bei Gleichstand auf die Punkte als letzten Tiebreaker zurueckfaellt.
 */
public enum RankingStrategy {

    POINTS(Comparator.comparingInt(TableRow::points)
            .thenComparingInt(TableRow::goalDifference)
            .thenComparingInt(TableRow::goalsFor)
            .reversed()),

    GOAL_DIFFERENCE(Comparator.comparingInt(TableRow::goalDifference)
            .thenComparingInt(TableRow::points)
            .thenComparingInt(TableRow::goalsFor)
            .reversed()),

    FAIR_PLAY(Comparator.comparingInt(TableRow::fairPlayPoints) // weniger Strafpunkte = besser
            .thenComparing(Comparator.comparingInt(TableRow::points).reversed()));

    private final Comparator<TableRow> comparator;

    RankingStrategy(Comparator<TableRow> comparator) {
        this.comparator = comparator;
    }

    public Comparator<TableRow> comparator() {
        return comparator;
    }

    public static RankingStrategy fromKey(String key) {
        if (key == null) return POINTS;
        return switch (key.toLowerCase()) {
            case "goaldifference", "tordifferenz" -> GOAL_DIFFERENCE;
            case "fairplay", "fair-play" -> FAIR_PLAY;
            default -> POINTS;
        };
    }
}
