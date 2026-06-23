package ch.bbw.wm.controller;

import ch.bbw.wm.dto.ResultRequest;
import ch.bbw.wm.entity.Match;
import ch.bbw.wm.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public List<Match> getMatches(@RequestParam(required = false) String group) {
        if (group != null) {
            return matchService.getMatchesByGroup(group);
        }
        return matchService.getAllMatches();
    }

    // Ergebnis erfassen / aktualisieren.
    // Hinweis: Die Eingaben werden NICHT validiert (siehe MatchService).
    @PutMapping("/{id}/result")
    public ResponseEntity<Match> updateResult(@PathVariable Long id,
                                              @RequestBody ResultRequest request) {
        Optional<Match> updated =
                matchService.updateResult(id, request.homeGoals(), request.awayGoals());
        return updated.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
