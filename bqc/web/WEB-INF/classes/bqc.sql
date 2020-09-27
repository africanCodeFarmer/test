CREATE TABLE product_category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

insert into product_category values (null,'c1'),(null,'c2'),(null,'c3');

CREATE TABLE product_category_property (
  id int(11) NOT NULL AUTO_INCREMENT,
  pcid int(11) NOT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_pcp_product_category FOREIGN KEY (pcid) REFERENCES product_category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into product_category_property values (null,1,'c1'),(null,1,'c2'),(null,1,'c3');

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  type tinyint(1) DEFAULT 0 COMMENT '0普通 1商家 2管理员', 
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE product (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  subTitle varchar(255) DEFAULT NULL,
  orignalPrice float DEFAULT 0,
  promotePrice float DEFAULT 0,
  stock int(11) DEFAULT 0,
  pcid int(11) NOT NULL,
  createTime datetime DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_p_product_category FOREIGN KEY (pcid) REFERENCES product_category (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

insert into product values 
	(null,'p1','1',2,3,4,1,'2020-09-27'),(null,'p2','2',3,4,5,2,'2020-09-28')
