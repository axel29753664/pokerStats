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

CREATE TABLE `roles` (
  `name` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `Roles_name_uindex` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `users` (
  `name`     VARCHAR(50) NOT NULL,
  `password` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`name`),
  UNIQUE KEY `Users_name_uindex` (`name`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE `user_roles` (
  `user` VARCHAR(50) NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  KEY `user_roles_users_name_fk` (`user`),
  KEY `user_roles_roles_name_fk` (`role`),
  CONSTRAINT `user_roles_roles_name_fk` FOREIGN KEY (`role`) REFERENCES `roles` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `user_roles_users_name_fk` FOREIGN KEY (`user`) REFERENCES `users` (`name`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

