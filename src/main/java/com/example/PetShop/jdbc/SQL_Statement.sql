CREATE TABLE `owners` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `date_created` varchar(255) NOT NULL,
  `date_modified` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
);

Create TABLE `pet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `breed` varchar(255) NOT NULL,
  `date_created` varchar(255) NOT NULL,
  `date_modified` varchar(255) NOT NULL,
  `owner_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`owner_id`) REFERENCES `owners` (`id`)
);

Create TABLE `pet` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `breed` varchar(255) NOT NULL,
  `date_created` varchar(255) NOT NULL,
  `date_modified` varchar(255) NOT NULL,
  `owner_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `owner_id` FOREIGN KEY (`id`) REFERENCES `owners` (`id`)
);