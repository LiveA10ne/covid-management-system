drop database if exists db;
create database db;
use db;


drop table if exists tb_user;
create table tb_user
(
    no       varchar(10) primary key unique not null comment '学号',
    name     varchar(10) null                 comment '姓名',
    password varchar(20) not null             comment '密码'
)comment '用户表';


drop table if exists tb_health;
create table tb_health
(
    id          int auto_increment primary key           comment 'ID' ,
    no          varchar(10)                         null comment '学号',
    name        varchar(10)                         null comment '姓名',
    college     varchar(15)                         null comment '学院',
    major       varchar(10)                         null comment '班级',
    create_time timestamp default CURRENT_TIMESTAMP null comment '生成时间',
    location    varchar(30)                         null comment '所在地',
    temperature float(3, 1)                         null comment '体温',
    cough       varchar(5)                          null comment '咳嗽',
    fever       varchar(5)                          null comment '发烧'
)comment '打卡记录';


insert into tb_user values ('1801160420','舒晓树','123456');

insert into tb_health values (null,'1801160420','舒晓树','计算机科学与工程学院','数字媒体技术2班',default,'泰塑6栋',36.5,'否','否'),
                             (null,'1801160421','康光华','艺术设计学院','产品设计1班',default,'泰塑7栋',37,'否','否'),
                             (null,'1901160422','黄光东','化工与制药学院','制药2班',default,'泰塑2栋',36.3,'否','否'),
                             (null,'1801160423','成艳萍','机电工程学院','电气自动化3班',default,'泰塑3栋',36.2,'否','否'),
                             (null,'2001160424','程昊宇','电气信息学院','光电信息1班',default,'泰塑5栋',36.8,'否','否'),
                             (null,'1801160425','张梁','管理学院','工程造价2班',default,'泰塑6栋',37.1,'否','否'),
                             (null,'2101160426','张嘉佳','法商学院','会计2班',default,'泰塑4栋',36.5,'否','否'),
                             (null,'1801160427','李春葆','计算机科学与工程学院','软件工程2班',default,'泰塑6栋',36.6,'否','否'),
                             (null,'1901160428','谢希仁','计算机科学与工程学院','计算机科学1班',default,'泰塑6栋',36.1,'否','否'),
                             (null,'1801160429','谭浩强','艺术设计学院','产品设计2班',default,'泰塑2栋',36.5,'否','否'),
                             (null,'1801160430','郑丹凤','计算机科学与工程学院','软件工程1班',default,'泰塑6栋',36.7,'否','否'),
                             (null,'1901160431','王涛','计算机科学与工程学院','物联网2班',default,'泰塑6栋',36.8,'否','否'),
                             (null,'2001160432','孙懿','机电工程学院','电气自动化2班',default,'泰塑4栋',36.4,'否','否'),
                             (null,'2001160433','张丽','机电工程学院','电气自动化2班',default,'泰塑4栋',36.6,'否','否'),
                             (null,'1801160434','郭伟','计算机科学与工程学院','数字媒体技术2班',default,'泰塑6栋',36.4,'否','否'),
                             (null,'1801160435','李志斌','艺术设计学院','产品设计3班',default,'泰塑7栋',36.5,'否','否');

