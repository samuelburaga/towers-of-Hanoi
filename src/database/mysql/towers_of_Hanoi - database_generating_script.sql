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
  `user_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table stores user information.',
  `first_name` VARCHAR(30) NOT NULL,
  `last_name` VARCHAR(30) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  `password` CHAR(25) NOT NULL,
  `created_at` DATETIME NOT NULL COMMENT 'This table contains the basic columns used for the login process. The table could be updated so that the password is salted and hashed to assure security.',
  `profile_picture` LONGBLOB NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `password_UNIQUE` (`password` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `towers-of-Hanoi`.`statistics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `towers-of-Hanoi`.`statistics` (
  `score_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table stores some data about user games.',
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


-- -----------------------------------------------------
-- Table `towers-of-Hanoi`.`complaints`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `towers-of-Hanoi`.`complaints` (
  `complaint_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table can store information about individual game sessions, including the user who played the game, the start and end times of the session, and the duration of the session.',
  `users_user_id` INT UNSIGNED NOT NULL,
  `complaint` VARCHAR(50) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`complaint_id`),
  UNIQUE INDEX `session_id_UNIQUE` (`complaint_id` ASC) VISIBLE,
  INDEX `fk_games_sessions_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_games_sessions_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `towers-of-Hanoi`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `towers-of-Hanoi`.`notifications`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `towers-of-Hanoi`.`notifications` (
  `notification_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table can be used to maintain a leaderboard of the top scores achieved by users in the game. It would store the user\'s ID, their score, the rank on the leaderboard, and the date when the score was achieved.',
  `users_user_id` INT UNSIGNED NOT NULL,
  `notification` VARCHAR(50) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`notification_id`),
  UNIQUE INDEX `leaderboard_id_UNIQUE` (`notification_id` ASC) VISIBLE,
  INDEX `fk_leaderboard_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_leaderboard_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `towers-of-Hanoi`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `towers-of-Hanoi`.`achievements`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `towers-of-Hanoi`.`achievements` (
  `achievements_id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'This table can track the achievements earned by users while playing the game. It would store the user\'s ID, the name of the achievement, and the date when the achievement was earned.',
  `users_user_id` INT UNSIGNED NOT NULL,
  `achievement` VARCHAR(20) NOT NULL,
  `date` DATE NOT NULL,
  PRIMARY KEY (`achievements_id`),
  UNIQUE INDEX `achievements_id_UNIQUE` (`achievements_id` ASC) VISIBLE,
  INDEX `fk_achievements_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_achievements_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `towers-of-Hanoi`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
