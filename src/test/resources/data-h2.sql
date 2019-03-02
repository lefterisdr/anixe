INSERT INTO `hotel` (`name`, `address`, `star_rating`) VALUES ('Grande Bretagne', 'Syntagma', 'FOUR_STAR');
INSERT INTO `hotel` (`name`, `address`, `star_rating`) VALUES ('King George', 'Syntagma', 'LUXURY');
INSERT INTO `hotel` (`name`, `address`, `star_rating`) VALUES ('President', 'Panormou', 'THREE_STAR');
INSERT INTO `hotel` (`name`, `address`, `star_rating`) VALUES ('Hilton', 'Kifissias', 'FIVE_STAR');

INSERT INTO `booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('2', 'EUR', 'Tom', 'Hanks', '3', '234');
INSERT INTO `booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('2', 'EUR', 'Betty', 'Middler', '7', '1024');
INSERT INTO `booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('1', 'USD', 'Carol', 'Danvers', '1', '555');
INSERT INTO `booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('3', 'AUD', 'Aaron', 'Hanks', '4', '480');
INSERT INTO `booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('2', 'EUR', 'Tom', 'Jerry', '2', '860');
INSERT INTO `booking` (`hotel_id`, `currency`, `customer_name`, `customer_surname`, `number_of_pax`, `price_amount`) VALUES ('1', 'USD', 'Jon', 'Snow', '2', '773');
