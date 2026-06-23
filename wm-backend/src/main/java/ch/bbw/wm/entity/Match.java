package ch.bbw.wm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor   // von JPA benoetigt: parameterloser Konstruktor
@AllArgsConstructor
@Entity
@Table(name = "wm_match")
public class Match {

    @Id
    private Long id;

    @Column(name = "home_team_id", nullable = false)
    private Long homeTeamId;

    @Column(name = "away_team_id", nullable = false)
    private Long awayTeamId;

    @Column(name = "match_group", nullable = false)
    private String group;

    @Column(name = "home_goals")
    private Integer homeGoals; // null = noch nicht gespielt

    @Column(name = "away_goals")
    private Integer awayGoals; // null = noch nicht gespielt

    public boolean isPlayed() {
        return homeGoals != null && awayGoals != null;
    }
}
