UPDATE Users SET updated = created WHERE updated is null;
ALTER TABLE Users
    MODIFY updated DEFAULT systimestamp NOT NULL;

UPDATE Games SET updated = created WHERE updated is null;
ALTER TABLE Games
    MODIFY updated DEFAULT systimestamp NOT NULL;

UPDATE Characters SET updated = created WHERE updated is null;
ALTER TABLE Characters
    MODIFY updated DEFAULT systimestamp NOT NULL;

UPDATE Users_Games SET updated = created WHERE updated is null;
ALTER TABLE Users_Games
    MODIFY updated DEFAULT systimestamp NOT NULL;

UPDATE User_Characters SET updated = created WHERE updated is null;
ALTER TABLE User_Characters
    MODIFY updated DEFAULT systimestamp NOT NULL;

UPDATE Gameplays SET updated = created WHERE updated is null;
ALTER TABLE Gameplays
    MODIFY updated DEFAULT systimestamp NOT NULL;

UPDATE Matches SET updated = created WHERE updated is null;
ALTER TABLE Matches
    MODIFY updated DEFAULT systimestamp NOT NULL;


---
ALTER TABLE Users
    MODIFY updated DEFAULT NULL;

ALTER TABLE Games
    MODIFY updated DEFAULT NULL;

ALTER TABLE Characters
    MODIFY updated DEFAULT NULL;

ALTER TABLE Users_Games
    MODIFY updated DEFAULT NULL;

ALTER TABLE User_Characters
    MODIFY updated DEFAULT NULL;

ALTER TABLE Gameplays
    MODIFY updated DEFAULT NULL;

ALTER TABLE Matches
    MODIFY updated DEFAULT NULL;