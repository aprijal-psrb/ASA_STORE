-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Apr 12, 2015 at 07:09 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `pasaribu_store`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE IF NOT EXISTS `barang` (
`id_barang` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_merek` int(11) NOT NULL,
  `id_penjual` int(11) NOT NULL,
  `id_gambar` int(11) NOT NULL,
  `nama_barang` varchar(200) NOT NULL,
  `stok_barang` int(11) NOT NULL,
  `satuan_barang` varchar(50) NOT NULL,
  `harga_barang` int(11) NOT NULL,
  `tgl_harga_stok_barang` date NOT NULL,
  `kode_barang` varchar(20) NOT NULL,
  `lokasi_barang` varchar(20) NOT NULL,
  `kategori_barang` int(11) NOT NULL,
  `deskripsi_barang` text NOT NULL,
  `id_favorite` tinyint(4) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=72 ;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `id_user`, `id_merek`, `id_penjual`, `id_gambar`, `nama_barang`, `stok_barang`, `satuan_barang`, `harga_barang`, `tgl_harga_stok_barang`, `kode_barang`, `lokasi_barang`, `kategori_barang`, `deskripsi_barang`, `id_favorite`) VALUES
(1, 1, 4, 2, 0, 'Lampu Depan V.R New / Supra', 20, 'Kotak', 65000, '2014-07-15', 'KODE', 'LOKASI', 1, 'Lampu Depan V.R New dengan kualitas prima', 0),
(2, 1, 1, 1, 0, 'Samsung 4K 81 Inch LED TV', 3, 'Unit', 85000000, '2014-11-05', 'KODE', 'LOKASI', 1, 'TV dengan ketajaman layar mengagumkan dengan resolusi UHD (4K), rasakan sensasi menonton iMax di rumah Anda.', 2),
(10, 1, 5, 2, 0, 'Lampu Sipion S75', 35, 'Kotak', 75000, '2014-11-15', 'KODE', 'LOKASI', 1, 'Lampu depan (biasa)', 3),
(11, 1, 2, 2, 0, 'Lampu Depan V.R New Aspira', 16, 'Lusin', 60000, '2014-11-16', 'KODE', 'LOKASI', 1, 'Lampu Biasa', 4),
(12, 1, 4, 2, 0, 'Pangkon V.R New', 1, 'Kotak', 45000, '2014-11-16', 'KODE', 'LOKASI', 1, 'Kualitas nomor 1', 3),
(13, 1, 4, 2, 0, 'Batok Depan V.R New (Aspira)', 1, 'Kotak', 46000, '2014-11-16', 'KODE', 'LOKASI', 1, 'Batok utk motor tipe V.R New', 0),
(28, 1, 17, 2, 0, 'Ban Dalam Artko (Swallow)', 200, 'Kotak', 45000, '2014-11-16', 'KODE', 'LOKASI', 1, 'Ban Dalam Sorong/Artko Biasa', 0),
(29, 1, 4, 2, 0, 'Kunci Kontak RX King, Asli', 23, 'Kotak', 85000, '2014-11-16', 'KODE', 'LOKASI', 1, 'Kunci kontak kualitas bagus,\npersamaan utk GL King, Honda, dsb. :v', 0),
(30, 1, 4, 2, 0, 'Kunci Kontak RX King, Seken', 3, 'Kotak', 45000, '2014-11-16', 'KODE', 'LOKASI', 1, 'Harga Murah', 0),
(32, 1, 2, 2, 0, 'Kol TV 32''''', 25, 'Unit', 8000, '2014-11-17', 'KODE', 'LOKASI', 1, 'Test', 9),
(33, 1, 8, 1, 0, 'Lampu LED 50 Watt - Hannocs', 12, 'Kotak', 75000, '2014-11-25', 'KODE', 'LOKASI', 1, 'Lampu hemat energy yang tetap terang walau listrik sedang tidak stabil.', 0),
(34, 1, 9, 4, 0, 'Philips LED 20 Watt - Hannocs', 12, 'Kotak', 55000, '2014-11-25', 'KODE', 'LOKASI', 1, 'Lampu hemat energy yang tetap terang walau listrik sedang tidak stabil.', 10),
(39, 1, 4, 2, 0, 'Filter Oil TAP', 12, 'Lusin', 500, '2014-12-10', 'KODE', 'LOKASI', 1, 'asasa sdsd', 13),
(40, 1, 9, 2, 0, 'Yamalube Matic', 10, 'Botol', 33500, '2014-12-11', 'KODE', 'LOKASI', 1, 'Oli utk matic', 0),
(42, 1, 5, 2, 0, 'Speaker 14 Inch', 25, 'Kotak', 200000, '2014-12-15', 'KODE', 'LOKASI', 1, 'Speaker Kualitas Prima.', 3),
(53, 0, 29, 3, 0, 'Ulok', 34, 'Lusin', 15000, '0000-00-00', '', '', 1, 'Ulok berbisa', 4),
(54, 0, 30, 1, 0, 'Ghgff', 525, 'Yhff', 555655, '0000-00-00', '', '', 1, 'Gjvhki', 0),
(55, 0, 31, 1, 0, 'Trafo', 39, 'Unit', 60000, '0000-00-00', '', '', 1, 'Trafo 5A', 2),
(56, 0, 32, 3, 0, 'Buku Android', 1, 'Buah', 90000, '0000-00-00', '', '', 1, 'Buku belajar', 0),
(57, 0, 8, 1, 0, 'Dyghyfe', 25, 'Unit', 1000, '0000-00-00', '', '', 1, 'Gghjgytyt', 0),
(58, 0, 8, 1, 0, 'Fydtyy', 55, 'Unit', 255855, '0000-00-00', '', '', 1, 'Gyghiy', 0),
(59, 0, 8, 1, 0, 'Aaaaaa', 12, 'Unit', 36000, '2015-04-08', '', '', 1, 'Gugyufr', 0),
(60, 0, 33, 1, 0, 'Laptop', 1, 'Unit', 6400000, '2015-04-11', '', '', 1, 'Asus', 0),
(61, 0, 34, 3, 0, 'Mie Instan', 5, 'Bungkus', 2500, '2015-04-12', '', '', 1, 'Sedaaap,\nEnak jg ya', 3),
(63, 0, 33, 7, 0, 'Asus K46CB', 1, 'Unit', 6400000, '2015-04-12', '', '', 1, 'Laptop saya.', 0),
(64, 0, 35, 8, 0, 'Atfyg', 22, 'Unit', 25000, '2015-04-12', '', '', 1, 'Deskripsi', 0),
(71, 0, 33, 5, 0, 'Ruytrrt', 22, 'Unit', 2268844, '2015-04-13', '', '', 3, 'Jfufufufufuf', 0);

-- --------------------------------------------------------

--
-- Table structure for table `barang_favorite`
--

CREATE TABLE IF NOT EXISTS `barang_favorite` (
`id_barang_favorite` int(11) NOT NULL,
  `id_favorite` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `tgl_favorite` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `barang_favorite`
--

INSERT INTO `barang_favorite` (`id_barang_favorite`, `id_favorite`, `id_barang`, `tgl_favorite`) VALUES
(1, 3, 2, '2014-12-05 02:57:38'),
(3, 3, 1, '2014-12-05 03:01:58');

--
-- Triggers `barang_favorite`
--
DELIMITER //
CREATE TRIGGER `after_added_barang_favorite` AFTER INSERT ON `barang_favorite`
 FOR EACH ROW BEGIN
update barang set id_favorite = NEW.id_favorite where id_barang = NEW.id_barang;
END
//
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `belanja`
--

CREATE TABLE IF NOT EXISTS `belanja` (
`id_belanja` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `tgl_belanja` date NOT NULL,
  `status_belanja` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--

CREATE TABLE IF NOT EXISTS `favorite` (
`id_favorite` int(11) NOT NULL,
  `warna_favorite` varchar(10) NOT NULL,
  `nama_favorite` varchar(50) NOT NULL,
  `deskripsi` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `favorite`
--

INSERT INTO `favorite` (`id_favorite`, `warna_favorite`, `nama_favorite`, `deskripsi`) VALUES
(1, '#ff684545', 'Uncategorized', 'Semua list dalam favorite'),
(2, '#ff28ffe0', 'Jarang Laku', 'Barang-barang yang jarang ada pembeli'),
(3, '#ffff00df', 'Barang Mewah', 'Barang dengan harga yang cukup mahal, jarang laku.'),
(4, '#ff82ff00', 'Langka', 'Barang-barang yang langka di pasaran.'),
(5, '#ffff0000', 'Coba', '');

-- --------------------------------------------------------

--
-- Table structure for table `gambar_barang`
--

CREATE TABLE IF NOT EXISTS `gambar_barang` (
`id_gambar` int(11) NOT NULL,
  `lokasi_gambar` varchar(200) NOT NULL,
  `deskripsi_gambar` varchar(200) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `gambar_barang`
--

INSERT INTO `gambar_barang` (`id_gambar`, `lokasi_gambar`, `deskripsi_gambar`) VALUES
(2, 'http://dev.mysql.com/common/logos/logo-mysql-110x57.png', 'Gambar Lampu Depan V.R New');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
`id` int(11) NOT NULL,
  `nama_kategori` varchar(20) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id`, `nama_kategori`) VALUES
(1, 'Elektronik'),
(2, 'ASA'),
(3, 'Rokok'),
(4, 'Laptop');

-- --------------------------------------------------------

--
-- Table structure for table `log_harga`
--

CREATE TABLE IF NOT EXISTS `log_harga` (
`id_log_harga` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tgl_harga` date NOT NULL,
  `potongan_harga` int(11) NOT NULL DEFAULT '0'
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `log_harga`
--

INSERT INTO `log_harga` (`id_log_harga`, `id_barang`, `harga`, `tgl_harga`, `potongan_harga`) VALUES
(1, 2, 80000000, '2014-11-05', 0),
(2, 1, 60000, '2014-07-15', 0);

-- --------------------------------------------------------

--
-- Table structure for table `log_stok`
--

CREATE TABLE IF NOT EXISTS `log_stok` (
`id_log_stok` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `jumlah_stok` int(11) NOT NULL,
  `tgl_muat_stok` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `log_transaksi`
--

CREATE TABLE IF NOT EXISTS `log_transaksi` (
`id_transaksi` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `tgl_transaksi` int(11) NOT NULL,
  `keterangan` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `merek`
--

CREATE TABLE IF NOT EXISTS `merek` (
`id_merek` int(11) NOT NULL,
  `nama_merek` varchar(100) NOT NULL,
  `logo_merek` varchar(200) NOT NULL,
  `deskripsi_merek` varchar(200) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=40 ;

--
-- Dumping data for table `merek`
--

INSERT INTO `merek` (`id_merek`, `nama_merek`, `logo_merek`, `deskripsi_merek`) VALUES
(1, 'Samsung', '', 'Samsung Elektronik'),
(2, 'LG', '', 'LG Electronic'),
(3, 'Polytron', '', 'Merek Taiwan'),
(4, 'Aspira', '', 'Merek Jepang, kualitas prima'),
(5, 'Ichidai', '', ''),
(6, 'Apple', '', ''),
(7, 'IBM', '', ''),
(8, 'Hannocs', '', ''),
(9, 'Yamalube', '', ''),
(14, 'Itachi', '', ''),
(16, 'Oregon', '', ''),
(17, 'Swallow', '', ''),
(18, 'Tidak Ada Merek', '', ''),
(19, 'Penzoil', '', ''),
(20, 'Penzoil', '', ''),
(21, 'Goa', '', ''),
(22, 'Awe', '', ''),
(23, 'acer', '', ''),
(24, 'adtggf', '', ''),
(25, 'aaaaa', '', ''),
(26, 'asa', '', ''),
(27, 'Spider', '', ''),
(28, 'Microsoft', '', ''),
(29, 'Kobra', '', ''),
(30, 'Fffhhg', '', ''),
(31, 'Nichi', '', ''),
(32, 'Android', '', ''),
(33, 'Asus', '', ''),
(34, 'Indomie', '', ''),
(35, 'Sony', '', ''),
(36, 'Juvug', '', ''),
(37, 'Hdhfufu', '', ''),
(38, 'Telor', '', ''),
(39, 'fhufuf', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `penjual`
--

CREATE TABLE IF NOT EXISTS `penjual` (
`id_penjual` int(11) NOT NULL,
  `nama_penjual` varchar(150) NOT NULL,
  `nama_toko` varchar(100) NOT NULL,
  `alamat_toko` varchar(200) NOT NULL,
  `geolocation` varchar(100) NOT NULL,
  `kontak_toko` varchar(100) NOT NULL DEFAULT '0',
  `email_toko` varchar(100) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `penjual`
--

INSERT INTO `penjual` (`id_penjual`, `nama_penjual`, `nama_toko`, `alamat_toko`, `geolocation`, `kontak_toko`, `email_toko`) VALUES
(1, 'Umak Riska', 'Hikmah Jaya', 'Jalan Merdeka, Padangsidimpuan', '', '0', 'hikmah_jaya@gmail.com'),
(2, 'Bintang Motor Staff', 'Bintang Motor', 'Padangsidimpuan', '', '0', ''),
(3, '', 'Hannocs Medan', 'N/A (Pakai Mobil)', '', '0888', ''),
(4, '', 'Zaman Baru', 'Padangsidimpuan', '', '0', ''),
(5, '', 'Kedai Sebelah', 'Medan', '', '087896325687', ''),
(6, '', 'Sony Inc', 'Medan', '', '625384', ''),
(7, '', 'Asus Corp', 'Medan', '', '642536', ''),
(8, '', 'Fhjgf Ugft', 'Fyfyh\nHggg\nMedan', '', '5545266', ''),
(9, '', 'Carefour', 'Medan', '', '863256', '');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE IF NOT EXISTS `user` (
`id_user` int(11) NOT NULL,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `pin` int(6) NOT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `user_name`, `email`, `password`, `pin`) VALUES
(1, 'anwar', 'anwar_pasaribu@live.com', 'pangarongan', 7799);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
 ADD PRIMARY KEY (`id_barang`);

--
-- Indexes for table `barang_favorite`
--
ALTER TABLE `barang_favorite`
 ADD PRIMARY KEY (`id_barang_favorite`);

--
-- Indexes for table `belanja`
--
ALTER TABLE `belanja`
 ADD PRIMARY KEY (`id_belanja`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
 ADD PRIMARY KEY (`id_favorite`);

--
-- Indexes for table `gambar_barang`
--
ALTER TABLE `gambar_barang`
 ADD PRIMARY KEY (`id_gambar`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
 ADD PRIMARY KEY (`id`);

--
-- Indexes for table `log_harga`
--
ALTER TABLE `log_harga`
 ADD PRIMARY KEY (`id_log_harga`);

--
-- Indexes for table `log_stok`
--
ALTER TABLE `log_stok`
 ADD PRIMARY KEY (`id_log_stok`);

--
-- Indexes for table `log_transaksi`
--
ALTER TABLE `log_transaksi`
 ADD PRIMARY KEY (`id_transaksi`);

--
-- Indexes for table `merek`
--
ALTER TABLE `merek`
 ADD PRIMARY KEY (`id_merek`);

--
-- Indexes for table `penjual`
--
ALTER TABLE `penjual`
 ADD PRIMARY KEY (`id_penjual`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
 ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
MODIFY `id_barang` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=72;
--
-- AUTO_INCREMENT for table `barang_favorite`
--
ALTER TABLE `barang_favorite`
MODIFY `id_barang_favorite` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `belanja`
--
ALTER TABLE `belanja`
MODIFY `id_belanja` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `favorite`
--
ALTER TABLE `favorite`
MODIFY `id_favorite` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `gambar_barang`
--
ALTER TABLE `gambar_barang`
MODIFY `id_gambar` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `log_harga`
--
ALTER TABLE `log_harga`
MODIFY `id_log_harga` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `log_stok`
--
ALTER TABLE `log_stok`
MODIFY `id_log_stok` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `log_transaksi`
--
ALTER TABLE `log_transaksi`
MODIFY `id_transaksi` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `merek`
--
ALTER TABLE `merek`
MODIFY `id_merek` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `penjual`
--
ALTER TABLE `penjual`
MODIFY `id_penjual` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
