DROP DATABASE IF EXISTS wishwebapp;
CREATE DATABASE wishwebapp;
use wishwebapp;
DROP TABLE IF EXISTS user, wishlist, wish;
CREATE TABLE `wishwebapp`.`user` (
                                      `user_ID` INT NOT NULL AUTO_INCREMENT,
                                      `username` VARCHAR(45) NOT NULL,
                                      `password` VARCHAR(45) NOT NULL,
                                      PRIMARY KEY (`user_ID`),
                                      UNIQUE INDEX `user_ID_UNIQUE` (`user_ID` ASC) VISIBLE,
                                      UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE);
CREATE TABLE `wishwebapp`.`wishlist` (
                                          `wishlist_ID` INT NOT NULL AUTO_INCREMENT,
                                          `user_ID` INT NOT NULL,
                                          `wishlist_name` VARCHAR(45) NOT NULL,
                                          PRIMARY KEY (`wishlist_ID`),
                                          UNIQUE INDEX `wishlist_ID_UNIQUE` (`wishlist_ID` ASC) VISIBLE);
CREATE TABLE `wishwebapp`.`wish` (
                                       `wishlist_ID` INT NOT NULL,
                                       `wish_ID` INT NOT NULL AUTO_INCREMENT,
                                       `product_name` VARCHAR(50) NOT NULL,
                                       `product_price` INT NULL,
                                       `product_link` VARCHAR(300) NULL,
                                       `reserved_status` INT NOT NULL DEFAULT 0,
                                       PRIMARY KEY (`wish_ID`));
