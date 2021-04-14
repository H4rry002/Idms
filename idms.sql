-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 14, 2021 at 11:58 AM
-- Server version: 10.4.17-MariaDB
-- PHP Version: 8.0.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `idms`
--

-- --------------------------------------------------------

--
-- Table structure for table `activereceipt`
--

CREATE TABLE `activereceipt` (
  `docRegisNo` int(6) NOT NULL,
  `patientName` varchar(20) NOT NULL,
  `patientPhNo` bigint(12) NOT NULL,
  `generateTime` timestamp NOT NULL DEFAULT current_timestamp(),
  `PatientAge` int(3) NOT NULL,
  `medicine` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `activereceipt`
--

INSERT INTO `activereceipt` (`docRegisNo`, `patientName`, `patientPhNo`, `generateTime`, `PatientAge`, `medicine`) VALUES
(298373, 'Jitesh Arora', 9896673312, '2021-02-28 08:06:46', 22, 'abacavir @ 2,');

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `docRegisNo` int(6) NOT NULL,
  `Name` varchar(20) NOT NULL,
  `username` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(200) NOT NULL,
  `PhoneNo` int(11) NOT NULL,
  `verified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`docRegisNo`, `Name`, `username`, `email`, `password`, `PhoneNo`, `verified`) VALUES
(298373, 'Jitesh Arora', 'DRJitesh82', 'jitesharora002@gmail.com', '$2a$12$BoaT2PVmAlL51V32luUAFe99KYQondcXiwzzOjeHmHiFphsVyQ3kC', 0, 1);

-- --------------------------------------------------------

--
-- Table structure for table `donereceipt`
--

CREATE TABLE `donereceipt` (
  `docRegisNo` int(7) NOT NULL,
  `patientName` varchar(20) NOT NULL,
  `patientPhNo` bigint(11) NOT NULL,
  `generateTime` timestamp NULL DEFAULT NULL,
  `PatientAge` int(3) NOT NULL,
  `medicine` varchar(200) NOT NULL,
  `medstoreid` varchar(15) NOT NULL,
  `purchaseTime` timestamp NULL DEFAULT NULL,
  `Amount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `donereceipt`
--

INSERT INTO `donereceipt` (`docRegisNo`, `patientName`, `patientPhNo`, `generateTime`, `PatientAge`, `medicine`, `medstoreid`, `purchaseTime`, `Amount`) VALUES
(298373, 'Jitesh Arora', 8054301982, '2021-02-25 07:53:33', 22, 'abacavir @ 2,', 'PHJitesh79', '2021-02-25 15:16:17', 400),
(298373, 'Jitesh Arora', 8054301982, '2021-02-28 05:51:23', 22, 'abacavir @ 2,', 'PHJitesh79', '2021-02-28 05:53:43', 600),
(298373, 'Jitesh Arora', 8054301982, '2021-02-28 07:27:51', 22, 'abacavir @ 2,', 'PHJitesh79', '2021-02-28 07:29:27', 400),
(298373, 'Tushar shahi', 9896673312, '2021-02-25 07:55:55', 22, 'abacavir @ 22,', 'PHJitesh79', '2021-02-28 08:08:15', 4400),
(298373, 'Jitesh Arora', 8054301982, '2021-04-14 09:41:24', 22, 'abacavir @ 2,', 'PHJitesh79', '2021-04-14 09:51:30', 400);

-- --------------------------------------------------------

--
-- Table structure for table `pharma`
--

CREATE TABLE `pharma` (
  `medstoreId` varchar(10) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(200) NOT NULL,
  `gstNo` varchar(15) NOT NULL,
  `email` varchar(30) NOT NULL,
  `PhoneNo` int(11) NOT NULL,
  `verified` tinyint(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pharma`
--

INSERT INTO `pharma` (`medstoreId`, `name`, `password`, `gstNo`, `email`, `PhoneNo`, `verified`) VALUES
('PHJitesh23', 'Jitesh Arora', '$2a$12$Ms/fqslbg4GdTmT6jQKKpuEuD5xw5cTd1zMHpp/0o4PW4XXhvXdQO', '1293723', 'jitesharora002@gmail.com', 0, 0),
('PHJitesh79', 'Jitesh Arora', '$2a$12$n6roQoiwXUM29ZdXwhgJiuyDJ2XELXs1eZSVUh4Ve8PC12uWx.T9m', '4393782', 'jitesharora256@gmail.com', 0, 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`docRegisNo`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `username` (`username`);

--
-- Indexes for table `pharma`
--
ALTER TABLE `pharma`
  ADD PRIMARY KEY (`medstoreId`),
  ADD UNIQUE KEY `gstNo` (`gstNo`),
  ADD UNIQUE KEY `email` (`email`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
