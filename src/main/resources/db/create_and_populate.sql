CREATE SCHEMA anixe;

CREATE TABLE `anixe`.`hotel` (
  `hotel_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `star_rating` ENUM('ONE_STAR', 'TWO_STAR', 'THREE_STAR', 'FOUR_STAR', 'FIVE_STAR', 'LUXURY') NOT NULL,
  PRIMARY KEY (`hotel_id`));

CREATE TABLE `anixe`.`booking` (
  `booking_id` INT NOT NULL AUTO_INCREMENT,
  `hotel_id` INT NOT NULL,
  `currency` ENUM('EUR', 'USD', 'AUD', 'JPY', 'BTC', 'CAD', 'GBP') NOT NULL,
  `customer_name` VARCHAR(30) NOT NULL,
  `customer_surname` VARCHAR(50) NOT NULL,
  `number_of_pax` INT NOT NULL,
  `price_amount` DOUBLE NOT NULL,
  PRIMARY KEY (`booking_id`),
  CONSTRAINT `HOTEL_FK`
  FOREIGN KEY (`hotel_id`)
  REFERENCES `anixe`.`hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

INSERT INTO `anixe`.`hotel` (`name`, `address`, `star_rating`) VALUES ('Grande Bretagne', 'Syntagma', 'FOUR_STAR');
INSERT INTO `anixe`.`hotel` (`name`, `address`, `star_rating`) VALUES ('King George', 'Syntagma', 'LUXURY');
INSERT INTO `anixe`.`hotel` (`name`, `address`, `star_rating`) VALUES ('President', 'Panormou', 'THREE_STAR');
INSERT INTO `anixe`.`hotel` (`name`, `address`, `star_rating`) VALUES ('Hilton', 'Kifissias', 'FIVE_STAR');

INSERT INTO `anixe`.`booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('2', 'EUR', 'Tom', 'Hanks', '3', '234');
INSERT INTO `anixe`.`booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('2', 'EUR', 'Betty', 'Middler', '7', '1024');
INSERT INTO `anixe`.`booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('1', 'USD', 'Carol', 'Danvers', '1', '555');
INSERT INTO `anixe`.`booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('3', 'AUD', 'Aaron', 'Hanks', '4', '480');
INSERT INTO `anixe`.`booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('2', 'EUR', 'Tom', 'Jerry', '2', '860');
INSERT INTO `anixe`.`booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('1', 'USD', 'Jon', 'Snow', '2', '773');
