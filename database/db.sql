/*
SQLyog Ultimate v11.3 (64 bit)
MySQL - 5.7.32-log : Database - xinlijiankangxitong
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`xinlijiankangxitong` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `xinlijiankangxitong`;

/*Table structure for table `config` */

DROP TABLE IF EXISTS `config`;

CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='配置文件';

/*Data for the table `config` */

insert  into `config`(`id`,`name`,`value`) values (1,'轮播图1','http://localhost:8080/xinlijiankangxitong/upload/config1.jpg'),(2,'轮播图2','http://localhost:8080/xinlijiankangxitong/upload/config2.jpg'),(3,'轮播图3','http://localhost:8080/xinlijiankangxitong/upload/config3.jpg');

/*Table structure for table `dictionary` */

DROP TABLE IF EXISTS `dictionary`;

CREATE TABLE `dictionary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dic_code` varchar(200) DEFAULT NULL COMMENT '字段',
  `dic_name` varchar(200) DEFAULT NULL COMMENT '字段名',
  `code_index` int(11) DEFAULT NULL COMMENT '编码',
  `index_name` varchar(200) DEFAULT NULL COMMENT '编码名字  Search111 ',
  `super_id` int(11) DEFAULT NULL COMMENT '父字段id',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COMMENT='字典';

/*Data for the table `dictionary` */

insert  into `dictionary`(`id`,`dic_code`,`dic_name`,`code_index`,`index_name`,`super_id`,`remark`,`create_time`) values (1,'sex_types','性别类型',1,'男',NULL,NULL,'2025-10-28'),(2,'sex_types','性别类型',2,'女',NULL,NULL,'2025-10-28'),(3,'Teacher_collection_types','收藏表类型',1,'收藏',NULL,NULL,'2025-10-28'),(4,'Teacher_order_yesno_types','预约状态',1,'待审核',NULL,NULL,'2025-10-28'),(5,'Teacher_order_yesno_types','预约状态',2,'同意',NULL,NULL,'2025-10-28'),(6,'Teacher_order_yesno_types','预约状态',3,'拒绝',NULL,NULL,'2025-10-28'),(7,'Time_types','时间段',1,'08:00-09:00',NULL,NULL,'2025-10-28'),(8,'Time_types','时间段',2,'09:00-10:00',NULL,NULL,'2025-10-28'),(9,'Time_types','时间段',3,'10:00-11:00',NULL,NULL,'2025-10-28'),(10,'Time_types','时间段',4,'11:00-12:00',NULL,NULL,'2025-10-28'),(11,'Time_types','时间段',5,'14:00-15:00',NULL,NULL,'2025-10-28'),(12,'Time_types','时间段',6,'15:00-16:00',NULL,NULL,'2025-10-28'),(13,'Time_types','时间段',7,'16:00-17:00',NULL,NULL,'2025-10-28'),(14,'Time_types','时间段',8,'17:00-18:00',NULL,NULL,'2025-10-28'),(15,'Info_types','通知类型',1,'通知类型1',NULL,NULL,'2025-10-28'),(16,'Info_types','通知类型',2,'通知类型2',NULL,NULL,'2025-10-28'),(17,'HealthyKnowledge_types','健康知识类型',1,'健康知识类型1',NULL,NULL,'2025-10-28'),(18,'HealthyKnowledge_types','健康知识类型',2,'健康知识类型2',NULL,NULL,'2025-10-28');

/*Table structure for table `HealthyKnowledge` */

DROP TABLE IF EXISTS `HealthyKnowledge`;

CREATE TABLE `HealthyKnowledge` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `HealthyKnowledge_name` varchar(200) DEFAULT NULL COMMENT '健康知识 Search111  ',
  `HealthyKnowledge_photo` varchar(200) DEFAULT NULL COMMENT '健康知识图片 ',
  `HealthyKnowledge_types` int(11) NOT NULL COMMENT '健康知识类型 ',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '健康知识发布时间 ',
  `HealthyKnowledge_content` text COMMENT '知识详情 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show1 show2 nameShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='健康知识';

/*Data for the table `HealthyKnowledge` */

insert  into `HealthyKnowledge`(`id`,`HealthyKnowledge_name`,`HealthyKnowledge_photo`,`HealthyKnowledge_types`,`insert_time`,`HealthyKnowledge_content`,`create_time`) values (1,'健康知识1','http://localhost:8080/xinlijiankangxitong/upload/Student1.jpg',1,'2025-10-28','知识详情1','2025-10-28'),(2,'健康知识2','http://localhost:8080/xinlijiankangxitong/upload/Student2.jpg',2,'2025-10-28','知识详情2','2025-10-28'),(3,'健康知识3','http://localhost:8080/xinlijiankangxitong/upload/Student3.jpg',1,'2025-10-28','知识详情3','2025-10-28'),(4,'健康知识4','http://localhost:8080/xinlijiankangxitong/upload/Student1.jpg',1,'2025-10-28','知识详情4','2025-10-28'),(5,'健康知识5','http://localhost:8080/xinlijiankangxitong/upload/Student2.jpg',1,'2025-10-28','知识详情5','2025-10-28');

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='token表';

/*Data for the table `token` */

insert  into `token`(`id`,`userid`,`username`,`tablename`,`role`,`token`,`addtime`,`expiratedtime`) values (1,6,'admin','Admin','管理员','qsh241u4wenz9q880lzg956l6gdvnfy5','2024-03-29 11:49:29','2024-03-29 14:10:47'),(2,1,'a1','Student','学生','1o29ta3k4dzq9qthlcltpazu9kmmu6ie','2024-03-29 11:50:44','2024-03-29 14:08:30'),(3,1,'a1','Teacher','心理老师','nu8v2a1pz7u1qg0es7c4yrjs6vd9r4j3','2024-03-29 13:07:56','2024-03-29 14:08:52');

/*Table structure for table `Info` */

DROP TABLE IF EXISTS `Info`;

CREATE TABLE `Info` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键 ',
  `Info_name` varchar(200) DEFAULT NULL COMMENT '通知 Search111  ',
  `Info_photo` varchar(200) DEFAULT NULL COMMENT '通知图片 ',
  `Info_types` int(11) NOT NULL COMMENT '通知类型 ',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '通知发布时间 ',
  `Info_content` text COMMENT '详情 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show1 show2 nameShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='通知';

/*Data for the table `Info` */

insert  into `Info`(`id`,`Info_name`,`Info_photo`,`Info_types`,`insert_time`,`Info_content`,`create_time`) values (1,'通知1','http://localhost:8080/xinlijiankangxitong/upload/Student1.jpg',2,'2025-10-28','详情1','2025-10-28'),(2,'通知2','http://localhost:8080/xinlijiankangxitong/upload/Student2.jpg',1,'2025-10-28','详情2','2025-10-28'),(3,'通知3','http://localhost:8080/xinlijiankangxitong/upload/Student3.jpg',1,'2025-10-28','详情3','2025-10-28'),(4,'通知4','http://localhost:8080/xinlijiankangxitong/upload/Student1.jpg',2,'2025-10-28','详情4','2025-10-28'),(5,'通知5','http://localhost:8080/xinlijiankangxitong/upload/Student2.jpg',1,'2025-10-28','详情5','2025-10-28');

/*Table structure for table `Admin` */

DROP TABLE IF EXISTS `Admin`;

CREATE TABLE `Admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='管理员';

/*Data for the table `Admin` */

insert  into `Admin`(`id`,`username`,`password`,`role`,`addtime`) values (6,'admin','admin','管理员','2025-10-28');

/*Table structure for table `Teacher` */

DROP TABLE IF EXISTS `Teacher`;

CREATE TABLE `Teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) DEFAULT NULL COMMENT '账户',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `Teacher_name` varchar(200) DEFAULT NULL COMMENT '心理老师 Search111 ',
  `Teacher_phone` varchar(200) DEFAULT NULL COMMENT '心理老师手机号',
  `Teacher_photo` varchar(200) DEFAULT NULL COMMENT '心理老师头像',
  `sex_types` int(11) DEFAULT NULL COMMENT '性别',
  `Teacher_email` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `Teacher_content` text COMMENT '详细介绍 ',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间  show1 show2 photoShow',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='心理老师';

/*Data for the table `Teacher` */

insert  into `Teacher`(`id`,`username`,`password`,`Teacher_name`,`Teacher_phone`,`Teacher_photo`,`sex_types`,`Teacher_email`,`Teacher_content`,`create_time`) values (1,'a1','123456','心理老师1','17703786901','http://localhost:8080/xinlijiankangxitong/upload/Teacher1.jpg',2,'1@qq.com','详细介绍1','2025-10-28'),(2,'a2','123456','心理老师2','17703786902','http://localhost:8080/xinlijiankangxitong/upload/Teacher2.jpg',2,'2@qq.com','详细介绍2','2025-10-28'),(3,'a3','123456','心理老师3','17703786903','http://localhost:8080/xinlijiankangxitong/upload/Teacher3.jpg',1,'3@qq.com','详细介绍3','2025-10-28');

/*Table structure for table `Teacher_collection` */

DROP TABLE IF EXISTS `Teacher_collection`;

/*Table structure for table `Teacher_Mes` */

DROP TABLE IF EXISTS `Teacher_Mes`;

CREATE TABLE `Teacher_Mes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Teacher_id` int(11) DEFAULT NULL COMMENT '心理老师',
  `Student_id` int(11) DEFAULT NULL COMMENT '用户',
  `Teacher_Mes_text` text COMMENT '留言',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '留言时间',
  `reply_text` text COMMENT '回复内容',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '回复时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='心理老师留言';

/*Data for the table `Teacher_Mes` */

insert  into `Teacher_Mes`(`id`,`Teacher_id`,`Student_id`,`Teacher_Mes_text`,`insert_time`,`reply_text`,`update_time`,`create_time`) values (1,2,2,'留言1','2025-10-28','回复信息1','2025-10-28','2025-10-28'),(2,1,1,'留言2','2025-10-28','回复信息2','2025-10-28','2025-10-28'),(3,1,3,'留言3','2025-10-28','回复信息3','2025-10-28','2025-10-28'),(4,2,2,'留言4','2025-10-28','回复信息4','2025-10-28','2025-10-28'),(5,3,3,'留言5','2025-10-28','回复信息5','2025-10-28','2025-10-28'),(6,1,1,'333333','2024-03-29 13:04:37','22222222222','2024-03-29 13:08:11','2024-03-29 13:04:37');

/*Table structure for table `Teacher_order` */

DROP TABLE IF EXISTS `Teacher_order`;

CREATE TABLE `Teacher_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `Teacher_order_uuid_number` varchar(200) DEFAULT NULL COMMENT '预约流水号 Search111 ',
  `Teacher_id` int(11) DEFAULT NULL COMMENT '心理老师',
  `Student_id` int(11) DEFAULT NULL COMMENT '用户',
  `yuyue_time` date DEFAULT NULL COMMENT '预约日期',
  `Time_types` int(11) DEFAULT NULL COMMENT '预约时间段 Search111 ',
  `Teacher_order_yesno_types` int(11) DEFAULT NULL COMMENT '预约状态 Search111 ',
  `Teacher_order_yesno_text` text COMMENT '审核意见',
  `insert_time` timestamp NULL DEFAULT NULL COMMENT '申请时间',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间 show3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='心理老师预约订单';

/*Data for the table `Teacher_order` */

insert  into `Teacher_order`(`id`,`Teacher_order_uuid_number`,`Teacher_id`,`Student_id`,`yuyue_time`,`Time_types`,`Teacher_order_yesno_types`,`Teacher_order_yesno_text`,`insert_time`,`create_time`) values (1,'1648525919361',2,1,'2024-03-30',8,1,NULL,'2024-03-29 11:51:59','2024-03-29 11:51:59'),(2,'1648530223381',3,1,'2024-03-30',8,1,NULL,'2024-03-29 13:03:43','2024-03-29 13:03:43'),(3,'1648530268888',1,1,'2024-03-31',3,2,'key','2024-03-29 13:04:29','2024-03-29 13:04:29');

/*Table structure for table `Student` */

DROP TABLE IF EXISTS `Student`;

CREATE TABLE `Student` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(200) DEFAULT NULL COMMENT '账户',
  `password` varchar(200) DEFAULT NULL COMMENT '密码',
  `Student_name` varchar(200) DEFAULT NULL COMMENT '学生姓名 Search111 ',
  `Student_phone` varchar(200) DEFAULT NULL COMMENT '学生手机号',
  `Student_id_number` varchar(200) DEFAULT NULL COMMENT '学生身份证号',
  `Student_photo` varchar(200) DEFAULT NULL COMMENT '学生头像',
  `sex_types` int(11) DEFAULT NULL COMMENT '性别',
  `Student_email` varchar(200) DEFAULT NULL COMMENT '电子邮箱',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='学生';

/*Data for the table `Student` */

insert  into `Student`(`id`,`username`,`password`,`Student_name`,`Student_phone`,`Student_id_number`,`Student_photo`,`sex_types`,`Student_email`,`create_time`) values (1,'a1','123456','学生1','17703786901','410224199610232001','http://localhost:8080/xinlijiankangxitong/upload/Student1.jpg',2,'1@qq.com','2025-10-28'),(2,'a2','123456','学生2','17703786902','410224199610232002','http://localhost:8080/xinlijiankangxitong/upload/Student2.jpg',2,'2@qq.com','2025-10-28'),(3,'a3','123456','学生3','17703786903','410224199610232003','http://localhost:8080/xinlijiankangxitong/upload/Student3.jpg',2,'3@qq.com','2025-10-28');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
