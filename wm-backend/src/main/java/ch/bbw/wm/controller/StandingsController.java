package ch.bbw.wm.controller;

import ch.bbw.wm.dto.TableRow;
import ch.bbw.wm.service.RankingStrategy;
import ch.bbw.wm.service.StandingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/standings")
public class StandingsController {

    private final StandingsService standingsService;

    @Autowired
    public StandingsController(StandingsService standingsService) {
        this.standingsService = standingsService;
    }

    @GetMapping("/{group}")
    public List<TableRow> getStandings(@PathVariable String group,
                                        @RequestParam(name = "sort", required = false) String sort) {
        return standingsService.getTable(group, RankingStrategy.fromKey(sort));
    }
}
