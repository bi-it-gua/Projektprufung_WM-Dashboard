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
@Table(name = "team")
public class Team {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "team_group", nullable = false)
    private String group; // z.B. "A", "B", "C", "D"
}
