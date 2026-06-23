# WM-Dashboard – Backend (Ausgangslage)

Spring-Boot-Backend (REST-API) für das WM-Dashboard. Dies ist die **Ausgangslage**
für das Projekt der Module 347 und 426. Die Anwendung ist lauffähig, aber bewusst
unfertig – an den markierten Stellen setzen die Issues an.

## Voraussetzungen

- Java 21
- Maven 3.9+ (oder den mitgelieferten Wrapper `./mvnw`)

## Starten

```bash
./mvnw spring-boot:run
```

Die API läuft danach auf `http://localhost:8080`.

## Technologie

- Spring Boot 4 (Web, Data JPA)
- **H2** als In-Memory-Datenbank
- **Lombok** (Getter/Setter/Konstruktoren der Entities)
- Kein Spring Security

Die H2-Konsole ist unter `http://localhost:8080/h2-console` erreichbar
(JDBC-URL `jdbc:h2:mem:wmdb`, Benutzer `sa`, kein Passwort).

## Daten

Seed-Daten basieren auf der Fussball-WM 2026 (reale Teams, Gruppen A–D).
Spieltag 1 und 2 sind gespielt, Spieltag 3 ist offen – diese Spiele können
über die Ergebniseingabe erfasst werden.

- `schema.sql` legt die Tabellen an.
- `data.sql` füllt die Seed-Daten.

## Endpoints

| Methode | Pfad | Beschreibung |
|---------|------|--------------|
| GET | `/api/teams` | Alle Teams |
| GET | `/api/matches` | Alle Spiele (optional `?group=A`) |
| PUT | `/api/matches/{id}/result` | Ergebnis erfassen (Body: `{ "homeGoals": 2, "awayGoals": 1 }`) |
| GET | `/api/standings/{group}` | Gruppentabelle (z.B. `/api/standings/A`) |

## Bewusst eingebaute Lücken (Ausgangslage)

- **Persistenz nur in H2 (In-Memory):** Die Daten gehen beim Neustart verloren.
  Das Issue C1 besteht darin, auf eine echte Datenbank (PostgreSQL) umzustellen.
- **Tabelle ohne Tiebreaker:** `StandingsService.getTable(...)` sortiert nur nach
  Punkten. Tordifferenz und weitere Kriterien fehlen.
- **Code-Smell:** Die Berechnung in `StandingsService` steckt in einer einzigen
  langen Methode mit unklaren Namen und parallelen Listen.
- **Keine Validierung:** `MatchService.updateResult(...)` übernimmt jede Eingabe
  ungeprüft (auch negative Tore).
- **Keine Containerisierung:** Es sind keine Dockerfiles vorhanden.

## Hinweise

- Es ist bewusst **kein** Spring Security eingebunden.
- `spring.jpa.hibernate.ddl-auto=validate`: Hibernate erzeugt das Schema nicht,
  sondern prüft die Entities gegen `schema.sql`.
- `@Autowired` ist an den Konstruktoren bewusst explizit gesetzt (zu Lehrzwecken).
  In der Praxis ist es bei genau einem Konstruktor optional.
