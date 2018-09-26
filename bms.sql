-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 26, 2018 at 05:54 PM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 7.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bms`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `UserId` varchar(20) NOT NULL,
  `Password` varchar(10) NOT NULL,
  `FirstName` varchar(30) NOT NULL,
  `LastName` varchar(30) NOT NULL,
  `Email` varchar(60) NOT NULL,
  `Phone` bigint(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`UserId`, `Password`, `FirstName`, `LastName`, `Email`, `Phone`) VALUES
('Ajay', '1223', 'Ajay', 'Gupta', 'gajay251173@gmail.com', 9719207967),
('Ani12', '5555', 'Anirudh', 'Singh', 'anirudhsingh7874@gmail.com', 9897600789),
('maddy', '1234', 'Madhur', 'Mittal', 'madhurmittal275@gmail.com', 8439481236),
('naruto', '4567', 'Naruto', 'Uzumaki', 'uzumakinaruto@gmail.com', 9897654321);

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `ItemCode` int(11) NOT NULL,
  `ItemName` varchar(50) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Price` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `sale`
--

CREATE TABLE `sale` (
  `ItemCode` int(11) NOT NULL,
  `ItemName` varchar(50) NOT NULL,
  `Quantity` int(11) NOT NULL,
  `TotalPrice` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `sale`
--

INSERT INTO `sale` (`ItemCode`, `ItemName`, `Quantity`, `TotalPrice`) VALUES
(1105, 'Lays Americano 89g', 9, 270),
(1106, 'Colgate Cibaca 100g', 95, 3705),
(1110, 'Parle G 50g', 6, 60),
(1111, 'Real Mix Fruit', 1, 99),
(1115, 'Real Guawa 1L', 3, 270),
(1118, 'Haldiram Aloo Bhujia', 6, 474),
(1125, 'CocaCola 2.25L', 10, 840),
(1126, 'Sprite 2.25L', 7, 588),
(1129, 'Mirinda 2.25L', 15, 1260),
(1130, 'Fanta 2.25L', 4, 336);

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `ItemCode` int(11) NOT NULL,
  `ItemName` varchar(60) NOT NULL,
  `ItemPrice` float NOT NULL,
  `Quantity` int(11) NOT NULL,
  `Discount` float NOT NULL,
  `DisQuantity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`ItemCode`, `ItemName`, `ItemPrice`, `Quantity`, `Discount`, `DisQuantity`) VALUES
(1101, 'Macroni 1Kg', 120, 218, 20, 3),
(1102, 'Rice 5Kg', 150, 226, 18, 1),
(1103, 'Yipee 35gm', 5, 270, 0.5, 9),
(1104, 'Bournvita+ 80gm', 30, 135, 27, 10),
(1105, 'Lays Americano 89g', 35, 91, 5, 1),
(1106, 'Colgate Cibaca 100g', 50, 80, 11, 3),
(1107, 'Johnson Baby Soap 75g', 65, 100, 5, 1),
(1108, 'Paper Napkin', 60, 142, 28, 1),
(1109, 'Amul Lassi 1L', 65, 100, 5, 1),
(1110, 'Parle G 50g', 10, 126, 2, 6),
(1111, 'Real Mix Fruit', 110, 88, 11, 1),
(1112, 'Sunfeast 50g', 10, 132, 2, 6),
(1113, 'Real Apple', 110, 100, 11, 1),
(1114, 'Real Grapes 1L', 110, 132, 11, 1),
(1115, 'Real Guawa 1L', 99, 205, 9, 1),
(1116, 'Real Lichi 1L', 99, 132, 9, 1),
(1117, 'Bisleri 1L', 20, 150, 2, 3),
(1118, 'Haldiram Aloo Bhujia', 91, 152, 12, 1),
(1119, 'Bikaner Mix 400g', 89, 126, 4.45, 1),
(1120, 'Haldiram Punjabi Tadka 200g', 50, 158, 7.25, 1),
(1121, 'CNC Biscuits 200g', 40, 126, 3.25, 2),
(1122, 'Scrubz DishWash Bar', 18, 356, 10, 10),
(1123, 'Bourbon 200g', 40, 126, 3.25, 2),
(1124, 'Pepsodent 200g', 56, 256, 4, 1),
(1125, 'CocaCola 2.25L', 89, 116, 5, 1),
(1126, 'Sprite 2.25L', 89, 138, 5, 1),
(1127, 'Limca 2.25L', 89, 126, 5, 1),
(1128, 'Mountain Dew 2.25L', 89, 145, 5, 1),
(1129, 'Mirinda 2.25L', 89, 111, 5, 1),
(1130, 'Fanta 2.25L', 89, 141, 5, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD UNIQUE KEY `UserId` (`UserId`);

--
-- Indexes for table `sale`
--
ALTER TABLE `sale`
  ADD UNIQUE KEY `ItemCode` (`ItemCode`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD UNIQUE KEY `ItemCode` (`ItemCode`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
