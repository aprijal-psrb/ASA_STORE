-- MySQL dump 10.13  Distrib 5.5.43, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: pasaribu_store
-- ------------------------------------------------------
-- Server version	5.5.43-0ubuntu0.14.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS pasaribu_store;
 
use pasaribu_store;


--
-- Table structure for table `barang`
--

DROP TABLE IF EXISTS `barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang` (
  `id_barang` int(11) NOT NULL AUTO_INCREMENT,
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
  `id_favorite` tinyint(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_barang`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang`
--

LOCK TABLES `barang` WRITE;
/*!40000 ALTER TABLE `barang` DISABLE KEYS */;
INSERT INTO `barang` VALUES (1,1,4,2,0,'Lampu Depan V.R New / Supra',20,'Kotak',65000,'2014-07-15','KODE','LOKASI',1,'Lampu Depan V.R New dengan kualitas prima',4),(2,1,1,1,0,'Samsung 4K 81 Inch LED TV',3,'Unit',85000000,'2014-11-05','KODE','LOKASI',1,'TV dengan ketajaman layar mengagumkan dengan resolusi UHD (4K), rasakan sensasi menonton iMax di rumah Anda.',0),(10,1,5,2,0,'Lampu Sipion S75',35,'Kotak',75000,'2014-11-15','KODE','LOKASI',1,'Lampu depan (biasa)',0),(11,1,2,2,0,'Lampu Depan V.R New Aspira',16,'Lusin',60000,'2014-11-16','KODE','LOKASI',1,'Lampu Biasa',4),(12,1,4,2,0,'Pangkon V.R New',1,'Kotak',45000,'2014-11-16','KODE','LOKASI',1,'Kualitas nomor 1',3),(13,1,4,2,0,'Batok Depan V.R New (Aspira)',1,'Kotak',46000,'2014-11-16','KODE','LOKASI',1,'Batok utk motor tipe V.R New',0),(28,1,17,2,0,'Ban Dalam Artko (Swallow)',200,'Kotak',45000,'2014-11-16','KODE','LOKASI',1,'Ban Dalam Sorong/Artko Biasa',5),(29,1,4,2,0,'Kunci Kontak RX King, Asli',23,'Kotak',85000,'2014-11-16','KODE','LOKASI',1,'Kunci kontak kualitas bagus,\npersamaan utk GL King, Honda, dsb. :v',0),(30,1,4,2,0,'Kunci Kontak RX King, Seken',3,'Kotak',45000,'2014-11-16','KODE','LOKASI',1,'Harga Murah',0),(32,1,2,2,0,'Kol TV 32\'\'',25,'Unit',8000,'2014-11-17','KODE','LOKASI',1,'Test',9),(33,1,8,1,0,'Lampu LED 50 Watt - Hannocs',12,'Kotak',75000,'2014-11-25','KODE','LOKASI',1,'Lampu hemat energy yang tetap terang walau listrik sedang tidak stabil.',0),(34,1,9,4,0,'Philips LED 20 Watt - Hannocs',12,'Kotak',55000,'2014-11-25','KODE','LOKASI',1,'Lampu hemat energy yang tetap terang walau listrik sedang tidak stabil.',4),(39,1,4,2,0,'Filter Oil TAP',12,'Lusin',500,'2014-12-10','KODE','LOKASI',1,'asasa sdsd',0),(40,1,9,2,0,'Yamalube Matic',10,'Botol',33500,'2014-12-11','KODE','LOKASI',1,'Oli utk matic',3),(42,1,5,2,0,'Speaker 14 Inch',25,'Kotak',200000,'2014-12-15','KODE','LOKASI',1,'Speaker Kualitas Prima.',0),(55,0,31,1,0,'Trafo',39,'Unit',60000,'0000-00-00','','',1,'Trafo 5A',5),(56,0,32,3,0,'Buku Android',1,'Buah',90000,'0000-00-00','','',1,'Buku belajar',0),(63,0,33,7,0,'Asus K46CB',1,'Unit',6400000,'2015-04-12','','',1,'Laptop saya.',0),(72,0,40,4,0,'Baut 10',100,'Buah',1500,'2015-05-04','','',6,'Ukuran 1 inch',0);
/*!40000 ALTER TABLE `barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `barang_favorite`
--

DROP TABLE IF EXISTS `barang_favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `barang_favorite` (
  `id_barang_favorite` int(11) NOT NULL AUTO_INCREMENT,
  `id_favorite` int(11) NOT NULL,
  `id_barang` int(11) NOT NULL,
  `tgl_favorite` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_barang_favorite`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `barang_favorite`
--

LOCK TABLES `barang_favorite` WRITE;
/*!40000 ALTER TABLE `barang_favorite` DISABLE KEYS */;
INSERT INTO `barang_favorite` VALUES (1,3,2,'2014-12-05 02:57:38'),(3,3,1,'2014-12-05 03:01:58');
/*!40000 ALTER TABLE `barang_favorite` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'NO_AUTO_VALUE_ON_ZERO' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `after_added_barang_favorite` AFTER INSERT ON `barang_favorite`
 FOR EACH ROW BEGIN
update barang set id_favorite = NEW.id_favorite where id_barang = NEW.id_barang;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `belanja`
--

DROP TABLE IF EXISTS `belanja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `belanja` (
  `id_belanja` int(11) NOT NULL AUTO_INCREMENT,
  `id_barang` int(11) NOT NULL,
  `tgl_belanja` date NOT NULL,
  `status_belanja` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_belanja`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `belanja`
--

LOCK TABLES `belanja` WRITE;
/*!40000 ALTER TABLE `belanja` DISABLE KEYS */;
/*!40000 ALTER TABLE `belanja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `favorite`
--

DROP TABLE IF EXISTS `favorite`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `favorite` (
  `id_favorite` int(11) NOT NULL AUTO_INCREMENT,
  `warna_favorite` varchar(10) NOT NULL,
  `nama_favorite` varchar(50) NOT NULL,
  `deskripsi` varchar(100) NOT NULL,
  PRIMARY KEY (`id_favorite`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `favorite`
--

LOCK TABLES `favorite` WRITE;
/*!40000 ALTER TABLE `favorite` DISABLE KEYS */;
INSERT INTO `favorite` VALUES (1,'#ff684545','Uncategorized','Semua list dalam favorite'),(2,'#ff28ffe0','Jarang Laku','Barang-barang yang jarang ada pembeli'),(3,'#ffff00df','Barang Mewah','Barang dengan harga yang cukup mahal, jarang laku.'),(4,'#ff82ff00','Langka','Barang-barang yang langka di pasaran.'),(5,'#ffff0000','Coba','');
/*!40000 ALTER TABLE `favorite` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gambar_barang`
--

DROP TABLE IF EXISTS `gambar_barang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gambar_barang` (
  `id_gambar` int(11) NOT NULL AUTO_INCREMENT,
  `lokasi_gambar` varchar(200) NOT NULL,
  `deskripsi_gambar` varchar(200) NOT NULL,
  PRIMARY KEY (`id_gambar`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gambar_barang`
--

LOCK TABLES `gambar_barang` WRITE;
/*!40000 ALTER TABLE `gambar_barang` DISABLE KEYS */;
INSERT INTO `gambar_barang` VALUES (2,'http://dev.mysql.com/common/logos/logo-mysql-110x57.png','Gambar Lampu Depan V.R New');
/*!40000 ALTER TABLE `gambar_barang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kategori`
--

DROP TABLE IF EXISTS `kategori`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kategori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama_kategori` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kategori`
--

LOCK TABLES `kategori` WRITE;
/*!40000 ALTER TABLE `kategori` DISABLE KEYS */;
INSERT INTO `kategori` VALUES (1,'Elektronik'),(4,'Laptop'),(6,'Automotif'),(7,'TV');
/*!40000 ALTER TABLE `kategori` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_harga`
--

DROP TABLE IF EXISTS `log_harga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_harga` (
  `id_log_harga` int(11) NOT NULL AUTO_INCREMENT,
  `id_barang` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `tgl_harga` date NOT NULL,
  `potongan_harga` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_log_harga`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_harga`
--

LOCK TABLES `log_harga` WRITE;
/*!40000 ALTER TABLE `log_harga` DISABLE KEYS */;
INSERT INTO `log_harga` VALUES (1,2,80000000,'2014-11-05',0),(2,1,60000,'2014-07-15',0);
/*!40000 ALTER TABLE `log_harga` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_stok`
--

DROP TABLE IF EXISTS `log_stok`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_stok` (
  `id_log_stok` int(11) NOT NULL AUTO_INCREMENT,
  `id_barang` int(11) NOT NULL,
  `jumlah_stok` int(11) NOT NULL,
  `tgl_muat_stok` date NOT NULL,
  PRIMARY KEY (`id_log_stok`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_stok`
--

LOCK TABLES `log_stok` WRITE;
/*!40000 ALTER TABLE `log_stok` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_stok` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `log_transaksi`
--

DROP TABLE IF EXISTS `log_transaksi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `log_transaksi` (
  `id_transaksi` int(11) NOT NULL AUTO_INCREMENT,
  `id_barang` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `tgl_transaksi` int(11) NOT NULL,
  `keterangan` int(11) NOT NULL,
  PRIMARY KEY (`id_transaksi`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `log_transaksi`
--

LOCK TABLES `log_transaksi` WRITE;
/*!40000 ALTER TABLE `log_transaksi` DISABLE KEYS */;
/*!40000 ALTER TABLE `log_transaksi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merek`
--

DROP TABLE IF EXISTS `merek`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merek` (
  `id_merek` int(11) NOT NULL AUTO_INCREMENT,
  `nama_merek` varchar(100) NOT NULL,
  `logo_merek` varchar(200) NOT NULL,
  `deskripsi_merek` varchar(200) NOT NULL,
  PRIMARY KEY (`id_merek`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merek`
--

LOCK TABLES `merek` WRITE;
/*!40000 ALTER TABLE `merek` DISABLE KEYS */;
INSERT INTO `merek` VALUES (1,'Samsung','','Samsung Elektronik'),(2,'LG','','LG Electronic'),(3,'Polytron','','Merek Taiwan'),(4,'Aspira','','Merek Jepang, kualitas prima'),(5,'Ichidai','',''),(6,'Apple','',''),(7,'IBM','',''),(8,'Hannocs','',''),(9,'Yamalube','',''),(14,'Itachi','',''),(16,'Oregon','',''),(17,'Swallow','',''),(18,'Tidak Ada Merek','',''),(19,'Penzoil','',''),(28,'Microsoft','',''),(33,'Asus','',''),(35,'Sony','','');
/*!40000 ALTER TABLE `merek` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `penjual`
--

DROP TABLE IF EXISTS `penjual`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `penjual` (
  `id_penjual` int(11) NOT NULL AUTO_INCREMENT,
  `nama_penjual` varchar(150) NOT NULL,
  `nama_toko` varchar(100) NOT NULL,
  `alamat_toko` varchar(200) NOT NULL,
  `geolocation` varchar(100) NOT NULL,
  `kontak_toko` varchar(100) NOT NULL DEFAULT '0',
  `email_toko` varchar(100) NOT NULL,
  PRIMARY KEY (`id_penjual`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `penjual`
--

LOCK TABLES `penjual` WRITE;
/*!40000 ALTER TABLE `penjual` DISABLE KEYS */;
INSERT INTO `penjual` VALUES (1,'Umak Riska','Hikmah Jaya','Jalan Merdeka, Padangsidimpuan','','0','hikmah_jaya@gmail.com'),(2,'Bintang Motor Staff','Bintang Motor','Padangsidimpuan','','0',''),(3,'','Hannocs Medan','N/A (Pakai Mobil)','','0888',''),(4,'','Zaman Baru','Padangsidimpuan','','0','');
/*!40000 ALTER TABLE `penjual` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) NOT NULL,
  `email` varchar(200) NOT NULL,
  `password` varchar(50) NOT NULL,
  `pin` int(6) NOT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'anwar','anwar_pasaribu@live.com','pangarongan',7799);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-05-04  7:37:20
