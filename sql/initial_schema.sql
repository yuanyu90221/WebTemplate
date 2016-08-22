DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(50) NOT NULL UNIQUE,
  `password` varchar(100) NOT NULL,
  `enabled` char(1) NOT NULL,
  `create_time` date NOT NULL,
  `last_signin_time` datetime DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `user_id` int(10) NOT NULL,
  `role` varchar(10) NOT NULL,
  CONSTRAINT unique_role UNIQUE (`user_id`, `role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;