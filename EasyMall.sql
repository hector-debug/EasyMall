/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.26-log : Database - javaweb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`javaweb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `javaweb`;

/*Table structure for table `orderitem` */

DROP TABLE IF EXISTS `orderitem`;

CREATE TABLE `orderitem` (
  `order_id` varchar(100) NOT NULL DEFAULT '',
  `product_id` varchar(100) NOT NULL DEFAULT '',
  `buynum` int(11) DEFAULT NULL,
  PRIMARY KEY (`order_id`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderitem` */

insert  into `orderitem`(`order_id`,`product_id`,`buynum`) values ('099136d5-2da5-4add-bf5c-9c610bff1687','05e20c1a-0401-4c0a-82ab-6fb0f37db397',2),('0b3b096b-473f-4d66-86ea-bbf61c92a939','09f47493-214d-44bc-927d-6ce0bf89a057',1),('1417c418-5843-4831-822c-276b6a1a2f4d','17c3f20e-ef86-4857-9293-f29e52954a95',1),('1417c418-5843-4831-822c-276b6a1a2f4d','26128d47-423b-4220-8047-544ff899db50',1),('1417c418-5843-4831-822c-276b6a1a2f4d','6746c459-b284-4256-bbc6-1df60ba4a0a2',1),('82a7c139-9069-4dd1-9d5c-8a4aec7263a6','09f47493-214d-44bc-927d-6ce0bf89a057',1),('b14d1e4d-fa1e-4c6d-8a2f-d08fd85f97f5','05e20c1a-0401-4c0a-82ab-6fb0f37db397',1),('b6ed9ece-b3c3-4048-8308-3a366024beb9','17c3f20e-ef86-4857-9293-f29e52954a95',2),('b6ed9ece-b3c3-4048-8308-3a366024beb9','38a4a0f0-7c33-4e78-aa9e-1a3f7f193683',1),('c2be553b-b505-44c5-9ca7-516f880acd09','09f47493-214d-44bc-927d-6ce0bf89a057',2),('db1c8ccd-0919-45fe-a7b5-e61e84a166e2','05e20c1a-0401-4c0a-82ab-6fb0f37db397',1);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` varchar(100) NOT NULL,
  `money` double DEFAULT NULL,
  `receiverinfo` varchar(255) DEFAULT NULL,
  `paystate` int(11) DEFAULT NULL,
  `ordertime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `orders_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`id`,`money`,`receiverinfo`,`paystate`,`ordertime`,`user_id`) values ('099136d5-2da5-4add-bf5c-9c610bff1687',18000000,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:06:13',10),('0b3b096b-473f-4d66-86ea-bbf61c92a939',1000,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 16:11:37',10),('120e4176-324c-4426-b031-fbfdfb9f5bf2',900,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 16:16:50',10),('1417c418-5843-4831-822c-276b6a1a2f4d',392,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 16:12:24',10),('524abde1-6b08-40f5-8210-c9cf96e52833',0,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:07:56',10),('82a7c139-9069-4dd1-9d5c-8a4aec7263a6',1000,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:30:28',10),('9e32e2ec-84d1-4227-95ec-869eb7083a47',0,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:08:48',10),('b14d1e4d-fa1e-4c6d-8a2f-d08fd85f97f5',9000000,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:09:15',10),('b6ed9ece-b3c3-4048-8308-3a366024beb9',1360,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 16:17:12',10),('c2be553b-b505-44c5-9ca7-516f880acd09',2000,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:32:23',10),('db1c8ccd-0919-45fe-a7b5-e61e84a166e2',9000000,'广东省佛山市南海区狮山镇狮山大学城华南师范大学南海校区',0,'2019-11-13 13:09:50',10);

/*Table structure for table `products` */

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products` (
  `id` varchar(100) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  `pnum` int(11) DEFAULT NULL,
  `imgurl` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `products` */

insert  into `products`(`id`,`name`,`price`,`category`,`pnum`,`imgurl`,`description`) values ('05e20c1a-0401-4c0a-82ab-6fb0f37db397','靳老师别墅',9000000,'电子数码',202,'/WEB-INF/upload/5/e/d/5/4/5/e/b/5f0d34dc-157f-49ba-ad39-1b28927ba6ae_1005714.jpg','贵族的生活，美女很多'),('09f47493-214d-44bc-927d-6ce0bf89a057','爱疯9S(0315-01)',1000,'电子数码',181,'/WEB-INF/upload/5/2/3/4/7/8/d/c/1838eaa6-6459-420f-b8e2-6ea9f43c4b5e_dfd259ab-bcc7-43f6-a9d5-62872ff5671e.jpg','爱疯9S(0315-01)'),('103e5414-0da2-4fba-b92f-0ba876e08939','滑雪套装',2000,'日用百货',676,'/WEB-INF/upload/4/d/2/a/2/3/1/8/b3c3fc7a-222c-49be-9491-f466553d2284_386718.jpg','这种运动值得你拥有'),('17c3f20e-ef86-4857-9293-f29e52954a95','打印机',180,'电子数码',0,'/WEB-INF/upload/7/c/b/f/7/d/2/9/5e229aef-063f-4d0d-91df-2d4aa7167670_6f84843a-1d1e-49c7-b4ce-c035d7790171.jpg','一个神奇的打印机?!'),('26128d47-423b-4220-8047-544ff899db50','1208_01iphone7',12,'电子数码',985,'/WEB-INF/upload/2/1/0/3/f/0/3/5/2fb0b43b-4dbe-440b-899b-13c02a9f5475_22d124c9-df52-4cd4-88b3-691005f1cafe.jpg','测试商品'),('2ad0d041-8c5f-4b70-a0ef-1ca2fd476dba','战神主机',5888,'电子数码',200,'/WEB-INF/upload/b/4/1/b/d/9/8/8/e41cd642-2fc2-4cb2-b20a-f8a78405eee2_e9dd0d91-40c1-4db5-a888-244e825e9ce4.jpg',''),('36b9407f-746a-4956-988e-557122bc74d0','banana',5,'日用百货',49999,'/WEB-INF/upload/e/5/0/9/c/7/6/0/60746822-144c-4e98-8aaa-fca07e142a63_banana.jpg','国产大banana,好吃又好用'),('38a4a0f0-7c33-4e78-aa9e-1a3f7f193683','打印机021701',1000,'电子数码',9,'/WEB-INF/upload/8/6/b/0/5/9/e/f/1d25320d-e1b2-42bc-b890-981d58391cf0_6f84843a-1d1e-49c7-b4ce-c035d7790171.jpg','希望好用'),('3da04a08-a570-4945-91b5-cd0d63ace7b4','2017011002',10000,'电子数码',100,'/WEB-INF/upload/9/f/f/a/1/6/d/0/49617712-4018-4c0e-9e7a-5ebc4ff79ad1_6f84843a-1d1e-49c7-b4ce-c035d7790171.jpg','打印机'),('3f36ac54-5da0-4cd8-9991-2ee86cc348c2','金士顿8G内存条',800,'电子数码',258,'/WEB-INF/upload/2/2/b/7/f/2/f/4/06402c91-aa25-45d5-b0c3-3ac276a7cd05_244c59c6-bf0a-451b-81e6-18f8bb257e5f.jpg','3级内存条，拿货220，数量有限！'),('59622587-958e-43cb-b657-49619f60713e','陈子龙的等身抱枕',88,'日用百货',100,'/WEB-INF/upload/6/5/a/4/2/9/e/8/cb68faa0-0033-4517-bff0-5fb2f1f1019a_671434fae6cd7b89a26ce25e072442a7d8330efa.jpg','陈子龙的等身抱枕'),('6746c459-b284-4256-bbc6-1df60ba4a0a2','跳吧',200,'电子数码',999999,'/WEB-INF/upload/2/6/4/a/a/5/2/3/ee6c796a-6333-4cd5-a06e-271d876aac8c_589577.jpg','我可不捞你'),('6c28bc1a-9c9b-4be3-b1cf-0068565e64e4','兔子',59,'家用电器',885,'/WEB-INF/upload/1/f/b/5/3/4/e/4/61a8cdff-52f7-4fce-bdb5-570426022082_preview.jpg','我们的征途是星辰大海!'),('70ee3179-3e76-4a3d-bd30-55d740f022dc','极品水母',998,'日用百货',555,'/WEB-INF/upload/e/6/f/d/3/f/6/1/d2370fcb-dc8f-4405-9bf2-76e798a91567_Jellyfish.jpg','居家旅行，必备之选'),('77feb539-a575-487b-8500-df38520f3239','战地1 豪华版 Origin平台正版激活码',780,'图书杂志',85,'/WEB-INF/upload/5/e/e/6/c/4/9/b/67c1a752-9020-4372-bcda-3375ef01c878_preview.jpg','怎么样,想想办法,干他一炮?'),('a0390f80-bed7-4a92-9954-5e22e64cbe17','沙发',100,'电子数码',1,'/WEB-INF/upload/5/0/6/f/4/4/b/f/40ca42aa-8298-430a-9fa9-88d6156d7b18_c987f2c1-4123-4d87-83bd-fe2fb221e272.jpg','好用'),('a08b13e9-c16a-4657-94ee-3b9bee2bd9c6','华为荣耀8',5000,'电子数码',2000,'/WEB-INF/upload/4/a/d/8/8/c/4/0/236ac480-db3a-4e6b-bc7f-c379a30c2c2c_301fb535-938a-4103-a2f5-f3f9af9ba9c6.jpg','挺好的'),('a7184417-5aa2-4de0-8237-a4c0f53972a1','无为手机8plus',5000,'电子数码',10,'/WEB-INF/upload/3/6/c/0/7/2/1/3/741c8c70-cdd1-43a9-8cde-aa6a787129ca_738f47e2-9605-46aa-b647-fc8dca814074.jpg','还可以。。。。老司机推荐使用'),('b1f9c947-4f72-4245-b09d-8c5a8c311ae1','地中海7日游',5000,'日用百货',199,'/WEB-INF/upload/6/5/5/c/5/4/1/9/d437c381-59af-49ee-80c6-2b01e0b06105_1017530.jpg','放松身心，寻找艳遇'),('bf45940e-ac72-454f-b67f-83dd288d11f9','反恐精英',10000,'服装服饰',99,'/WEB-INF/upload/1/4/2/e/7/3/6/4/09af74da-3829-45c5-9517-380d2cc74f6a_preview.jpg','专业反恐部队'),('c0e7b4f3-e1ad-47d6-8c0d-f1c58b820ca8','大金表',1000,'电子数码',100,'/WEB-INF/upload/c/4/b/7/b/5/8/f/5adda796-66af-4c6e-a9e5-49a52a3c44a5_371cee6d-d81b-42b7-a11f-3ad36dc0e537.jpg','好用'),('c2952779-e9e0-4eda-8e0a-41a61f1afc66','苍#null',998,'床上用品',50,'/WEB-INF/upload/e/d/b/1/f/0/6/7/bcff4ee1-cc7f-4b30-a29c-017f76a21bf8_1.jpg','苍老师同款'),('c2978733-5af8-473b-adbc-05073126164b','宠物猫',30000,'日用百货',1,'/WEB-INF/upload/3/5/5/4/c/3/a/b/943de853-0e1b-4d51-9524-991607024d3b_IMG_0928.JPG','只有一只哦，快来抢购吧'),('c766ec19-4645-4e6b-9ddf-73a0f4aa5f6c','愤怒的大胡子',999,'日用百货',1,'/WEB-INF/upload/f/e/f/8/2/e/3/c/82c1698f-38a2-4340-9df7-83fadaefff4b_howardmouth.jpg','限量款,全球唯一'),('d73ab7ed-9f78-4775-a93b-4d355b2d5fc0','死亡海',4444,'日用百货',42,'/WEB-INF/upload/c/8/1/0/4/6/3/b/28139e28-7390-45a7-82c8-03e673486e60_Desert.jpg','体验死亡的感觉，你值得拥有'),('d7f7cce4-b268-41a7-9429-21fa69b64159','好好的',20000,'家用电器',1000,'/WEB-INF/upload/1/8/e/9/b/2/7/2/95e01470-8e6f-40dc-a76b-087d804bb0cf_bae0a60a-521d-48ef-bea6-0854b89d7be0.jpg','好用啊'),('d8cb845e-37f6-4515-9fc1-dea07719ee06','糖炒栗子',0,'日用百货',0,'/WEB-INF/upload/6/b/8/3/a/4/9/8/3fb77001-cd6c-4e4f-94a1-21a9c0563778_Hydrangeas.jpg','好吃的栗子'),('ff838641-feb5-42a1-b061-042b9113a95c','烈性炸药',5899,'电子数码',88,'/WEB-INF/upload/c/7/4/1/4/2/3/2/a99e691b-88d4-43a2-ac12-82ec54db123d_738f47e2-9605-46aa-b647-fc8dca814074.jpg','充电会自动引爆');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `email` varchar(60) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`nickname`,`email`) values (1,'苏三','456','',''),(2,'李四','123','',''),(3,'钱五','123','',''),(4,'赵六','123','',''),(10,'zhangsan','ss','zhangsan','a@163.com'),(11,'xiaoer','aaa','zhangsan','a@163.com');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
