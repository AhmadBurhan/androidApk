-- phpMyAdmin SQL Dump
-- version 4.4.12
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 25, 2017 at 03:16 PM
-- Server version: 5.6.25
-- PHP Version: 5.5.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `uasapk`
--

-- --------------------------------------------------------

--
-- Table structure for table `master_karyawan`
--

CREATE TABLE IF NOT EXISTS `master_karyawan` (
  `id_karyawan` int(10) NOT NULL,
  `nama_karyawan` varchar(20) NOT NULL,
  `jabatan` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_karyawan`
--

INSERT INTO `master_karyawan` (`id_karyawan`, `nama_karyawan`, `jabatan`) VALUES
(1, 'MIstah', 'Operator'),
(2, 'ibnu', 'admin'),
(3, 'bagus', 'hrd');

-- --------------------------------------------------------

--
-- Table structure for table `master_produksi`
--

CREATE TABLE IF NOT EXISTS `master_produksi` (
  `id_operator` int(10) NOT NULL,
  `nama_operator` varchar(20) NOT NULL,
  `mesin` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_produksi`
--

INSERT INTO `master_produksi` (`id_operator`, `nama_operator`, `mesin`) VALUES
(1, 'Darmono', 'Mata'),
(2, 'Ginanjar', 'Kulit'),
(3, 'Gugun', 'Kehamilan'),
(4, 'Darmono', 'JSW'),
(5, 'Komo', 'Grinding');

-- --------------------------------------------------------

--
-- Table structure for table `master_qc`
--

CREATE TABLE IF NOT EXISTS `master_qc` (
  `id_qc` int(10) NOT NULL,
  `nama_inspector` varchar(30) NOT NULL,
  `nama_produk` varchar(40) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_qc`
--

INSERT INTO `master_qc` (`id_qc`, `nama_inspector`, `nama_produk`) VALUES
(1, 'Abib', 'Kopet'),
(13, 'Riri', 'Mobil');

-- --------------------------------------------------------

--
-- Table structure for table `master_warehouse`
--

CREATE TABLE IF NOT EXISTS `master_warehouse` (
  `id_warehouse` int(10) NOT NULL,
  `nama_karyawan` varchar(20) NOT NULL,
  `nama_produk` varchar(20) NOT NULL,
  `jumlah_produk` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `master_warehouse`
--

INSERT INTO `master_warehouse` (`id_warehouse`, `nama_karyawan`, `nama_produk`, `jumlah_produk`) VALUES
(1, 'eko', 'motor', 'emon'),
(2, 'burhan', 'Bobin coil', 'septi'),
(3, 'Dedi', 'Integral', '234'),
(4, 'Putro', 'cinta', '230'),
(5, 'andri', 'motor', '2');

-- --------------------------------------------------------

--
-- Table structure for table `nahasiswa`
--

CREATE TABLE IF NOT EXISTS `nahasiswa` (
  `npm` int(10) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `kelas` varchar(20) NOT NULL,
  `sesi` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(10) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `master_karyawan`
--
ALTER TABLE `master_karyawan`
  ADD PRIMARY KEY (`id_karyawan`);

--
-- Indexes for table `master_produksi`
--
ALTER TABLE `master_produksi`
  ADD PRIMARY KEY (`id_operator`);

--
-- Indexes for table `master_qc`
--
ALTER TABLE `master_qc`
  ADD PRIMARY KEY (`id_qc`);

--
-- Indexes for table `master_warehouse`
--
ALTER TABLE `master_warehouse`
  ADD PRIMARY KEY (`id_warehouse`);

--
-- Indexes for table `nahasiswa`
--
ALTER TABLE `nahasiswa`
  ADD PRIMARY KEY (`npm`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `master_karyawan`
--
ALTER TABLE `master_karyawan`
  MODIFY `id_karyawan` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `master_produksi`
--
ALTER TABLE `master_produksi`
  MODIFY `id_operator` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `master_qc`
--
ALTER TABLE `master_qc`
  MODIFY `id_qc` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `master_warehouse`
--
ALTER TABLE `master_warehouse`
  MODIFY `id_warehouse` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `nahasiswa`
--
ALTER TABLE `nahasiswa`
  MODIFY `npm` int(10) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
