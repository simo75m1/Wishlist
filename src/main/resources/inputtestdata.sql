INSERT INTO `wishwebapp`.`user` (`username`, `password`) VALUES ('test', '1234');
INSERT INTO `wishwebapp`.`user` (`username`, `password`) VALUES ('simon', '12345');
INSERT INTO `wishwebapp`.`user` (`username`, `password`) VALUES ('test2', 'testkode');
INSERT INTO `wishwebapp`.`wishlist`(`user_ID`,`wishlist_name`) VALUES('1','Birthday');
INSERT INTO `wishwebapp`.`wishlist`(`user_ID`,`wishlist_name`) VALUES('1', 'Christmas wishlist');
INSERT INTO `wishwebapp`.`wishlist`(`user_ID`,`wishlist_name`) VALUES('2', 'Wedding');
INSERT INTO `wishwebapp`.`wishlist`(`user_ID`,`wishlist_name`) VALUES('3', 'Hanukkah list');
INSERT INTO `wishwebapp`.`wishlist`(`user_ID`,`wishlist_name`) VALUES('3', 'Birthday');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('1','Playstation','1500','www.tomato.dk');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('1','Nvidia RTX 4090','18000','www.example.com');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('2','Nvidia RTX 4090','18000','www.example.com');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('2','Xbox','3000','www.power.dk');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('2','Sko','300','www.FætterBR.dk');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('3','Hundehvalp','7000','www.hundehvalp.dk');
INSERT INTO `wishwebapp`.`wish`(`wishlist_ID`,`product_name`, `product_price`, `product_link`) VALUES('3','LG 55 tommer smart tv','7999','www.Powersucks.dk');
