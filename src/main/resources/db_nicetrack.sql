-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 05, 2018 at 11:03 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_nicetrack`
--

-- --------------------------------------------------------

--
-- Table structure for table `t_dossiers`
--

CREATE TABLE `t_dossiers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `nom` varchar(128) NOT NULL,
  `commentaire` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_events`
--

CREATE TABLE `t_events` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `event_id` varchar(16) DEFAULT NULL,
  `start_date` timestamp NULL DEFAULT NULL,
  `duration` varchar(16) DEFAULT NULL,
  `event_type` varchar(24) DEFAULT NULL,
  `direction` varchar(4) DEFAULT NULL,
  `relevancy` varchar(16) DEFAULT NULL,
  `caller_id` varchar(24) DEFAULT NULL,
  `caller_imei` varchar(24) DEFAULT NULL,
  `caller_imsi` varchar(24) DEFAULT NULL,
  `target_name` varchar(32) DEFAULT NULL,
  `called_id` varchar(24) DEFAULT NULL,
  `called_imei` varchar(24) DEFAULT NULL,
  `called_imsi` varchar(24) DEFAULT NULL,
  `synopsis` text,
  `sms_content` text,
  `location` varchar(64) DEFAULT NULL,
  `transcription` text,
  `ref_id_numero` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `t_numero`
--

CREATE TABLE `t_numero` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `callid` varchar(24) NOT NULL,
  `ref_id_dossier` bigint(20) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `t_dossiers`
--
ALTER TABLE `t_dossiers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `t_events`
--
ALTER TABLE `t_events`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `TRIPLET_UNIQUE` (`event_id`,`caller_id`,`called_id`),
  ADD KEY `ref_id_dossier` (`ref_id_numero`);

--
-- Indexes for table `t_numero`
--
ALTER TABLE `t_numero`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ref_id_dossier` (`ref_id_dossier`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `t_dossiers`
--
ALTER TABLE `t_dossiers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `t_events`
--
ALTER TABLE `t_events`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2260;

--
-- AUTO_INCREMENT for table `t_numero`
--
ALTER TABLE `t_numero`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `t_events`
--
ALTER TABLE `t_events`
  ADD CONSTRAINT `foreign_key` FOREIGN KEY (`ref_id_numero`) REFERENCES `t_numero` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `t_numero`
--
ALTER TABLE `t_numero`
  ADD CONSTRAINT `foreign_key_dossier` FOREIGN KEY (`ref_id_dossier`) REFERENCES `t_dossiers` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
