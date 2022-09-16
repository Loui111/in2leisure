-- flyway 쓰다가 말았는데 향후 살려야 할수도 있음.
--
--CREATE TABLE if not exists member (
--   member_id    bigint auto_increment primary key,
--   email        varchar(255),
--   password     VARCHAR(30),
--   member_name   VARCHAR(255),
--   phone_number   varchar(255),
--   gender        varchar(255),
--   birthday      DATE,
--   address       varchar(255),
--   profile_image  varchar(255)
--);
--
--create table if not exists orders
--(
--    orders_id     bigint auto_increment primary key,
--    member_id     INT,
--    member_name    varchar(255),
--    shop_id       varchar(255),
--    shop_name      varchar(255),
--    origin_price   INT,
--    discount_price INT,
--    discount_rate  FLOAT,
--    currency      varchar(255),
--    order_status   varchar(255)
--);
--
--create table IF not exists orders_product
--(
--    orders_product_id  bigint auto_increment primary key,
--    orders_id       bigint,
--    product_id      bigint
--);
--
--create table IF not exists product
--(
--    product_id      bigint auto_increment primary key,
--    shop_id         INT,
--    shop_name       varchar(255),
--    product_name    varchar(255),
--    product_desc    varchar(255),
--    amount          INT,
--    origin_price    INT,
--    discount_price  INT,
--    discount_rate   FLOAT,
--    currency        varchar(255),
--    sold_count      INT
--);
--
--create table IF not exists shop
--(
--    shop_id         bigint auto_increment primary key,
--    types           varchar(255),
--    sub_types       varchar(255),
--    shop_desc       varchar(255),
--    country         varchar(255),
--    front_image     varchar(255),
--    content_image   varchar(255),
--    review_score    FLOAT,
--    rankScore       FLOAT
--);

--
--create table member
--(
--    member_id     INTEGER NOT NULL AUTO_INCREMENT primary key,
--    email         varchar(255),
--    password      varchar(255),
--    memberName    varchar(255),
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
--    memberName    varchar(255),
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
--    memberName    varchar(255),
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
