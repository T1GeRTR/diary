DROP DATABASE IF EXISTS diary;

CREATE DATABASE `diary`;

USE `diary`;

CREATE TABLE `project` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `preview` varchar(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY name (name),
    KEY preview (preview)
) ENGINE=INNODB;

CREATE TABLE `skill` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `icon` varchar(50) NOT NULL,
    PRIMARY KEY (id),
	UNIQUE KEY name (name),
    KEY icon (icon)
) ENGINE=INNODB;

CREATE TABLE `link_type` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `icon` varchar(50) NOT NULL,
    PRIMARY KEY (id),
	UNIQUE KEY name (name),
    KEY icon (icon)
);

CREATE TABLE `project_skill` (
    `id` int NOT NULL AUTO_INCREMENT,
    `project_id` int NOT NULL,
    `skill_id` int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (project_id) REFERENCES `project` (id) ON DELETE CASCADE,
    FOREIGN KEY (skill_id) REFERENCES `skill` (id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE `link` (
    `id` int NOT NULL AUTO_INCREMENT,
    `link_type_id` int NOT NULL,
    `project_id` int NOT NULL,
    `url` varchar(50) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (link_type_id) REFERENCES `link_type` (id) ON DELETE CASCADE,
    FOREIGN KEY (project_id) REFERENCES `project` (id) ON DELETE CASCADE,
    UNIQUE KEY url (url)
) ENGINE=INNODB;

Insert into project values (1, "project1", "preview");
Insert into link_type values (1, "github", "icon");
Insert into skill values(1, "Java", "icon");
