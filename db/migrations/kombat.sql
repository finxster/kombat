CREATE TABLE Users (
	id_user INT NOT NULL,
	name VARCHAR2(200) NOT NULL,
	email VARCHAR2(100) NOT NULL,
	experience NUMBER(19,0) DEFAULT 0,
	picture VARCHAR2(200),
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
	PRIMARY KEY(id_user),	
	CONSTRAINT unique_user_name UNIQUE(name),
	CONSTRAINT unique_user_email UNIQUE(email)
);

CREATE SEQUENCE users_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE Games (
	id_game INT NOT NULL,
	name VARCHAR2(100) NOT NULL,
	slug VARCHAR2(50) NOT NULL,
	picture VARCHAR2(200),
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
	PRIMARY KEY(id_game),
	CONSTRAINT unique_game_name UNIQUE(name),
	CONSTRAINT unique_game_slug UNIQUE(slug)
);

CREATE SEQUENCE games_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE Characters (
	id_character INT NOT NULL,
	name VARCHAR2(100) NOT NULL,
	picture VARCHAR2(200),
	game_id INT NOT NULL,
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
	PRIMARY KEY(id_character),	
	CONSTRAINT unique_game_character UNIQUE(game_id,name),
	CONSTRAINT fk_character_game
		FOREIGN KEY(game_id) 
		REFERENCES Games(id_game)
);

CREATE SEQUENCE characters_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE Users_Games (
	user_id INT NOT NULL,
	game_id INT NOT NULL,
	played NUMBER(11,0) DEFAULT 1,
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
	PRIMARY KEY(user_id,game_id),
	CONSTRAINT fk_user_game
		FOREIGN KEY(user_id) 
		REFERENCES Users(id_user),
	CONSTRAINT fk_game_user
		FOREIGN KEY(game_id) 
		REFERENCES Games(id_game)
);

CREATE TABLE User_Characters (
	id_user_character INT NOT NULL,
	user_id INT NOT NULL,
	character_id INT NOT NULL,
	played NUMBER(11,0) DEFAULT 1,
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
	PRIMARY KEY(id_user_character),
	CONSTRAINT fk_user_character
		FOREIGN KEY(user_id) 
		REFERENCES Users(id_user),
	CONSTRAINT fk_character_user
		FOREIGN KEY(character_id) 
		REFERENCES Characters(id_character)
);

CREATE SEQUENCE user_characters_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE Gameplays (
	id_gameplay INT NOT NULL,
	type CHAR CHECK(type in('T','S')),
	description VARCHAR2(200),
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
  PRIMARY KEY(id_gameplay)
);

CREATE SEQUENCE gameplays_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE TABLE Gameplay_Characters (
	gameplay_id INT NOT NULL,
	character_id INT NOT NULL,
	PRIMARY KEY(gameplay_id, character_id),
	CONSTRAINT fk_gameplay_characters
		FOREIGN KEY(gameplay_id) 
		REFERENCES Gameplays(id_gameplay),	
	CONSTRAINT fk_characters_gameplay
		FOREIGN KEY(character_id) 
		REFERENCES Characters(id_character)
);

CREATE TABLE Matches (
	id_match INT NOT NULL,
	gameplay_id INT NOT NULL,
	status CHAR CHECK(status in('S','C','F')),
	experience NUMBER(11,0) DEFAULT 100,
	created TIMESTAMP DEFAULT sysdate,
	updated TIMESTAMP,
	PRIMARY KEY(id_match),
	CONSTRAINT fk_match_gameplay
		FOREIGN KEY(gameplay_id) 
		REFERENCES Gameplays(id_gameplay)
);

CREATE TABLE Match_Players (
	match_id INT NOT NULL,
	user_character_id INT NOT NULL,
	winner NUMBER(11,0) DEFAULT 0,
	PRIMARY KEY(match_id,user_character_id),
	CONSTRAINT fk_match_players
		FOREIGN KEY(match_id) 
		REFERENCES Matches(id_match),	
	CONSTRAINT fk_players_match
		FOREIGN KEY(user_character_id) 
		REFERENCES User_Characters(id_user_character)
);

CREATE SEQUENCE matches_seq
 START WITH     1000
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

--- ROLLBACK --- 
DROP TABLE users_games;

DROP TABLE user_characters; 
DROP SEQUENCE user_characters_seq;

DROP TABLE users;
DROP SEQUENCE users_seq;

DROP TABLE gameplay_characters;

DROP TABLE gameplays;
DROP SEQUENCE gameplays_seq;

DROP TABLE characters;
DROP SEQUENCE characters_seq;

DROP TABLE games;
DROP SEQUENCE games_seq;

DROP TABLE matches;
DROP SEQUENCE matches_seq;

DROP TABLE match_players;