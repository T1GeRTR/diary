DROP DATABASE IF EXISTS diary;

CREATE DATABASE `diary`;

USE `diary`;

CREATE TABLE `project` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `preview` varchar(50) NOT NULL,
    PRIMARY KEY (id),
    KEY name (name),
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

CREATE TABLE `linkType` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL,
    `icon` varchar(50) NOT NULL,
    PRIMARY KEY (id),
	UNIQUE KEY name (name),
    KEY icon (icon)
);

CREATE TABLE `project_skill` (
    `id` int NOT NULL AUTO_INCREMENT,
    `projectId` int NOT NULL,
    `skillId` int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (projectId) REFERENCES `project` (id) ON DELETE CASCADE,
    FOREIGN KEY (skillId) REFERENCES `skill` (id) ON DELETE CASCADE
) ENGINE=INNODB;

CREATE TABLE `project_link` (
    `id` int NOT NULL AUTO_INCREMENT,
    `projectId` int NOT NULL,
    `linkId` int NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (projectId) REFERENCES `project` (id) ON DELETE CASCADE,
    FOREIGN KEY (linkId) REFERENCES `linkType` (id) ON DELETE CASCADE
) ENGINE=INNODB;