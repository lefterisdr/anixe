
CREATE TABLE `hotel` (
  `hotel_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `address` VARCHAR(100) NOT NULL,
  `star_rating` ENUM('ONE_STAR', 'TWO_STAR', 'THREE_STAR', 'FOUR_STAR', 'FIVE_STAR', 'LUXURY') NOT NULL,
  PRIMARY KEY (`hotel_id`));

CREATE TABLE `booking` (
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
  REFERENCES `hotel` (`hotel_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);