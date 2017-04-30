CREATE TABLE `games` (
  `id`   BIGINT(20) NOT NULL AUTO_INCREMENT,
  `date` DATETIME   NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 47
  DEFAULT CHARSET = utf8;

CREATE TABLE `players` (
  `id`   BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `players_name_uindex` (`name`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8;

CREATE TABLE `player_places` (
  `player_id` BIGINT(20) NOT NULL,
  `game_id`   BIGINT(20) NOT NULL,
  `place`     INT(11) DEFAULT NULL,
  PRIMARY KEY (`player_id`, `game_id`),
  KEY `player_places_games_id_fk` (`game_id`),
  CONSTRAINT `player_places_games_id_fk` FOREIGN KEY (`game_id`) REFERENCES `games` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `player_places_players_id_fk` FOREIGN KEY (`player_id`) REFERENCES `players` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `rated_places` (
  `players_quantity`      INT(11) NOT NULL,
  `rated_places_quantity` INT(11) DEFAULT NULL,
  PRIMARY KEY (`players_quantity`),
  UNIQUE KEY `rated_places_players_quantity_pk` (`players_quantity`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `history` (
  `id`          BIGINT(20)  NOT NULL AUTO_INCREMENT,
  `date`        DATETIME    NOT NULL,
  `user_name`   VARCHAR(50) NOT NULL,
  `action`      VARCHAR(10)          DEFAULT NULL,
  `description` VARCHAR(400)         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 67
  DEFAULT CHARSET = utf8;

CREATE TABLE `roles` (
  `name` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `Roles_name_uindex` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE users
(
  id       BIGINT        NOT NULL AUTO_INCREMENT
    PRIMARY KEY,
  name     VARBINARY(50) NOT NULL,
  password VARCHAR(60)   NOT NULL,

  CONSTRAINT Users_name_uindex
  UNIQUE (name)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;


CREATE TABLE user_roles
(
  user_id BIGINT      NOT NULL,
  role    VARCHAR(10) NOT NULL,
  CONSTRAINT user_id_roles_users_id_fk
  FOREIGN KEY (user_id) REFERENCES users (id)
    ON UPDATE CASCADE
    ON DELETE CASCADE,
  CONSTRAINT user_roles_roles_name_fk
  FOREIGN KEY (role) REFERENCES roles (name)
    ON UPDATE CASCADE
    ON DELETE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE INDEX user_id_roles_users_id_fk
  ON user_roles (user_id);

CREATE INDEX user_roles_roles_name_fk
  ON user_roles (role);


