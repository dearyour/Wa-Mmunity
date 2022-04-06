-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema wamunity
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wamunity
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wamunity` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `wamunity` ;

-- -----------------------------------------------------
-- Table `wamunity`.`free_article_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`free_article_comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `content` TEXT NOT NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`free_article_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`free_article_like` (
  `free_like_id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `member_id` INT NOT NULL COMMENT 'Comment',
  PRIMARY KEY (`free_like_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`free_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`free_board` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `photo` TEXT NULL DEFAULT NULL,
  `tag` TEXT NULL DEFAULT NULL,
  `regtime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `price` INT NOT NULL,
  PRIMARY KEY (`article_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`member`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`member` (
  `member_id` INT NOT NULL AUTO_INCREMENT,
  `nickname` VARCHAR(255) NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `is_adult` TINYINT NOT NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`member_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 36
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`member_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`member_roles` (
  `member_member_id` INT NOT NULL,
  `roles` VARCHAR(255) NULL DEFAULT NULL,
  INDEX `FKruptm2dtwl95mfks4bnhv828k` (`member_member_id` ASC) VISIBLE,
  CONSTRAINT `FKruptm2dtwl95mfks4bnhv828k`
    FOREIGN KEY (`member_member_id`)
    REFERENCES `wamunity`.`member` (`member_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`notice`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`notice` (
  `notice_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `board_id` INT NULL DEFAULT NULL COMMENT '게시판 구분',
  `article_id` INT NULL DEFAULT NULL COMMENT '글 id',
  `content` TEXT NULL DEFAULT NULL COMMENT '내용',
  PRIMARY KEY (`notice_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`resell_article_comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`resell_article_comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `content` TEXT NOT NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 8
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`resell_article_like`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`resell_article_like` (
  `resell_like_id` INT NOT NULL AUTO_INCREMENT,
  `article_id` INT NOT NULL,
  `member_id` INT NOT NULL COMMENT 'Comment',
  PRIMARY KEY (`resell_like_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 3
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`resell_board`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`resell_board` (
  `article_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `title` VARCHAR(255) NOT NULL,
  `content` TEXT NOT NULL,
  `photo` TEXT NULL DEFAULT NULL,
  `tag` TEXT NULL DEFAULT NULL,
  `price` INT NOT NULL,
  `regtime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`article_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`review_base_recomm`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`review_base_recomm` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  `wine_id` INT NOT NULL,
  `exp_rating` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 11
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`wine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`wine` (
  `wine_id` INT NOT NULL,
  `name` VARCHAR(255) NULL DEFAULT NULL,
  `img` TEXT NULL DEFAULT NULL,
  `winery` VARCHAR(255) NULL DEFAULT NULL,
  `wine_style` VARCHAR(255) NULL DEFAULT NULL,
  `style` VARCHAR(255) NULL DEFAULT NULL,
  `country` VARCHAR(255) NULL DEFAULT NULL,
  `region1` VARCHAR(255) NULL DEFAULT NULL,
  `region2` VARCHAR(255) NULL DEFAULT NULL,
  `region3` VARCHAR(255) NULL DEFAULT NULL,
  `grape1` VARCHAR(255) NULL DEFAULT NULL,
  `grape2` VARCHAR(255) NULL DEFAULT NULL,
  `grape3` VARCHAR(255) NULL DEFAULT NULL,
  `cat1` VARCHAR(255) NULL DEFAULT NULL,
  `cat2` VARCHAR(255) NULL DEFAULT NULL,
  `price` INT NULL DEFAULT NULL,
  `alcohol_content` FLOAT NULL DEFAULT NULL,
  `allergen1` VARCHAR(255) NULL DEFAULT NULL,
  `allergen2` VARCHAR(255) NULL DEFAULT NULL,
  `allergen3` VARCHAR(255) NULL DEFAULT NULL,
  `rating_avg` DOUBLE NULL DEFAULT NULL,
  `rating_num` INT NULL DEFAULT NULL,
  `oaky` FLOAT NULL DEFAULT NULL,
  `earthy` FLOAT NULL DEFAULT NULL,
  `black_fruit` FLOAT NULL DEFAULT NULL,
  `red_fruit` FLOAT NULL DEFAULT NULL,
  `spices` FLOAT NULL DEFAULT NULL,
  `floral` FLOAT NULL DEFAULT NULL,
  `dried_fruit` FLOAT NULL DEFAULT NULL,
  `ageing` FLOAT NULL DEFAULT NULL,
  `yeasty` FLOAT NULL DEFAULT NULL,
  `vegetal` FLOAT NULL DEFAULT NULL,
  `citrus` FLOAT NULL DEFAULT NULL,
  `tree_fruit` FLOAT NULL DEFAULT NULL,
  `bold` FLOAT NULL DEFAULT NULL,
  `tannic` FLOAT NULL DEFAULT NULL,
  `sweet` FLOAT NULL DEFAULT NULL,
  `acidic` FLOAT NULL DEFAULT NULL,
  `beef` FLOAT NULL DEFAULT NULL,
  `lamb` FLOAT NULL DEFAULT NULL,
  `game` FLOAT NULL DEFAULT NULL,
  `poultry` FLOAT NULL DEFAULT NULL,
  `tropical` FLOAT NULL DEFAULT NULL,
  `pasta` FLOAT NULL DEFAULT NULL,
  `veal` FLOAT NULL DEFAULT NULL,
  `cured_meat` FLOAT NULL DEFAULT NULL,
  `mature_and_hard_cheese` FLOAT NULL DEFAULT NULL,
  `pork` FLOAT NULL DEFAULT NULL,
  `allergens` VARCHAR(255) NULL DEFAULT NULL,
  `food_parings` VARCHAR(255) NULL DEFAULT NULL,
  `grapes` VARCHAR(255) NULL DEFAULT NULL,
  `regions` VARCHAR(255) NULL DEFAULT NULL,
  `taste` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`wine_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`wine_review`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`wine_review` (
  `wine_review_id` INT NOT NULL AUTO_INCREMENT,
  `wine_id` INT NOT NULL,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `rating` DOUBLE NULL DEFAULT NULL,
  `content` VARCHAR(255) NULL DEFAULT NULL,
  `regtime` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`wine_review_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 176
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`wine_survey`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`wine_survey` (
  `wine_survey_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL,
  `amount_of_alcohol` INT NOT NULL,
  `smell_taste1` INT NOT NULL,
  `smell_taste2` INT NOT NULL,
  `smell_taste3` INT NOT NULL,
  `acidic_preference` INT NOT NULL,
  `sweet_preference` INT NOT NULL,
  `tannic_preference` INT NOT NULL,
  `bold_preference` INT NOT NULL,
  `min_price` INT NOT NULL,
  `max_price` INT NOT NULL,
  `food1` INT NOT NULL,
  `food2` INT NOT NULL,
  `food3` INT NOT NULL,
  PRIMARY KEY (`wine_survey_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wamunity`.`wine_wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wamunity`.`wine_wishlist` (
  `wish_id` INT NOT NULL AUTO_INCREMENT,
  `member_id` INT NOT NULL COMMENT 'Comment',
  `wine_id` INT NOT NULL,
  PRIMARY KEY (`wish_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 511
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
