DROP SCHEMA IF EXISTS `library` ;
CREATE SCHEMA `library` DEFAULT CHARACTER SET utf8 ;
USE `library`;

CREATE TABLE IF NOT EXISTS `books` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `author_name` VARCHAR(45) NOT NULL,
  `date_of_creation` DATE NOT NULL,
  `quantity` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `users` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(20) NOT NULL,
  `surname` VARCHAR(20) NOT NULL,
  `date_of_birth` DATE NOT NULL,
  `date_of_registration` TIMESTAMP NOT NULL,
  `role` VARCHAR(10) NOT NULL,
  `login` VARCHAR(20) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `book_to_user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `book_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  `start_date` TIMESTAMP NOT NULL,
  `end_date` TIMESTAMP,
  `type` VARCHAR(15),
  `status` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

ALTER TABLE `book_to_user`
  ADD INDEX `fk_orders_books_id` (`book_id` ASC) VISIBLE,
  ADD INDEX `fk_orders_users_id` (`user_id` ASC) VISIBLE,
  ADD CONSTRAINT `fk_orders_books`
    FOREIGN KEY (`book_id`)
    REFERENCES `books` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_orders_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION;
COMMIT;