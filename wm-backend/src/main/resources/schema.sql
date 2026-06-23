
CREATE TABLE IF NOT EXISTS team (
    id         BIGINT       NOT NULL PRIMARY KEY,
    name       VARCHAR(255) NOT NULL,
    team_group VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS wm_match (
    id           BIGINT       NOT NULL PRIMARY KEY,
    home_team_id BIGINT       NOT NULL,
    away_team_id BIGINT       NOT NULL,
    match_group  VARCHAR(255) NOT NULL,
    home_goals   INTEGER,
    away_goals   INTEGER
);
