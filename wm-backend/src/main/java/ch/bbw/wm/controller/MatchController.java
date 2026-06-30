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
    public List<Match> getMatches(@RequestParam(required = false) String group,
                                   @RequestParam(required = false) Integer matchday) {
        return matchService.getMatches(group, matchday);
    }

    @PutMapping("/{id}/result")
    public ResponseEntity<?> updateResult(@PathVariable Long id,
                                          @RequestBody ResultRequest request) {
        if (request.homeGoals() == null || request.awayGoals() == null) {
            return ResponseEntity.badRequest().body("Tore dürfen nicht leer sein.");
        }
        if (request.homeGoals() < 0 || request.awayGoals() < 0) {
            return ResponseEntity.badRequest().body("Tore dürfen nicht negativ sein.");
        }
        Optional<Match> updated = matchService.updateResult(id, request.homeGoals(), request.awayGoals());
        return updated.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(404).body("Spiel nicht gefunden."));
    }
}
