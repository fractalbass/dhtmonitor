CREATE TABLE `dhtmonitor`.`measurement` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `sensor` VARCHAR(45) NOT NULL,
  `attribute` VARCHAR(45) NOT NULL,
  `val` DECIMAL(10,2) NOT NULL,
  `serverts` BIGINT(20) NOT NULL,
  PRIMARY KEY (`id`));