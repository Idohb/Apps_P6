-- MySQL Script generated by MySQL Workbench
-- Fri Dec 16 17:29:48 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dbp6
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `dbp6` ;

-- -----------------------------------------------------
-- Schema dbp6
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dbp6` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dbp6` ;

-- -----------------------------------------------------
-- Table `dbp6`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbp6`.`user` ;

CREATE TABLE IF NOT EXISTS `dbp6`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `balance` DOUBLE ZEROFILL NOT NULL,
  PRIMARY KEY (`id_user`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbp6`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbp6`.`transaction` ;

CREATE TABLE IF NOT EXISTS `dbp6`.`transaction` (
  `id_transaction` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  `amount_transaction` DOUBLE NOT NULL,
  `time_transaction` DATETIME NOT NULL,
  `commission` DOUBLE ZEROFILL NOT NULL,
  `creditor` INT NOT NULL,
  `debtor` INT NOT NULL,
  `type` ENUM('P', 'D', 'C'),
  PRIMARY KEY (`id_transaction`),
  INDEX `fk_creditor_user_idx` (`creditor` ASC) VISIBLE,
  INDEX `fk_debtor_user_idx` (`debtor` ASC) VISIBLE,
  CONSTRAINT `fk_creditor_user`
    FOREIGN KEY (`creditor`)
    REFERENCES `dbp6`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_debtor_user`
    FOREIGN KEY (`debtor`)
    REFERENCES `dbp6`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbp6`.`bank`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbp6`.`bank` ;

CREATE TABLE IF NOT EXISTS `dbp6`.`bank` (
  `id_bank` INT NOT NULL AUTO_INCREMENT,
  `iban` VARCHAR(45) NOT NULL,
  `amount_bank` DOUBLE NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id_bank`),
  INDEX `fk_user_id_person_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_id_person_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `dbp6`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `dbp6`.`friend`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbp6`.`friend` ;

CREATE TABLE IF NOT EXISTS `dbp6`.`friend` (
  `user_iduser` INT NOT NULL,
  `user_iduser1` INT NOT NULL,
  PRIMARY KEY (`user_iduser`, `user_iduser1`),
  INDEX `fk_user_has_user_user2_idx` (`user_iduser1` ASC) VISIBLE,
  INDEX `fk_user_has_user_user1_idx` (`user_iduser` ASC) VISIBLE,
  CONSTRAINT `fk_user_has_user_user1`
    FOREIGN KEY (`user_iduser`)
    REFERENCES `dbp6`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_has_user_user2`
    FOREIGN KEY (`user_iduser1`)
    REFERENCES `dbp6`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `dbp6`.`bank_operation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `dbp6`.`bankoperation` ;

CREATE TABLE IF NOT EXISTS `dbp6`.`bankoperation` (
    `date` DATETIME NOT NULL,
    `amount` DOUBLE ZEROFILL NOT NULL,
    `user_id_user` INT NOT NULL,
    `bank_id_bank` INT NOT NULL,
    PRIMARY KEY (`user_id_user`, `bank_id_bank`),
    INDEX `fk_user_has_bank_bank1_idx` (`bank_id_bank` ASC) INVISIBLE,
    INDEX `fk_user_has_bank_user1_idx` (`user_id_user` ASC) VISIBLE,
    CONSTRAINT `fk_user_has_bank_user1`
       FOREIGN KEY (`user_id_user`)
           REFERENCES `dbp6`.`user` (`id_user`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION,
    CONSTRAINT `fk_user_has_bank_bank1`
       FOREIGN KEY (`bank_id_bank`)
           REFERENCES `dbp6`.`bank` (`id_bank`)
           ON DELETE NO ACTION
           ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
