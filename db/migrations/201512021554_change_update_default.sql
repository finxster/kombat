ALTER TABLE Users
    MODIFY updated DEFAULT systimestamp;

ALTER TABLE Games
    MODIFY updated DEFAULT systimestamp;

ALTER TABLE Characters
    MODIFY updated DEFAULT systimestamp;

ALTER TABLE Users_Games
    MODIFY updated DEFAULT systimestamp;

ALTER TABLE User_Characters
    MODIFY updated DEFAULT systimestamp;

ALTER TABLE Gameplays
    MODIFY updated DEFAULT systimestamp;

ALTER TABLE Matches
    MODIFY updated DEFAULT systimestamp;


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