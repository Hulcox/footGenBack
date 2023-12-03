CREATE TABLE team
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(200),
    nickname VARCHAR(200),
    slogan VARCHAR(500),
    primary_color VARCHAR(10),
    secondary_color VARCHAR(20),
    logo VARCHAR(200),
    is_deletable BOOLEAN default 1
);

CREATE TABLE player
(
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(200),
    number INTEGER,
    position VARCHAR(100),
    team_id INTEGER,
    FOREIGN KEY (team_id) REFERENCES team(id)
);