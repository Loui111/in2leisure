-- INIT data insert
INSERT IGNORE INTO  member (
  member_id,
  address,
  email,
  member_name,
  password,
  phone_number,
  profile_image,
  gender
  )
VALUES (
  1,
  'address1111',
  'test@gmail.com',
  '고냥인',
  'Password123!@#',
  '0101231234',
  '/path/1',
  'FEMALE'
);

INSERT IGNORE INTO product(id ,shop_id, shop_name, product_name, product_desc, amount, origin_price, discount_price, currency, sold_count)
  VALUES (1, 11, 'in2Shop', 'Scubadiving11', '바다속친구들', 10, 10000, 8000, 'KRW', 10);

INSERT IGNORE INTO product(id ,shop_id, shop_name, product_name, product_desc, amount, origin_price, discount_price, currency, sold_count)
  VALUES (2, 11, 'in2Shop', 'Scubadiving22', '바다속친구들22', 100, 22000, 20000, 'KRW', 0);

INSERT IGNORE INTO product(id ,shop_id, shop_name, product_name, product_desc, amount, origin_price, discount_price, currency, sold_count)
  VALUES (3, 11, 'in2Shop', 'Scubadiving33', '바다속친구들33', 333, 3000, 200, 'USD', 0);

INSERT IGNORE INTO product(id ,shop_id, shop_name, product_name, product_desc, amount, origin_price, discount_price, currency, sold_count)
  VALUES (4, 43, 'in2Ski', 'Board', '스키친구들', 100, 22000, 20000, 'KRW', 0);

-- Shop 추가
INSERT IGNORE INTO shop(shop_id,content_image,country,front_image,rank_score,review_score,shop_desc,sub_types,types)
  VALUES (1, '/image1.png', 'KOR', '/front_image22.jpg', 4.5, 5, '고냥인Shopssss', 'WaterSports', 'WATER');



--INSERT IGNORE INTO product(product_id ,shop_id, shopName, productName, productDesc, amount, originPrice, discountPrice, currency, soldCount)
--   VALUES (2, 11, 'in2Shop', 'Scubadiving22', '바다속친구들22', 100, 22000, 20000, 'KRW', 0);
--INSERT IGNORE INTO product(product_id ,shop_id, shopName, productName, productDesc, amount, originPrice, discountPrice, currency, soldCount)
--   VALUES (3, 11, 'in2Shop', 'Scubadiving33', '바다속친구들33', 333, 3000, 200, 'USD', 0);
--INSERT IGNORE INTO product(product_id ,shop_id, shopName, productName, productDesc, amount, originPrice, discountPrice, currency, soldCount)
--   VALUES (4, 43, 'in2Ski', 'Board', '스키친구들', 100, 22000, 20000, 'KRW', 0);
----



--
--create table member
--(
--    member_id     INTEGER NOT NULL AUTO_INCREMENT primary key,
--    email         varchar(255),
--    password      varchar(255),
--    name    varchar(255),
--    phoneNumber   varchar(255),
--    gender        varchar(255),
--    birthDay      DATE,
--    address       varchar(255),
--    profileImage  varchar(255)
--);
--
--create table orders
--(
--    orders_id     INTEGER NOT NULL AUTO_INCREMENT primary key,
--    member_id     INTEGER,
--    name    varchar(255),
--    shop_id       varchar(255),
--    shopName      varchar(255),
--    originPrice   INTEGER,
--    discountPrice INTEGER,
--    discountRate  FLOAT,
--    currency      varchar(255),
--    orderStatus   varchar(255),
--);
--
--create table orders
--(
--    orders_id     INTEGER NOT NULL AUTO_INCREMENT primary key,
--    member_id     INTEGER,
--    name    varchar(255),
--    shop_id       varchar(255),
--    shopName      varchar(255),
--    originPrice   INTEGER,
--    discountPrice INTEGER,
--    discountRate  FLOAT,
--    currency      varchar(255),
--    orderStatus   varchar(255)
--);
--
--create table order_items
--(
--    orderItems_id INTEGER NOT NULL AUTO_INCREMENT primary key,
--    order_id      INTEGER,
--    product_id    INTEGER,
--    shop_id       varchar(255),
--    shopName      varchar(255),
--    originPrice   INTEGER,
--    discountPrice INTEGER,
--    discountRate  FLOAT,
--    currency      varchar(255),
--    orderStatus   varchar(255),
--);
--
--create table product
--(
--    product_id    INTEGER NOT NULL AUTO_INCREMENT primary key,
--    shop_id       INTEGER,
--    product_id    INTEGER,
--    shop_id       varchar(255),
--    shopName      varchar(255),
--    originPrice   INTEGER,
--    discountPrice INTEGER,
--    discountRate  FLOAT,
--    currency      varchar(255),
--    orderStatus   varchar(255),
--);
--
--
