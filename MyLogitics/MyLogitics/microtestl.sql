/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.22 : Database - microtest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`microtest` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `microtest`;

/*Table structure for table `acrule` */

DROP TABLE IF EXISTS `acrule`;

CREATE TABLE `acrule` (
  `sentAddressId` int NOT NULL,
  `receiveAddressId` int NOT NULL,
  `airWeight` double DEFAULT NULL,
  `airVolume` double DEFAULT NULL,
  `trainWeight` double DEFAULT NULL,
  `trainVolume` double DEFAULT NULL,
  PRIMARY KEY (`sentAddressId`,`receiveAddressId`),
  KEY `receiveAddressId` (`receiveAddressId`),
  CONSTRAINT `acrule_ibfk_1` FOREIGN KEY (`sentAddressId`) REFERENCES `address` (`addressId`),
  CONSTRAINT `acrule_ibfk_2` FOREIGN KEY (`receiveAddressId`) REFERENCES `address` (`addressId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `acrule` */

insert  into `acrule`(`sentAddressId`,`receiveAddressId`,`airWeight`,`airVolume`,`trainWeight`,`trainVolume`) values 
(1,11,100,200,50,80);

/*Table structure for table `address` */

DROP TABLE IF EXISTS `address`;

CREATE TABLE `address` (
  `addressId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`addressId`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `address` */

insert  into `address`(`addressId`,`name`) values 
(1,'陕西省'),
(2,'陕西省西安市灞桥区毛西村2号房'),
(3,'陕西省西安市灞桥区毛西村3号房'),
(4,'陕西省西安市灞桥区毛西村4号房'),
(5,'陕西省西安市灞桥区毛西村5号房'),
(6,'陕西省西安市灞桥区毛西村6号房'),
(7,'陕西省西安市灞桥区毛西村7号房'),
(8,'陕西省西安市灞桥区毛西村8号房'),
(9,'陕西省西安市灞桥区毛西村9号房'),
(10,'陕西省西安市灞桥区毛西村10号房'),
(11,'广东省佛山市南海区华南师范大学E栋1号房'),
(12,'广东省佛山市南海区华南师范大学E栋2号房'),
(13,'广东省佛山市南海区华南师范大学E栋3号房'),
(14,'广东省佛山市南海区华南师范大学E栋4号房'),
(15,'广东省佛山市南海区华南师范大学E栋5号房');

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `oId` int NOT NULL AUTO_INCREMENT,
  `openId` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sender` varchar(15) DEFAULT NULL,
  `senderAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `senderPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `receiver` varchar(15) DEFAULT NULL,
  `receiverAddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `receiverPhone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sendAddressId` int DEFAULT NULL,
  `receiveAddressId` int DEFAULT NULL,
  `transport` varchar(10) DEFAULT NULL,
  `goodsWeight` double DEFAULT NULL,
  `goodsVolume` double DEFAULT NULL,
  `goodsNum` int DEFAULT NULL,
  `insurance` int DEFAULT NULL,
  `price` double DEFAULT NULL,
  `placedTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `pickedTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `receivedTime` datetime DEFAULT CURRENT_TIMESTAMP,
  `isPay` int DEFAULT NULL,
  `state` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`oId`),
  KEY `sendAddressId` (`sendAddressId`),
  KEY `receiveAddressId` (`receiveAddressId`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`sendAddressId`) REFERENCES `address` (`addressId`),
  CONSTRAINT `orders_ibfk_2` FOREIGN KEY (`receiveAddressId`) REFERENCES `address` (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`oId`,`openId`,`sender`,`senderAddress`,`senderPhone`,`receiver`,`receiverAddress`,`receiverPhone`,`sendAddressId`,`receiveAddressId`,`transport`,`goodsWeight`,`goodsVolume`,`goodsNum`,`insurance`,`price`,`placedTime`,`pickedTime`,`receivedTime`,`isPay`,`state`) values 
(1,'a','xf','陕西省西安市灞桥区毛西村4号房','13412341234','小红4','广东省佛山市南海区华南师范大学E栋4号房','13412341234',6,14,'飞机',5,6,1,100,1001111.3333,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',1,'Cancelled'),
(2,'aaaa','小明52','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'Received'),
(9,'a','小明5','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'a'),
(10,'a','小明5','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'a'),
(11,'a','小明5','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'a'),
(12,'a','小明5','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'a'),
(13,'a','小明5','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'a'),
(14,'a','小明5','陕西省西安市灞桥区毛西村5号房','13412341234','小红5','广东省佛山市南海区华南师范大学E栋5号房','13412341234',5,15,'飞机',5,6,1,100,1000,'2021-08-16 13:32:31','2021-08-16 13:32:31','2021-08-16 13:32:31',0,'a');

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `userPermission` int NOT NULL,
  `api` varchar(255) DEFAULT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `permission` */

insert  into `permission`(`userPermission`,`api`,`id`) values 
(1,'/user/updateUser',1),
(1,'/user/searchUser',4),
(1,'/user/deleteUser',5),
(1,'/user/findAllUser',6),
(1,'/user/delete',7),
(1,'/user/modifyUserInfo',8),
(1,'/user/register',9),
(1,'/order/findOrderByPlacedTime',11),
(1,'/order/findAllOrder',12),
(1,'/order/delOrder',13),
(1,'/order/findOrder',14),
(1,'/order/addOrder',15),
(1,'/order/editOrder',16),
(2,'/order/findOrderByPlacedTime',18),
(2,'/order/findAllOrder',19),
(2,'/order/delOrder',20),
(2,'/order/findOrder',21),
(2,'/order/addOrder',22),
(2,'/order/editOrder',23),
(3,'/order/editOrder',24),
(3,'/order/updateOrder',25),
(1,'/order/updateOrder',26),
(2,'/order/updateOrder',27);

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `tokenId` int NOT NULL AUTO_INCREMENT,
  `tokenName` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `effectiveTime` int DEFAULT '30',
  `loginName` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`tokenName`),
  KEY `tokenId` (`tokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8;

/*Data for the table `token` */

insert  into `token`(`tokenId`,`tokenName`,`createTime`,`effectiveTime`,`loginName`) values 
(85,'029042eac24c40e5bd086a9562dadbe7','2021-08-24 15:24:42',30,'员工5'),
(70,'0386b902cfa44aca96454230f8eb15e5','2021-08-24 12:18:07',30,'员工3'),
(115,'0565960e3cf5470f8ff32133fa33fb45','2021-08-24 23:29:22',30,'员工5'),
(121,'06f8e7ad94c3484588e9935869ee3327','2021-08-24 23:48:38',30,'员工5'),
(80,'07b92e1df45c46969af048283261ace3','2021-08-24 14:36:59',30,'员工3'),
(12,'0c088e79c1204ed79464e3f3b28ffeec','2021-08-22 18:12:29',30,'员工3'),
(131,'0e09b6f10e5746468467576d211bc8f0','2021-08-25 15:30:09',30,'员工5'),
(144,'0e39fa2fa49649fc90950889bb1c5752','2021-08-25 16:21:08',30,'员工5'),
(152,'0fe34f01faf746d78ddfe99a96fec2d9','2021-08-25 17:00:00',30,'员工5'),
(141,'1009a4da1b1543fea22a15628d943c49','2021-08-25 16:16:02',30,'员工5'),
(17,'1282804f5d214ddab9d7a1ce56e0598c','2021-08-22 18:55:55',30,'员工3'),
(172,'12dc9f95ec2e4fb8911421260df58de8','2021-08-25 21:17:59',30,'员工5'),
(56,'12e52cb400a34a248d0c67518bccf3e6','2021-08-24 09:30:22',30,'员工3'),
(79,'12f7efe2ddd84108ba0d9a9c711eac82','2021-08-24 14:31:58',30,'员工3'),
(94,'147c7297706d4e90a7b6f454ce003b60','2021-08-24 16:13:39',30,'员工5'),
(82,'148948bc6a3b44ff8c2de6ac9cd057eb','2021-08-24 14:41:44',30,'员工3'),
(168,'150f34c74618420ebbd356728dfc347a','2021-08-25 19:05:29',30,'员工5'),
(24,'179f2255a95448d3aac2d5cc2c00885b','2021-08-23 13:58:16',1,'员工3'),
(57,'17ab23a8b2ee42ccb09d734879bec55c','2021-08-24 09:31:24',30,'员工3'),
(101,'18df721647ff42a283c673caf509e0c1','2021-08-24 22:37:05',30,'员工5'),
(114,'1a412c793bc04b22a2470d94e36bdd6d','2021-08-24 23:27:11',30,'员工5'),
(153,'1b8be9c3adf448a098cf20725e6e5d21','2021-08-25 17:06:29',30,'员工5'),
(13,'1be08a6ae4584ac4b0567381d0acd729','2021-08-22 18:15:23',30,'员工3'),
(89,'1d04c3714198453b8acdc3fbf0dd1747','2021-08-24 15:48:08',30,'员工5'),
(145,'1d42d8a4cade4d70a33f46e267374795','2021-08-25 16:33:33',30,'员工5'),
(50,'1e0f9d4b91584280a5a1a396d5c9e588','2021-08-23 23:42:42',30,'员工3'),
(92,'1f50f21b1b6b42118fb04ae4f29a7963','2021-08-24 16:10:27',30,'员工5'),
(117,'23c49a6764874567a8eb99e8d8a52f9c','2021-08-24 23:39:43',30,'员工5'),
(6,'245b0235b93947b48f8cd166571473e2','2021-08-22 16:24:59',30,'员工3'),
(21,'256c8e29b81b40b8951c49556d0b765f','2021-08-22 19:58:44',30,'员工3'),
(43,'25a2ca0aadcc47f0b8102595a7f42e17','2021-08-23 21:49:04',30,'员工3'),
(160,'2babcd6fcac64a818986c8821f51efa3','2021-08-25 17:30:37',30,'员工5'),
(181,'2bfb9bb3b81a438da440ab7d6ed0c4eb','2021-08-25 23:11:51',30,'员工5'),
(151,'2d7ff3e333734ba392943c80bc23c395','2021-08-25 16:52:12',30,'员工5'),
(110,'3045a1f10a3f45de914c1d3548d93a69','2021-08-24 23:13:05',30,'员工5'),
(120,'30f9992bc57343ca8b935d51d30e3711','2021-08-24 23:48:16',30,'员工5'),
(25,'311e7fcea101406ab439f3c28541ccb8','2021-08-23 14:03:33',1,'员工3'),
(134,'34ea0e3cd38246d6b6ce92b1f4e4000c','2021-08-25 15:38:35',30,'员工5'),
(33,'388e3b5aeacb4427a98357489add2001','2021-08-23 15:06:56',1,'员工3'),
(30,'38f81dfa3efe42b2bc4490ff523f8de5','2021-08-23 14:38:14',1,'员工3'),
(81,'3b80ca2f803b4178b03bfd9c26e6d1a5','2021-08-24 14:38:57',30,'员工3'),
(147,'3d8e27f0835e4c34bdb5fa2750943206','2021-08-25 16:43:48',30,'员工5'),
(156,'3f47d07cc50146a6977a74bb499f7c8c','2021-08-25 17:19:56',30,'员工5'),
(3,'4410512eb1c24928a3b698cbb5ed016f','2021-08-22 11:53:26',30,'员工4'),
(154,'461a8038d693477dbe0fe73ff6fd2380','2021-08-25 17:14:15',30,'员工5'),
(48,'47932ce959064dd4a8d39a78b554f5ac','2021-08-23 23:20:22',30,'员工3'),
(52,'4856a896f6794a25bce2b7fa8e1de4c4','2021-08-24 00:07:10',30,'员工3'),
(15,'49fa53c121244019a5d456c478a393e2','2021-08-22 18:45:50',30,'员工3'),
(46,'4a38288d78464bf2a649df38e213c27a','2021-08-23 22:49:03',30,'员工3'),
(142,'4b5f736ec2df49b894332c41e8c76f1a','2021-08-25 16:18:50',30,'员工5'),
(95,'4f18e69d734e434db806cf9f878b7602','2021-08-24 17:27:19',30,'员工5'),
(184,'50eda22715b945309c03122f69f97d6d','2021-08-25 23:16:10',30,'员工5'),
(72,'51ff07e23ec947d9ae06a75feb1344b1','2021-08-24 13:15:41',30,'员工3'),
(123,'5287347c469948d9ad2de98274d43fcb','2021-08-25 10:33:41',30,'员工5'),
(174,'54181cf5939949dbb14cccdf9d364130','2021-08-25 21:34:09',30,'员工5'),
(4,'55bbad02774042beb8086bb1911218a0','2021-08-22 12:00:44',30,'员工3'),
(49,'57a99c9af8b1471d8ea0ba10b29a53d1','2021-08-23 23:29:52',30,'员工3'),
(171,'59133ce4fc5a4ef0ae423a802550d13b','2021-08-25 20:44:53',30,'员工5'),
(14,'5ab1feb2a83a4880be3814bb0e5ff4b1','2021-08-22 18:40:59',30,'员工3'),
(133,'5b63107912ac4e639324197d197ad3f5','2021-08-25 15:36:30',30,'员工5'),
(158,'5beff6d5322f458c9fd314b99817fb68','2021-08-25 17:21:21',30,'员工5'),
(34,'5cd4f8958e994fc499801f5463ce19ed','2021-08-23 15:10:24',30,'员工3'),
(124,'5d317d3ec59f4f8993abbdbed7d64d30','2021-08-25 10:46:27',30,'员工5'),
(36,'6189a85f8cd149e2b1a6590181fd12de','2021-08-23 15:25:37',30,'员工3'),
(66,'61919d2acf00410bbbc612234a09b4f1','2021-08-24 12:07:47',30,'员工3'),
(76,'63c47b16ba884b74b738dbfb45ddcc6e','2021-08-24 13:40:20',30,'员工3'),
(112,'63e732bc6a174f6aaf53f31fd0658a90','2021-08-24 23:15:17',30,'员工5'),
(162,'65c0a83b598641aea982567faa20c015','2021-08-25 17:34:24',30,'员工5'),
(71,'68823ca4f9004795ac5cd819797326f6','2021-08-24 13:09:10',30,'员工3'),
(173,'68a405d2e9474c9b9a5db3ef51b48cb1','2021-08-25 21:31:28',30,'员工5'),
(179,'6b9778c8b44c4aff90370ad56170ed75','2021-08-25 22:52:41',30,'员工5'),
(155,'6e5a40a9fe1d4a2195b9a5518eee380d','2021-08-25 17:17:06',30,'员工5'),
(47,'70247332619e4ba9887d1cce529f6e3f','2021-08-23 23:16:10',30,'员工3'),
(106,'709e4268b1284214a9b81b80e6436b86','2021-08-24 22:43:07',30,'员工5'),
(60,'70e92d320c304d4bace0036fb2caacff','2021-08-24 10:11:27',30,'员工3'),
(27,'71b49bb621694da0ba7b53c17750bccf','2021-08-23 14:22:18',1,'员工3'),
(11,'7284188e53a743f6a14c733cb617516c','2021-08-22 18:08:14',30,'员工3'),
(150,'72e6a720b9ef4cdea4c91d268b04ac95','2021-08-25 16:52:12',30,'员工5'),
(64,'7420ec0f7f2e45378157b6ada140dd22','2021-08-24 11:34:21',30,'员工3'),
(28,'74624b84547b4a56ae2e7780ce4c9a3c','2021-08-23 14:23:21',1,'员工3'),
(98,'781e619522ab450288ffd30fa61f4a57','2021-08-24 22:34:13',30,'员工5'),
(157,'7890840a2eb44cd4878a7484534740f2','2021-08-25 17:21:21',30,'员工5'),
(35,'7a236362e48145b89b16e41293a717fe','2021-08-23 15:23:39',30,'员工3'),
(78,'7c765a6558b84a07b07d905fb2fc1172','2021-08-24 14:22:37',30,'员工3'),
(138,'7cdbed94cab14a288b1e9b92d7cd13ac','2021-08-25 16:00:18',30,'员工5'),
(83,'800fbdac658e435582e47f95f231c63b','2021-08-24 14:54:30',30,'员工5'),
(42,'8101b341c14c46bdbe444db2f4f5b8af','2021-08-23 21:48:03',30,'员工3'),
(105,'8323f566b8ec4774b587b953a917726d','2021-08-24 22:40:24',30,'员工5'),
(87,'8350f82c26ed4e4c9735b2d76373f991','2021-08-24 15:38:23',30,'员工5'),
(103,'84ae3637e1334824a1b8190d293c9779','2021-08-24 22:39:24',30,'员工5'),
(137,'863c2cd56baa45b5ae1c020e16b56790','2021-08-25 15:41:45',30,'员工5'),
(26,'884e11543f65449b8988eb39cb72a353','2021-08-23 14:11:24',1,'员工3'),
(19,'89780b897a784bd9b5436b7b3ebee8f9','2021-08-22 18:59:27',30,'员工3'),
(67,'8ad896f2dcca43a1b6d0f448c9ce7835','2021-08-24 12:08:58',30,'员工3'),
(58,'8b6a3d8cff5046898eb22e3e4114f0ae','2021-08-24 09:53:17',30,'员工3'),
(99,'8c2d39202b3a47f68240872fc9e27724','2021-08-24 22:35:03',30,'员工5'),
(32,'8d7933afaccd4dc7b95b3c6d3e2a26ab','2021-08-23 14:59:32',1,'员工3'),
(161,'8f3bc9bc98324077b4ded5554ea011c9','2021-08-25 17:32:32',30,'员工5'),
(107,'928fd5221fb84f52b5ee6d712f4a7e70','2021-08-24 22:51:50',30,'员工5'),
(149,'9462610430eb4b91a0ad9be822a94018','2021-08-25 16:48:44',30,'员工5'),
(116,'94e4b8527e384f65ac4621863f0b062f','2021-08-24 23:37:01',30,'员工5'),
(175,'952022bac37e4f768c4ce2befc2cad7d','2021-08-25 21:39:57',30,'员工5'),
(126,'968f0b4443f949439bc31186b291311e','2021-08-25 11:46:44',30,'员工5'),
(125,'97d5d5fb613a4c52a7726abb5d017eff','2021-08-25 11:01:10',30,'员工5'),
(29,'98ccc0b031f9408ea678a25c62ffb1fe','2021-08-23 14:23:52',1,'员工3'),
(9,'98d7d5d139914361abf19bbbec664f1b','2021-08-22 16:48:53',30,'员工3'),
(119,'98f40f21ae0444daafdd8b6aee4cdc85','2021-08-24 23:41:45',30,'员工5'),
(68,'99688a384f7046dc93ac2c3202086a76','2021-08-24 12:11:33',30,'员工3'),
(88,'9a719c9bfb1f478cb615e74b2aad2dfd','2021-08-24 15:45:06',30,'员工5'),
(188,'9bf9c2528768405c89491266e547754a','2021-08-25 23:29:41',30,'员工5'),
(136,'9c208e6c23dc4734847559a23cd782c2','2021-08-25 15:40:17',30,'员工5'),
(128,'9c86f7536b994b83bea6af5b6657e697','2021-08-25 11:52:32',30,'员工5'),
(73,'9e8bd926e9e54d1e84cc7bcee209c3d6','2021-08-24 13:32:11',30,'员工3'),
(31,'9fa0004093804d699e9e84290709163d','2021-08-23 14:54:07',1,'员工3'),
(177,'a1ad1de99e7e4629be66119a6709a40f','2021-08-25 22:07:37',30,'员工5'),
(148,'a3e026d0a60241b4b32de4e372c8c553','2021-08-25 16:45:23',30,'员工5'),
(140,'a4d426902546400bb5e2600f3cabdd5c','2021-08-25 16:15:25',30,'员工5'),
(62,'a5636e2a67ca41b6ad6e309e057f24ef','2021-08-24 10:50:29',30,'员工3'),
(22,'a62bb7c286794192a77ab60defb05668','2021-08-23 13:48:56',30,'员工3'),
(100,'a73156b5c13846409e6a1573f2a19b0e','2021-08-24 22:35:49',30,'员工5'),
(176,'a73934f27da24b17b0e5bae8772f7a7e','2021-08-25 21:42:48',30,'员工5'),
(59,'a7f8bb4411bc47bd9aad32f05bdc024a','2021-08-24 09:54:30',30,'员工3'),
(180,'aadcd2d8612a4d44b84a3d8b9365e6d6','2021-08-25 23:00:40',30,'员工5'),
(108,'ab9b88666f034e99a3cef91edeee7846','2021-08-24 22:56:23',30,'员工5'),
(111,'abecb9a809b642dabf1c35713a452851','2021-08-24 23:13:47',30,'员工5'),
(84,'ae7a0e5a55e84cf78700fe869cbb44ee','2021-08-24 15:13:02',30,'员工5'),
(77,'aed85848975e4dc68b88b3e021a51a6c','2021-08-24 13:41:09',30,'员工3'),
(93,'af7b4f58f3204da5a397d5390e203aa5','2021-08-24 16:11:52',30,'员工5'),
(16,'b08f66be19064259ba7df76644d40d1e','2021-08-22 18:51:02',30,'员工3'),
(51,'b29f396a4d7e430fb043948f527752c2','2021-08-23 23:53:16',30,'员工3'),
(97,'b2e00b53d22e4b05b04351b8ccc9645a','2021-08-24 22:32:22',30,'员工5'),
(44,'b31e472130594e38bab5ef4780e15431','2021-08-23 21:50:22',30,'员工3'),
(159,'b3abef4952be4ad8acdc52188e3ec01d','2021-08-25 17:27:22',30,'员工5'),
(170,'b4aadcfcd3c9461d8280c795aee25b36','2021-08-25 20:43:15',30,'员工5'),
(53,'b4d531286e9e4fd69a76461d0671bb55','2021-08-24 00:08:41',30,'员工3'),
(135,'b8a15eef129e4abfbb67903b7c25eb62','2021-08-25 15:39:11',30,'员工5'),
(127,'b8bb795a8e2b484bb74038d39eaed2d8','2021-08-25 11:47:32',30,'员工5'),
(54,'b8e1b484d5784c3b829fa8dce47ae6f8','2021-08-24 00:09:32',30,'员工3'),
(23,'b92f183a3a06459ba3a29c5e05920368','2021-08-23 13:52:12',1,'员工3'),
(39,'b9693861044b4ecbb4d2566caf993db6','2021-08-23 20:40:02',30,'员工3'),
(113,'ba8f39ff8cbb422caaf2ddaca710f579','2021-08-24 23:18:13',30,'员工5'),
(182,'badcb0a37301426a8e367e597c1f1743','2021-08-25 23:13:29',30,'员工5'),
(122,'bbdf7b803e3b42be9187a4eecb0ee2db','2021-08-24 23:55:32',30,'员工5'),
(38,'bcea675ffc6a417793a4f98e06e06a42','2021-08-23 20:26:57',30,'员工3'),
(183,'c11cc35ec8334376b50a01851d40408b','2021-08-25 23:14:20',30,'员工5'),
(8,'c2792b1455d54c60b964b0075274d0e9','2021-08-22 16:39:23',30,'员工3'),
(164,'c3912306e74044ddbf4d2cfe3adbb6c0','2021-08-25 17:42:28',30,'员工5'),
(40,'c54ee1c8beec44adb1c0a66757f9713a','2021-08-23 20:55:47',30,'员工3'),
(41,'c65f3b775833487ab0c0a34450dbb87a','2021-08-23 21:03:14',30,'员工3'),
(75,'c67d707c425d41749ae517eb641ba314','2021-08-24 13:38:01',30,'员工3'),
(187,'c6e21632ab0746c6a1cabff0c6206fc6','2021-08-25 23:26:21',30,'员工5'),
(132,'c769686ec2354e69b9f63eb4d9a77167','2021-08-25 15:32:55',30,'员工5'),
(96,'c89eaa1acd1d4febb05a71b913130f8a','2021-08-24 17:43:24',30,'员工5'),
(63,'c8dfddb2a52c4fb1ba0a2afa8284cdba','2021-08-24 11:29:41',30,'员工3'),
(65,'cc9ddf2bcb9a43cd9a24d733616b4520','2021-08-24 11:39:42',30,'员工3'),
(139,'cdecf5a8d65442bb9bd70b4878b904f2','2021-08-25 16:13:20',30,'员工5'),
(102,'ceb16a1f6d7a4e059336dce76a195272','2021-08-24 22:39:16',30,'员工5'),
(104,'cf16a4c1cbfd4562a3a8ee30a53243dc','2021-08-24 22:39:49',30,'员工5'),
(129,'d2fad9628efd4ae0a36d019cf97fceba','2021-08-25 15:05:21',30,'员工5'),
(20,'d36e384d2d3b4173b7494996feff2dc6','2021-08-22 19:05:56',30,'员工3'),
(143,'d45afee0127c4e378e8d59bf47909c31','2021-08-25 16:20:33',30,'员工5'),
(169,'d4edd7af27ba471e8c019638201129f0','2021-08-25 20:32:18',30,'员工5'),
(74,'d5ffc87956114b998b5cbea1083595d0','2021-08-24 13:37:41',30,'员工3'),
(61,'d7e5922e973d41558bd02285d21eb8dc','2021-08-24 10:18:54',30,'员工3'),
(163,'d8342a5578bf4317b89e76e24a4d6792','2021-08-25 17:37:46',30,'员工5'),
(186,'dc1d23df555f4aa4b33d5957a49a945d','2021-08-25 23:25:53',30,'员工5'),
(7,'ddb27cf5a50742f6add16a4829347783','2021-08-22 16:35:10',30,'员工3'),
(45,'de6fc691e9d744cb8a1fad64fbc06dca','2021-08-23 22:42:42',30,'员工3'),
(18,'de977eef033e4662a8bddc9a2251f27a','2021-08-22 18:57:48',30,'员工3'),
(185,'deb9100e3d424e14906c0115a0947239','2021-08-25 23:17:32',30,'员工5'),
(118,'e2c8c7a3038d432490b3f981ad54180a','2021-08-24 23:40:45',30,'员工5'),
(178,'e5d295b8bb604405b4efbf0dde166503','2021-08-25 22:17:36',30,'员工5'),
(69,'e88c450a5c7a4731bb7c4dc61e535fea','2021-08-24 12:14:12',30,'员工3'),
(146,'e897d634dea245528d5a9bfa4f9345a0','2021-08-25 16:38:54',30,'员工5'),
(130,'ead644e81d224bee9c604a8ccd5d7dee','2021-08-25 15:10:06',30,'员工5'),
(10,'eb414b1c0f7e4d9daf9bef72adcba676','2021-08-22 18:00:50',30,'员工3'),
(86,'ee7f295dad5f4ec8adad5a688d379cf5','2021-08-24 15:32:00',30,'员工5'),
(109,'efa588b97c3340f288f9fa0ff6232d5b','2021-08-24 23:12:34',30,'员工5'),
(55,'f04e10009eae4990989ce377ea2daba8','2021-08-24 09:21:54',30,'员工3'),
(90,'f1217725298a477dab879ff4a840c1c9','2021-08-24 16:00:39',30,'员工5'),
(167,'f26182a5902b4c8b97338e0fd108130d','2021-08-25 17:45:43',30,'员工5'),
(37,'f3ff0ee8f4b74ac1b3d1373b744a7899','2021-08-23 20:16:13',30,'员工3'),
(165,'f5b0617bd3ee4c5aac4aff5b858879c0','2021-08-25 17:43:23',30,'员工5'),
(91,'f8ccdd8d5c864f05a08ecd3857ef1614','2021-08-24 16:09:32',30,'员工5'),
(5,'f9580a7e80e2421db3d8437c99712ef3','2021-08-22 16:22:33',30,'员工3'),
(166,'faada3e5a37543379da1f01edcabab26','2021-08-25 17:44:58',30,'员工5'),
(1,'员工2','2021-08-22 10:50:22',30,'员工2'),
(2,'员工3','2021-08-22 11:45:49',30,'员工3');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `username` varchar(15) DEFAULT NULL,
  `password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT '111',
  `permission` int DEFAULT '1' COMMENT '1老板;2地方负责人;3员工',
  `nickname` varchar(15) DEFAULT 'null',
  `phone` varchar(15) DEFAULT 'null',
  `addressId` int DEFAULT NULL,
  `email` varchar(20) DEFAULT 'null',
  `status` int DEFAULT NULL,
  PRIMARY KEY (`uid`),
  KEY `addressId` (`addressId`),
  KEY `permission` (`permission`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`uid`,`username`,`password`,`permission`,`nickname`,`phone`,`addressId`,`email`,`status`) values 
(1,'用户1','111',1,'1','1',1,'1@qq.com',NULL),
(3,'用户2','111',1,'1','1',1,'1@qq.com',NULL),
(5,'员工5','111',1,'普通员工','13512341234',5,'123456@qq.com',0),
(9,'员工9','111',1,'普通员工','13512341234',9,'123456@qq.com',1),
(10,'员工10','111',1,'普通员工','13512341234',10,'123456@qq.com',0),
(11,'地方负责人1','111',2,'地方1','13512341234',1,'123456@qq.com',0),
(12,'地方负责人1','111',2,'地方1','13512341234',1,'123456@qq.com',0),
(13,'地方负责人2','111',2,'地方2','13512341234',2,'123456@qq.com',0),
(14,'地方负责人3','111',2,'地方3','13512341234',3,'123456@qq.com',0),
(15,'地方负责人4','111',2,'地方4','13512341234',4,'123456@qq.com',0),
(16,'地方负责人5','111',2,'地方5','13512341234',5,'123456@qq.com',0),
(17,'地方负责人6','111',2,'地方6','13512341234',6,'123456@qq.com',0),
(18,'地方负责人7','111',2,'地方7','13512341234',7,'123456@qq.com',1),
(19,'地方负责人8','111',2,'地方8','13512341234',8,'123456@qq.com',0),
(20,'地方负责人9','111',2,'地方9','13512341234',9,'123456@qq.com',0),
(21,'地方负责人10','111',2,'地方10','13512341234',10,'123456@qq.com',0),
(22,'老板1','111',3,'大老板1','13512341234',1,'123456@qq.com',0),
(23,'老板2','111',3,'大老板2','13512341234',2,'123456@qq.com',0),
(24,'老板3','111',3,'大老板3','13512341234',3,'123456@qq.com',0),
(25,'老板4','111',3,'大老板4','13512341234',4,'123456@qq.com',0),
(26,'老板5','111',3,'大老板5','13512341234',5,'123456@qq.com',0),
(27,'老板6','111',3,'大老板6','13512341234',6,'123456@qq.com',0),
(28,'老板7','111',3,'大老板7','13512341234',7,'123456@qq.com',1),
(29,'老板8','111',3,'大老板8','13512341234',8,'123456@qq.com',0),
(30,'老板9','111',3,'大老板9','13512341234',9,'123456@qq.com',0),
(31,'老板10','111',3,'大老板10','13512341234',10,'123456@qq.com',0),
(35,'用户1','111',1,'1','1',1,'1@qq.com',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
