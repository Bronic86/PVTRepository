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

-- Дамп структуры базы данных drycleanerdb
CREATE DATABASE IF NOT EXISTS `drycleanerdb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `drycleanerdb`;


-- Дамп структуры для таблица drycleanerdb.clothes
CREATE TABLE IF NOT EXISTS `clothes` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `model_id` bigint(20) unsigned NOT NULL,
  `price` decimal(10,0) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_clothes_models` (`model_id`),
  CONSTRAINT `FK_clothes_models` FOREIGN KEY (`model_id`) REFERENCES `models` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.clothes: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `clothes` DISABLE KEYS */;
INSERT INTO `clothes` (`id`, `model_id`, `price`) VALUES
	(1, 1, 55000),
	(5, 2, 55000),
	(6, 1, 20000);
/*!40000 ALTER TABLE `clothes` ENABLE KEYS */;


-- Дамп структуры для таблица drycleanerdb.models
CREATE TABLE IF NOT EXISTS `models` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `type_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_models_types` (`type_id`),
  CONSTRAINT `FK_models_types` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.models: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `models` DISABLE KEYS */;
INSERT INTO `models` (`id`, `name`, `type_id`) VALUES
	(1, 'thin jeans', 1),
	(2, 'winter jeans', 1);
/*!40000 ALTER TABLE `models` ENABLE KEYS */;


-- Дамп структуры для таблица drycleanerdb.orders
CREATE TABLE IF NOT EXISTS `orders` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `clother_id` bigint(20) unsigned NOT NULL,
  `quantity` bigint(20) unsigned NOT NULL,
  `ordering_day` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_orders_clothes` (`clother_id`),
  KEY `FK_orders_users` (`user_id`),
  CONSTRAINT `FK_orders_clothes` FOREIGN KEY (`clother_id`) REFERENCES `clothes` (`id`),
  CONSTRAINT `FK_orders_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.orders: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;


-- Дамп структуры для таблица drycleanerdb.roles
CREATE TABLE IF NOT EXISTS `roles` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.roles: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` (`id`, `name`) VALUES
	(1, 'user'),
	(2, 'admin');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;


-- Дамп структуры для таблица drycleanerdb.types
CREATE TABLE IF NOT EXISTS `types` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.types: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `types` DISABLE KEYS */;
INSERT INTO `types` (`id`, `name`) VALUES
	(1, 'jeans'),
	(2, 'trousers'),
	(3, 'shirt'),
	(4, 'fur coat'),
	(5, 'bedclothes');
/*!40000 ALTER TABLE `types` ENABLE KEYS */;


-- Дамп структуры для таблица drycleanerdb.users
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `login` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `second_name` varchar(50) NOT NULL,
  `telephone` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `client_login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.users: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `first_name`, `second_name`, `telephone`) VALUES
	(1, 'boris', '123', 'Boris', 'Alekhno', 375291234567),
	(2, 'andrew', '123', 'Andrew', 'Pushkin', 375331234567);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;


-- Дамп структуры для таблица drycleanerdb.users_role
CREATE TABLE IF NOT EXISTS `users_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) unsigned NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_users_role_users` (`user_id`),
  KEY `FK_users_role_roles` (`role_id`),
  CONSTRAINT `FK_users_role_roles` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`),
  CONSTRAINT `FK_users_role_users` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы drycleanerdb.users_role: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `users_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_role` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
