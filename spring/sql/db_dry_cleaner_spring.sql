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

-- Дамп структуры базы данных db_dry_cleaner
CREATE DATABASE IF NOT EXISTS `db_dry_cleaner` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_dry_cleaner`;


-- Дамп структуры для таблица db_dry_cleaner.clothes
CREATE TABLE IF NOT EXISTS `clothes` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `model_id` int(10) unsigned NOT NULL,
  `price` double unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clothes_models` (`model_id`),
  CONSTRAINT `FK_clothes_models` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.clothes: ~29 rows (приблизительно)
DELETE FROM `clothes`;
/*!40000 ALTER TABLE `clothes` DISABLE KEYS */;
INSERT INTO `clothes` (`id`, `model_id`, `price`) VALUES
	(1, 1, 120000),
	(2, 2, 120000),
	(3, 3, 120000),
	(4, 4, 120000),
	(5, 5, 120000),
	(6, 6, 60000),
	(7, 7, 75000),
	(8, 8, 130000),
	(9, 9, 130000),
	(10, 10, 130000),
	(11, 11, 130000),
	(12, 12, 150000),
	(13, 13, 150000),
	(14, 14, 150000),
	(15, 15, 150000),
	(16, 16, 50000),
	(17, 17, 140000),
	(18, 18, 200000),
	(19, 19, 170000),
	(20, 20, 500000),
	(21, 21, 160000),
	(22, 22, 155000),
	(23, 23, 130000),
	(24, 24, 650000),
	(25, 25, 290000),
	(26, 26, 385000),
	(27, 27, 180000),
	(28, 28, 165000),
	(29, 29, 175000);
/*!40000 ALTER TABLE `clothes` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.models
CREATE TABLE IF NOT EXISTS `models` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK_models_types` (`type_id`),
  CONSTRAINT `FK_models_types` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.models: ~29 rows (приблизительно)
DELETE FROM `models`;
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` (`id`, `name`, `type_id`) VALUES
	(1, 'jeans', 1),
	(2, 'coveralls', 1),
	(3, 'sport pants', 1),
	(4, 'pants', 1),
	(5, 'shorts', 1),
	(6, 'ironing products', 2),
	(7, 'spot removing', 2),
	(8, 'vest', 3),
	(9, 'T-shirt', 3),
	(10, 'shirt', 3),
	(11, 'pullover ', 3),
	(12, 'sweater', 3),
	(13, 'bike ', 3),
	(14, 'jacket', 3),
	(15, 'blouse', 3),
	(16, 'tie', 3),
	(17, 'dress', 4),
	(18, 'evening dress', 4),
	(19, 'gown', 4),
	(20, 'dress with a sophisticated finish', 4),
	(21, 'sarafan', 4),
	(22, 'a long skirt with pleats', 4),
	(23, 'miniskirt ', 4),
	(24, 'wedding dress', 4),
	(25, 'ordering costumes', 5),
	(26, 'big costumes', 5),
	(27, 'tail-coat', 5),
	(28, 'dinner jacket', 5),
	(29, 'windcheater', 5);
/*!40000 ALTER TABLE `models` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.orders
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.orders: ~5 rows (приблизительно)
DELETE FROM `orders`;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` (`id`, `ordering_day`, `quantity`, `clother_id`, `state_id`, `user_id`) VALUES
	(1, '2015-08-24', 1, 1, 5, 1),
	(2, '2015-08-27', 1, 14, 4, 2),
	(3, '2015-08-29', 1, 28, 3, 2),
	(4, '2015-09-01', 1, 17, 1, 1),
	(5, '2015-09-11', 1, 4, 1, 2),
	(6, '2015-10-07', 1, 25, 1, 18);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.roles: ~3 rows (приблизительно)
DELETE FROM `roles`;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(2, 'admin'),
	(3, 'manager'),
	(1, 'user');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.states
CREATE TABLE IF NOT EXISTS `states` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `state` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rdb7pnbo5e3l4vc5bkpk5q6t1` (`state`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.states: ~5 rows (приблизительно)
DELETE FROM `states`;
/*!40000 ALTER TABLE `states` DISABLE KEYS */;
INSERT INTO `states` (`id`, `state`) VALUES
	(2, 'accept'),
	(5, 'closed'),
	(1, 'create'),
	(4, 'finished'),
	(3, 'in progress');
/*!40000 ALTER TABLE `states` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.types
CREATE TABLE IF NOT EXISTS `types` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.types: ~5 rows (приблизительно)
DELETE FROM `types`;
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` (`id`, `name`) VALUES
	(5, 'Costumes'),
	(4, 'Dresses and skirts'),
	(2, 'Extended services'),
	(1, 'Jeans, trousers, shorts'),
	(3, 'Shirts , T-shirts');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.users
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

-- Дамп данных таблицы db_dry_cleaner.users: ~2 rows (приблизительно)
DELETE FROM `users`;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `second_name`, `telephone`) VALUES
	(1, 'boris@mail.ru', '4dbf44c6b1be736ee92ef90090452fc2', 'Boris', 'Alekhno', 375291234567),
	(2, 'andrew@tut.by', '4dbf44c6b1be736ee92ef90090452fc2', 'Andrew', 'Pupkin', 375331234567),
	(18, 'test', 'test', 'Test', 'Test', 1);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Дамп структуры для таблица db_dry_cleaner.user_roles
CREATE TABLE IF NOT EXISTS `user_roles` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) unsigned NOT NULL,
  `role_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user_roles_roles` (`role_id`),
  KEY `FK_user_roles_users` (`user_id`),
  CONSTRAINT `FK_user_roles_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_user_roles_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы db_dry_cleaner.user_roles: ~3 rows (приблизительно)
DELETE FROM `user_roles`;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
INSERT INTO `user_roles` (`id`, `user_id`, `role_id`) VALUES
	(1, 2, 1),
	(2, 1, 1),
	(3, 1, 2),
	(13, 18, 1);
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
