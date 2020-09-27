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
  CONSTRAINT fk_this_product_category FOREIGN KEY (pcid) REFERENCES product_category (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into product_category_property values (null,1,'c1'),(null,1,'c2'),(null,1,'c3');