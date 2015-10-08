-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.6.22-log - MySQL Community Server (GPL)
-- ОС Сервера:                   Win64
-- HeidiSQL Версия:              8.3.0.4694
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Дамп структуры базы данных test_dry_cleaner
CREATE DATABASE IF NOT EXISTS `test_dry_cleaner` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test_dry_cleaner`;


-- Дамп структуры для таблица test_dry_cleaner.clothes
CREATE TABLE IF NOT EXISTS `clothes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `model_id` int(10) unsigned NOT NULL,
  `price` double unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clothes_models` (`model_id`),
  CONSTRAINT `FK_clothes_models` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.clothes: ~9 rows (приблизительно)
DELETE FROM `clothes`;
/*!40000 ALTER TABLE `clothes` DISABLE KEYS */;
INSERT INTO `clothes` (`id`, `model_id`, `price`) VALUES
	(1, 1, 100000),
	(2, 2, 200000),
	(3, 3, 50000),
	(4, 4, 90000),
	(5, 5, 110000);
/*!40000 ALTER TABLE `clothes` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.models
CREATE TABLE IF NOT EXISTS `models` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK_models_types` (`type_id`),
  CONSTRAINT `FK_models_types` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.models: ~29 rows (приблизительно)
DELETE FROM `models`;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` (`id`, `name`, `type_id`) VALUES
	(1, 'thin jeans', 1),
	(2, 'heavy jeans', 1),
	(3, 'cotton collor shirt', 2),
	(4, 'white shirt', 2),
	(5, 'nightshirt', 2);
/*!40000 ALTER TABLE `models` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `ordering_day` date NOT NULL,
  `quantity` int(11) unsigned NOT NULL,
  `clother_id` int(11) unsigned NOT NULL,
  `state_id` int(11) unsigned NOT NULL,
  `user_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orders_clothes` (`clother_id`),
  KEY `FK_orders_users` (`user_id`),
  KEY `FK_orders_states` (`state_id`),
  CONSTRAINT `FK_orders_clothes` FOREIGN KEY (`clother_id`) REFERENCES `clothes` (`id`),
  CONSTRAINT `FK_orders_states` FOREIGN KEY (`state_id`) REFERENCES `states` (`id`),
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.orders: ~5 rows (приблизительно)
DELETE FROM `orders`;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `ordering_day`, `quantity`, `clother_id`, `state_id`, `user_id`) VALUES
	(1, '2015-08-11', 1, 3, 1, 1),
	(2, '2015-08-09', 2, 5, 1, 2),
	(3, '2015-08-15', 1, 3, 2, 2);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.roles: ~3 rows (приблизительно)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(2, 'admin'),
	(1, 'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.states
CREATE TABLE IF NOT EXISTS `states` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `state` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rdb7pnbo5e3l4vc5bkpk5q6t1` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.states: ~5 rows (приблизительно)
DELETE FROM `states`;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` (`id`, `state`) VALUES
	(1, 'create'),
	(2, 'processed');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.types
CREATE TABLE IF NOT EXISTS `types` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.types: ~0 rows (приблизительно)
DELETE FROM `types`;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` (`id`, `name`) VALUES
	(1, 'jeans'),
	(2, 'shirt');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `telephone` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.users: ~0 rows (приблизительно)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `second_name`, `telephone`) VALUES
	(1, 'boris@mail.ru', '123', 'Boris', 'Alekhno', 375292766536),
	(2, 'test@mail.ru', '321', 'Andrew', 'Pupkin', 375295592117);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Дамп структуры для таблица test_dry_cleaner.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `role_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_roles_roles` (`role_id`),
  KEY `FK_user_roles_users` (`user_id`),
  CONSTRAINT `FK_user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_user_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы test_dry_cleaner.user_roles: ~0 rows (приблизительно)
DELETE FROM `user_roles`;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`id`, `user_id`, `role_id`) VALUES
	(1, 1, 1),
	(2, 2, 1),
	(3, 1, 2);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
