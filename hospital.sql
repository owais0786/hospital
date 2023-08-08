-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2023 at 08:26 PM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hospital`
--

-- --------------------------------------------------------

--
-- Table structure for table `hospital_staff_mst`
--

CREATE TABLE `hospital_staff_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `address` varchar(255) DEFAULT NULL,
  `contact_number` varchar(255) DEFAULT NULL,
  `staff_designation` varchar(255) DEFAULT NULL,
  `staff_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `hospital_staff_mst`
--

INSERT INTO `hospital_staff_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `address`, `contact_number`, `staff_designation`, `staff_name`) VALUES
(1, NULL, '2023-08-08 06:15:36', b'1', NULL, '2023-08-08 06:15:36', NULL, '7860456450', 'DOCTOR', 'Batra'),
(2, NULL, '2023-08-08 06:15:36', b'1', NULL, '2023-08-08 06:15:36', NULL, '7860456400', 'DOCTOR', 'Batra2');

-- --------------------------------------------------------

--
-- Table structure for table `patient_doctor_relationship_mst`
--

CREATE TABLE `patient_doctor_relationship_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `admit_date` date DEFAULT NULL,
  `admit_status` varchar(255) DEFAULT NULL,
  `expensed` double DEFAULT NULL,
  `hospital_staff_mst_id` bigint(20) NOT NULL,
  `patient_mst_id` bigint(20) NOT NULL,
  `release_date` date DEFAULT NULL,
  `room_no` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_doctor_relationship_mst`
--

INSERT INTO `patient_doctor_relationship_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `admit_date`, `admit_status`, `expensed`, `hospital_staff_mst_id`, `patient_mst_id`, `release_date`, `room_no`) VALUES
(2, NULL, '2023-08-08 07:39:34', b'1', NULL, '2023-08-08 13:50:03', '2023-01-21', 'Discharged', 54, 1, 1, NULL, '001'),
(3, NULL, '2023-08-08 07:39:34', b'1', NULL, '2023-08-08 14:07:11', '2023-01-21', 'Discharged', 54, 1, 1, NULL, '001');

-- --------------------------------------------------------

--
-- Table structure for table `patient_mst`
--

CREATE TABLE `patient_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `address` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `patient_name` varchar(255) NOT NULL,
  `phone_number` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `patient_mst`
--

INSERT INTO `patient_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `address`, `dob`, `patient_name`, `phone_number`) VALUES
(1, NULL, '2023-08-08 06:24:10', b'1', NULL, '2023-08-08 06:24:10', 'Gorakhpur', '2000-07-17', 'Owais Siddiqui', '8318235969');

-- --------------------------------------------------------

--
-- Table structure for table `system_mst`
--

CREATE TABLE `system_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `system_code` varchar(255) DEFAULT NULL,
  `system_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `system_mst`
--

INSERT INTO `system_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `system_code`, `system_name`) VALUES
(1, NULL, '2023-08-08 06:20:23', b'1', NULL, '2023-08-08 06:20:23', 'APP', 'App');

-- --------------------------------------------------------

--
-- Table structure for table `user_authorization_mst`
--

CREATE TABLE `user_authorization_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `system_mst_id` bigint(20) NOT NULL,
  `user_role_mst_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_authorization_mst`
--

INSERT INTO `user_authorization_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `system_mst_id`, `user_role_mst_id`) VALUES
(1, NULL, '2023-08-08 06:20:43', b'1', NULL, '2023-08-08 06:20:43', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_mst`
--

CREATE TABLE `user_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `password` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  `user_role_mst_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_mst`
--

INSERT INTO `user_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `password`, `user_name`, `user_role_mst_id`) VALUES
(1, NULL, '2023-08-08 06:15:36', b'1', NULL, '2023-08-08 06:15:36', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'batra@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `user_role_mst`
--

CREATE TABLE `user_role_mst` (
  `id` bigint(20) NOT NULL,
  `created_by` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `is_active` bit(1) DEFAULT NULL,
  `modified_by` bigint(20) DEFAULT NULL,
  `modified_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `role_code` varchar(255) NOT NULL,
  `role_name` varchar(255) NOT NULL,
  `sort_order` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user_role_mst`
--

INSERT INTO `user_role_mst` (`id`, `created_by`, `created_at`, `is_active`, `modified_by`, `modified_at`, `role_code`, `role_name`, `sort_order`) VALUES
(1, NULL, '2023-08-08 06:14:34', b'1', NULL, '2023-08-08 06:14:34', '001', 'USER', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `hospital_staff_mst`
--
ALTER TABLE `hospital_staff_mst`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `patient_doctor_relationship_mst`
--
ALTER TABLE `patient_doctor_relationship_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdw5e4s4ahsgyfwvpr0riph6yj` (`hospital_staff_mst_id`),
  ADD KEY `FKhvj4l2cob6kgu00w2r6rpu0v7` (`patient_mst_id`);

--
-- Indexes for table `patient_mst`
--
ALTER TABLE `patient_mst`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `system_mst`
--
ALTER TABLE `system_mst`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_i133dhd829a5csjpiuf8tyud` (`system_code`);

--
-- Indexes for table `user_authorization_mst`
--
ALTER TABLE `user_authorization_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKium9oy4grpn1a6nd3gsbiemjp` (`system_mst_id`),
  ADD KEY `FKjuibmdo4l0iyg236xwvdv4kla` (`user_role_mst_id`);

--
-- Indexes for table `user_mst`
--
ALTER TABLE `user_mst`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKe49u0iellvdc4r5idvllyuhsm` (`user_role_mst_id`);

--
-- Indexes for table `user_role_mst`
--
ALTER TABLE `user_role_mst`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `hospital_staff_mst`
--
ALTER TABLE `hospital_staff_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `patient_doctor_relationship_mst`
--
ALTER TABLE `patient_doctor_relationship_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `patient_mst`
--
ALTER TABLE `patient_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `system_mst`
--
ALTER TABLE `system_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_authorization_mst`
--
ALTER TABLE `user_authorization_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user_mst`
--
ALTER TABLE `user_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user_role_mst`
--
ALTER TABLE `user_role_mst`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `patient_doctor_relationship_mst`
--
ALTER TABLE `patient_doctor_relationship_mst`
  ADD CONSTRAINT `FKdw5e4s4ahsgyfwvpr0riph6yj` FOREIGN KEY (`hospital_staff_mst_id`) REFERENCES `hospital_staff_mst` (`id`),
  ADD CONSTRAINT `FKhvj4l2cob6kgu00w2r6rpu0v7` FOREIGN KEY (`patient_mst_id`) REFERENCES `patient_mst` (`id`);

--
-- Constraints for table `user_authorization_mst`
--
ALTER TABLE `user_authorization_mst`
  ADD CONSTRAINT `FKium9oy4grpn1a6nd3gsbiemjp` FOREIGN KEY (`system_mst_id`) REFERENCES `system_mst` (`id`),
  ADD CONSTRAINT `FKjuibmdo4l0iyg236xwvdv4kla` FOREIGN KEY (`user_role_mst_id`) REFERENCES `user_role_mst` (`id`);

--
-- Constraints for table `user_mst`
--
ALTER TABLE `user_mst`
  ADD CONSTRAINT `FKe49u0iellvdc4r5idvllyuhsm` FOREIGN KEY (`user_role_mst_id`) REFERENCES `user_role_mst` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
