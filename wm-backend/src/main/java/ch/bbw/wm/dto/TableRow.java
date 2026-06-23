package ch.bbw.wm.dto;

/**
 * Eine berechnete Zeile in der Gruppentabelle (reines Uebertragungsobjekt).
 */
public record TableRow(
        Long teamId,
        String teamName,
        int played,
        int won,
        int drawn,
        int lost,
        int goalsFor,
        int goalsAgainst,
        int points
) {
    public int goalDifference() {
        return goalsFor - goalsAgainst;
    }
}
