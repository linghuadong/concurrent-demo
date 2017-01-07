CREATE DATABASE IF NOT EXISTS concurrent DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE concurrent;

CREATE TABLE user(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` VARCHAR(120) NOT NULL COMMENT '用户名称',
  `password` VARCHAR(120) NOT NULL COMMENT '用户密码',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  `last_date` DATETIME NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (id),
  UNIQUE (username)
) ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET = utf8 COMMENT '用户表';

INSERT INTO user(username, password, create_date, last_date) VALUE ('test','test',now(),now());

CREATE TABLE product(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `intro` VARCHAR(2000) NOT NULL COMMENT '商品介绍',
  `picture` VARCHAR(2000) COMMENT '商品图片',
  `description` LONGTEXT COMMENT '商品描述',
  `price` DECIMAL NOT NULL COMMENT '商品价格',
  `sold_quantity` INT NOT NULL COMMENT '销售数量',
  `stock_quantity` INT NOT NULL COMMENT '剩余库存数量',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (id)
)ENGINE = InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET = utf8 COMMENT '商品表';

INSERT INTO product(name, intro, picture, description, price, sold_quantity, stock_quantity, create_date) VALUE ('键盘' ,'很好的键盘',NULL ,NULL ,10,0,500,now());

DROP TABLE IF EXISTS orders;

CREATE TABLE orders(
  `number` BIGINT NOT NULL COMMENT '单号',
  `user_id` BIGINT NOT NULL COMMENT '用户ID',
  `state` VARCHAR(15) NOT NULL COMMENT '订单状态',
  `total_price` DECIMAL NOT NULL COMMENT '订单的总价格',
  `create_date` DATETIME NOT NULL COMMENT '创建时间',
  PRIMARY KEY (number),
  FOREIGN KEY (user_id) REFERENCES user(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '订单表';

DROP TABLE IF EXISTS order_line;

CREATE TABLE order_line(
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `order_number` BIGINT NOT NULL COMMENT '订单ID',
  `product_id` BIGINT NOT NULL COMMENT '商品ID',
  `product_name` VARCHAR(120) NOT NULL COMMENT '商品名称',
  `product_picture` VARCHAR(2000) COMMENT '商品图片',
  `product_price` DECIMAL NOT NULL COMMENT '商品价格',
  `num` INT NOT NULL COMMENT '购买数量',
  PRIMARY KEY (id),
  FOREIGN KEY (order_number) REFERENCES orders(number),
  FOREIGN KEY (product_id) REFERENCES product(id)
) ENGINE = InnoDB DEFAULT CHARSET = utf8 COMMENT '订单项表';