-- Seed-Daten WM 2026 (reale Teams, alle 12 Gruppen A-L, 48 Teams).
-- Stand: 23. Juni 2026 - Spieltag 1 und 2 gespielt, Spieltag 3 offen (NULL).
--
-- Hinweis: Gruppen-Einteilung und Tabellenstaende (Punkte/Tordifferenz)
-- entsprechen dem realen Turnierverlauf. Einzelne Spielresultate sind so
-- gesetzt, dass sie zur realen Tabelle passen; nicht jedes Einzelergebnis
-- ist zwingend das exakte reale Resultat. Spieltag 3 ist bewusst offen.

-- ============== TEAMS ==============
-- Gruppe A
INSERT INTO team (id, name, team_group) VALUES (1, 'Mexiko', 'A');
INSERT INTO team (id, name, team_group) VALUES (2, 'Suedafrika', 'A');
INSERT INTO team (id, name, team_group) VALUES (3, 'Suedkorea', 'A');
INSERT INTO team (id, name, team_group) VALUES (4, 'Tschechien', 'A');
-- Gruppe B
INSERT INTO team (id, name, team_group) VALUES (5, 'Kanada', 'B');
INSERT INTO team (id, name, team_group) VALUES (6, 'Bosnien-Herzegowina', 'B');
INSERT INTO team (id, name, team_group) VALUES (7, 'Katar', 'B');
INSERT INTO team (id, name, team_group) VALUES (8, 'Schweiz', 'B');
-- Gruppe C
INSERT INTO team (id, name, team_group) VALUES (9, 'Brasilien', 'C');
INSERT INTO team (id, name, team_group) VALUES (10, 'Marokko', 'C');
INSERT INTO team (id, name, team_group) VALUES (11, 'Haiti', 'C');
INSERT INTO team (id, name, team_group) VALUES (12, 'Schottland', 'C');
-- Gruppe D
INSERT INTO team (id, name, team_group) VALUES (13, 'USA', 'D');
INSERT INTO team (id, name, team_group) VALUES (14, 'Paraguay', 'D');
INSERT INTO team (id, name, team_group) VALUES (15, 'Australien', 'D');
INSERT INTO team (id, name, team_group) VALUES (16, 'Tuerkiye', 'D');
-- Gruppe E
INSERT INTO team (id, name, team_group) VALUES (17, 'Deutschland', 'E');
INSERT INTO team (id, name, team_group) VALUES (18, 'Curacao', 'E');
INSERT INTO team (id, name, team_group) VALUES (19, 'Elfenbeinkueste', 'E');
INSERT INTO team (id, name, team_group) VALUES (20, 'Ecuador', 'E');
-- Gruppe F
INSERT INTO team (id, name, team_group) VALUES (21, 'Niederlande', 'F');
INSERT INTO team (id, name, team_group) VALUES (22, 'Japan', 'F');
INSERT INTO team (id, name, team_group) VALUES (23, 'Schweden', 'F');
INSERT INTO team (id, name, team_group) VALUES (24, 'Tunesien', 'F');
-- Gruppe G
INSERT INTO team (id, name, team_group) VALUES (25, 'Belgien', 'G');
INSERT INTO team (id, name, team_group) VALUES (26, 'Aegypten', 'G');
INSERT INTO team (id, name, team_group) VALUES (27, 'Iran', 'G');
INSERT INTO team (id, name, team_group) VALUES (28, 'Neuseeland', 'G');
-- Gruppe H
INSERT INTO team (id, name, team_group) VALUES (29, 'Spanien', 'H');
INSERT INTO team (id, name, team_group) VALUES (30, 'Kap Verde', 'H');
INSERT INTO team (id, name, team_group) VALUES (31, 'Saudi-Arabien', 'H');
INSERT INTO team (id, name, team_group) VALUES (32, 'Uruguay', 'H');
-- Gruppe I
INSERT INTO team (id, name, team_group) VALUES (33, 'Frankreich', 'I');
INSERT INTO team (id, name, team_group) VALUES (34, 'Senegal', 'I');
INSERT INTO team (id, name, team_group) VALUES (35, 'Irak', 'I');
INSERT INTO team (id, name, team_group) VALUES (36, 'Norwegen', 'I');
-- Gruppe J
INSERT INTO team (id, name, team_group) VALUES (37, 'Argentinien', 'J');
INSERT INTO team (id, name, team_group) VALUES (38, 'Algerien', 'J');
INSERT INTO team (id, name, team_group) VALUES (39, 'Oesterreich', 'J');
INSERT INTO team (id, name, team_group) VALUES (40, 'Jordanien', 'J');
-- Gruppe K
INSERT INTO team (id, name, team_group) VALUES (41, 'Portugal', 'K');
INSERT INTO team (id, name, team_group) VALUES (42, 'DR Kongo', 'K');
INSERT INTO team (id, name, team_group) VALUES (43, 'Usbekistan', 'K');
INSERT INTO team (id, name, team_group) VALUES (44, 'Kolumbien', 'K');
-- Gruppe L
INSERT INTO team (id, name, team_group) VALUES (45, 'England', 'L');
INSERT INTO team (id, name, team_group) VALUES (46, 'Kroatien', 'L');
INSERT INTO team (id, name, team_group) VALUES (47, 'Ghana', 'L');
INSERT INTO team (id, name, team_group) VALUES (48, 'Panama', 'L');

-- ============== MATCHES ==============
-- Pro Gruppe: Spieltag 1 und 2 gespielt, Spieltag 3 offen (NULL).
-- Match-IDs: Gruppe A=101.., B=111.., usw. (10er-Bloecke pro Gruppe)

-- ----- Gruppe A (Mexiko 6 / Suedkorea 3 / Tschechien 1 / Suedafrika 1) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (101, 1, 2, 'A', 2, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (102, 3, 4, 'A', 2, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (103, 4, 2, 'A', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (104, 1, 3, 'A', 1, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (105, 4, 1, 'A', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (106, 2, 3, 'A', NULL, NULL);

-- ----- Gruppe B (Kanada 4/+6, Schweiz 4/+3, Bosnien 1/-3, Katar 1/-6) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (111, 5, 6, 'B', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (112, 7, 8, 'B', 0, 3);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (113, 8, 6, 'B', 3, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (114, 5, 7, 'B', 5, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (115, 8, 5, 'B', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (116, 6, 7, 'B', NULL, NULL);

-- ----- Gruppe C (Brasilien 4/+3, Marokko 4/+1, Schottland 3/0, Haiti 0/-4) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (121, 9, 10, 'C', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (122, 11, 12, 'C', 0, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (123, 12, 10, 'C', 1, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (124, 9, 11, 'C', 3, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (125, 12, 9, 'C', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (126, 10, 11, 'C', NULL, NULL);

-- ----- Gruppe D (USA 6/+5, Australien 3/0, Paraguay 3/-2, Tuerkiye 0/-3) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (131, 13, 14, 'D', 2, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (132, 15, 16, 'D', 1, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (133, 16, 14, 'D', 1, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (134, 13, 15, 'D', 3, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (135, 16, 13, 'D', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (136, 14, 15, 'D', NULL, NULL);

-- ----- Gruppe E (Deutschland 6/+7, Elfenbeinkueste 3/0, Ecuador 1/-1, Curacao 1/-6) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (141, 17, 18, 'E', 4, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (142, 19, 20, 'E', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (143, 20, 18, 'E', 2, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (144, 17, 19, 'E', 3, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (145, 20, 17, 'E', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (146, 18, 19, 'E', NULL, NULL);

-- ----- Gruppe F (Niederlande 4/+4, Japan 4/+4, Schweden 3/0, Tunesien 0/-8) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (151, 21, 22, 'F', 2, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (152, 23, 24, 'F', 3, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (153, 24, 22, 'F', 0, 4);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (154, 21, 23, 'F', 2, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (155, 24, 21, 'F', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (156, 22, 23, 'F', NULL, NULL);

-- ----- Gruppe G (Aegypten 4/+2, Iran 2/0, Belgien 2/0, Neuseeland 1/-2) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (161, 25, 26, 'G', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (162, 27, 28, 'G', 2, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (163, 28, 26, 'G', 0, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (164, 25, 27, 'G', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (165, 28, 25, 'G', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (166, 26, 27, 'G', NULL, NULL);

-- ----- Gruppe H (Spanien 4/+4, Uruguay 2/0, Kap Verde 2/0, Saudi-Arabien 1/-4) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (171, 29, 30, 'H', 3, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (172, 31, 32, 'H', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (173, 32, 30, 'H', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (174, 29, 31, 'H', 2, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (175, 32, 29, 'H', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (176, 30, 31, 'H', NULL, NULL);

-- ----- Gruppe I (Norwegen 3/+3, Frankreich 3/+2, Senegal 0/-2, Irak 0/-3) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (181, 33, 34, 'I', 2, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (182, 35, 36, 'I', 0, 3);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (183, 36, 34, 'I', 0, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (184, 33, 35, 'I', 0, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (185, 36, 33, 'I', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (186, 34, 35, 'I', NULL, NULL);

-- ----- Gruppe J (Argentinien 3/+3, Oesterreich 3/+2, Jordanien 0/-2, Algerien 0/-3) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (191, 37, 38, 'J', 3, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (192, 39, 40, 'J', 2, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (193, 40, 38, 'J', 0, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (194, 37, 39, 'J', 0, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (195, 40, 37, 'J', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (196, 38, 39, 'J', NULL, NULL);

-- ----- Gruppe K (Kolumbien 3/+2, DR Kongo 1/0, Portugal 1/0, Usbekistan 0/-2) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (201, 41, 42, 'K', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (202, 43, 44, 'K', 0, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (203, 44, 42, 'K', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (204, 41, 43, 'K', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (205, 44, 41, 'K', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (206, 42, 43, 'K', NULL, NULL);

-- ----- Gruppe L (England 3/+2, Ghana 3/+1, Panama 0/-1, Kroatien 0/-2) -----
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (211, 45, 46, 'L', 2, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (212, 47, 48, 'L', 1, 0);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (213, 48, 46, 'L', 1, 2);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (214, 45, 47, 'L', 1, 1);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (215, 48, 45, 'L', NULL, NULL);
INSERT INTO wm_match (id, home_team_id, away_team_id, match_group, home_goals, away_goals) VALUES (216, 46, 47, 'L', NULL, NULL);