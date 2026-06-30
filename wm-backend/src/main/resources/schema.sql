
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
    away_goals   INTEGER,
    match_type VARCHAR(20) NOT NULL DEFAULT 'GROUP',
    fair_play_home INTEGER NOT NULL DEFAULT 0,
    fair_play_away INTEGER NOT NULL DEFAULT 0,
    matchday INTEGER
);
