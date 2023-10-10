-- phpMyAdmin SQL Dump
-- version 5.1.1deb5ubuntu1
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 10, 2023 at 08:46 AM
-- Server version: 8.0.32-0ubuntu0.22.04.2
-- PHP Version: 8.1.2-1ubuntu2.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kutxabank`
--

-- --------------------------------------------------------

--
-- Table structure for table `erabiltzaile_mugimenduak`
--

CREATE TABLE `erabiltzaile_mugimenduak` (
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `izena` varchar(20) NOT NULL,
  `kantitatea` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `erabiltzaile_mugimenduak`
--

INSERT INTO `erabiltzaile_mugimenduak` (`timestamp`, `izena`, `kantitatea`) VALUES
('2023-10-09 18:14:50', 'hodei', 10),
('2023-10-09 18:15:16', 'hodei', -5),
('2023-10-09 18:15:31', 'hodei', 200),
('2023-10-09 18:15:43', 'hodei', -45),
('2023-10-09 18:38:25', 'peio', 200);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `erabiltzaile_mugimenduak`
--
ALTER TABLE `erabiltzaile_mugimenduak`
  ADD PRIMARY KEY (`timestamp`,`izena`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
