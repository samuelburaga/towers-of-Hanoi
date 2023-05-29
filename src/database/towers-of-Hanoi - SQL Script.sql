-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema towers-of-Hanoi
-- -----------------------------------------------------
-- This database contains tables used to store user data and other information related to the classic game "Towers of Hanoi" made by myself using JavaFX. The database will be connected to Java using the MySQL Connector/J driver.

-- -----------------------------------------------------
-- Schema towers-of-Hanoi
--
-- This database contains tables used to store user data and other information related to the classic game "Towers of Hanoi" made by myself using JavaFX. The database will be connected to Java using the MySQL Connector/J driver.
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `towers-of-Hanoi` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `towers-of-Hanoi` ;

-- -----------------------------------------------------
-- Table `towers-of-Hanoi`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `towers-of-Hanoi`.`users` (
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` CHAR(25) NOT NULL,
  `created_at` DATETIME NOT NULL COMMENT 'This table contains the basic columns used for the login process. The table could be updated so that the password is salted and hashed to assure security.',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `towers-of-Hanoi`.`statistics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `towers-of-Hanoi`.`statistics` (
  `score_id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `users_user_id` INT UNSIGNED NOT NULL,
  `disks` INT UNSIGNED NOT NULL COMMENT 'This table is used to store the user\'s game statistics.',
  `points` INT UNSIGNED NOT NULL,
  `time` TIME NOT NULL,
  PRIMARY KEY (`score_id`, `users_user_id`),
  UNIQUE INDEX `score_id_UNIQUE` (`score_id` ASC) VISIBLE,
  INDEX `fk_statistics_users_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_statistics_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `towers-of-Hanoi`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
