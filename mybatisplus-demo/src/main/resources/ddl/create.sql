CREATE TABLE `mbp_json` (
  `id` int NOT NULL  AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(256) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;





CREATE TABLE `mbp_dto` (
  `id` int NOT NULL  AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `contact` varchar(256) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `version` int NOT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;




CREATE TABLE `mbp_sample` (
  `id` int NOT NULL  AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `login_delete` int DEFAULT 0  NULL,
  `enum_int` int DEFAULT 1  NULL,
  `enum_str` char(1) DEFAULT 'a' NULL,
  `update_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `actor` (
  `id` int NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `film` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`),
KEY `idx_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;


CREATE TABLE `film_actor` (
  `id` int NOT NULL,
  `film_id` int NOT NULL,
  `actor_id` int NOT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
KEY `idx_film_actor_id` (`film_id`,`actor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3




