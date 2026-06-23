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

/**
 * Berechnet die Gruppentabelle.
 *
 * ACHTUNG (Ausgangslage): Diese Klasse enthaelt bewusst unsauberen Code.
 * Die Berechnung steckt in einer einzigen langen Methode, die Sortierung
 * erfolgt nur nach Punkten (kein Tiebreaker nach Tordifferenz o.ae.).
 * Hier setzen die Issues "Strategy" und "Refactoring" an.
 */
@Service
public class StandingsService {

    private final TeamRepository teamRepository;
    private final MatchRepository matchRepository;

    @Autowired
    public StandingsService(TeamRepository teamRepository, MatchRepository matchRepository) {
        this.teamRepository = teamRepository;
        this.matchRepository = matchRepository;
    }

    public List<TableRow> getTable(String g) {
        // alle teams der gruppe holen
        List<Team> ts = teamRepository.findByGroupIgnoreCase(g);
        // hilfsstrukturen: index ueber teamId
        List<Long> ids = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<Integer> pl = new ArrayList<>();
        List<Integer> wo = new ArrayList<>();
        List<Integer> dr = new ArrayList<>();
        List<Integer> lo = new ArrayList<>();
        List<Integer> gf = new ArrayList<>();
        List<Integer> ga = new ArrayList<>();
        List<Integer> pt = new ArrayList<>();
        for (Team t : ts) {
            ids.add(t.getId());
            names.add(t.getName());
            pl.add(0);
            wo.add(0);
            dr.add(0);
            lo.add(0);
            gf.add(0);
            ga.add(0);
            pt.add(0);
        }
        // alle matches durchgehen und werte draufrechnen
        List<Match> ms = matchRepository.findByGroupIgnoreCase(g);
        for (Match m : ms) {
            if (m.getHomeGoals() != null && m.getAwayGoals() != null) {
                int hi = ids.indexOf(m.getHomeTeamId());
                int ai = ids.indexOf(m.getAwayTeamId());
                if (hi >= 0 && ai >= 0) {
                    pl.set(hi, pl.get(hi) + 1);
                    pl.set(ai, pl.get(ai) + 1);
                    gf.set(hi, gf.get(hi) + m.getHomeGoals());
                    ga.set(hi, ga.get(hi) + m.getAwayGoals());
                    gf.set(ai, gf.get(ai) + m.getAwayGoals());
                    ga.set(ai, ga.get(ai) + m.getHomeGoals());
                    if (m.getHomeGoals() > m.getAwayGoals()) {
                        wo.set(hi, wo.get(hi) + 1);
                        lo.set(ai, lo.get(ai) + 1);
                        pt.set(hi, pt.get(hi) + 3); // 3 punkte fuer sieg
                    } else if (m.getHomeGoals() < m.getAwayGoals()) {
                        wo.set(ai, wo.get(ai) + 1);
                        lo.set(hi, lo.get(hi) + 1);
                        pt.set(ai, pt.get(ai) + 3);
                    } else {
                        dr.set(hi, dr.get(hi) + 1);
                        dr.set(ai, dr.get(ai) + 1);
                        pt.set(hi, pt.get(hi) + 1);
                        pt.set(ai, pt.get(ai) + 1);
                    }
                }
            }
        }
        // rows bauen
        List<TableRow> rows = new ArrayList<>();
        for (int i = 0; i < ids.size(); i++) {
            rows.add(new TableRow(ids.get(i), names.get(i), pl.get(i), wo.get(i),
                    dr.get(i), lo.get(i), gf.get(i), ga.get(i), pt.get(i)));
        }
        // sortieren - nur nach punkten, rest wird ignoriert
        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.size() - 1; j++) {
                if (rows.get(j).points() < rows.get(j + 1).points()) {
                    TableRow tmp = rows.get(j);
                    rows.set(j, rows.get(j + 1));
                    rows.set(j + 1, tmp);
                }
            }
        }
        return rows;
    }
}
