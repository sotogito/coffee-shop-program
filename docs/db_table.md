USE coffeeshopdb;

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
`user_number`     INT             NOT NULL 	AUTO_INCREMENT 	COMMENT '회원번호',
`user_id`         VARCHAR(50)     NOT NULL 	UNIQUE			COMMENT '아이디',
`user_password`   VARCHAR(50)     NOT NULL 					COMMENT '비밀번호',
`user_name`       VARCHAR(50)     NOT NULL 					COMMENT '이름',
`user_address`    VARCHAR(100)    NOT NULL 					COMMENT '주소',
`user_phone`      VARCHAR(50)     NULL 						COMMENT '전화번호',
`created_at`      DATETIME        NOT NULL 					COMMENT '생성일',
`modified_at`     DATETIME        NULL 						COMMENT '수정일',
PRIMARY KEY (`user_number`)
);

DROP TABLE IF EXISTS `tbl_user_type`;
CREATE TABLE `tbl_user_type` (
`user_number`  INT             NOT NULL 	COMMENT '회원번호',
`user_type`    VARCHAR(30)     NOT NULL 	COMMENT '사용자타입',
PRIMARY KEY (`user_number`)
);


DROP TABLE IF EXISTS `tbl_user_wallet`;
CREATE TABLE `tbl_user_wallet` (
`user_number`  INT             NOT NULL 	COMMENT '회원번호',
`balance`      INT             NOT NULL 	COMMENT '잔액',
`point`        INT             NOT NULL 	COMMENT '포인트',
`updated_at`   DATETIME        NOT NULL 	COMMENT '수정일',
PRIMARY KEY (`user_number`)
);


DROP TABLE IF EXISTS `tbl_store`;
CREATE TABLE `tbl_store` (
`store_id`     INT             NOT NULL 	AUTO_INCREMENT 	COMMENT '상점번호',
`store_name`   VARCHAR(50)     NOT NULL 					COMMENT '상점이름',
`phone`        VARCHAR(50)     NOT NULL 					COMMENT '전화번호',
`address`      VARCHAR(100)    NOT NULL 					COMMENT '주소',
`email`        VARCHAR(100)    NOT NULL 					COMMENT '이메일',
PRIMARY KEY (`store_id`)
);


DROP TABLE IF EXISTS `tbl_product`;
CREATE TABLE `tbl_product` (
`product_id`           INT             NOT NULL 	AUTO_INCREMENT 	COMMENT '상품번호',
`product_name`         VARCHAR(50)     NOT NULL 	UNIQUE			COMMENT '상품명',
`product_price`        INT             NOT NULL 					COMMENT '상품가격',
`category_id`          INT             NOT NULL 					COMMENT '카테고리번호',
`product_description`  VARCHAR(225)    NULL 						COMMENT '상품설명',
`stock`                INT             NOT NULL 					COMMENT '재고',
`sales_status`         ENUM('Y','N')   NOT NULL 					COMMENT '판매여부',
PRIMARY KEY (`product_id`)
);

DROP TABLE IF EXISTS `tbl_product_detail`;
CREATE TABLE `tbl_product_detail` (
`product_id`  INT         NOT NULL 	COMMENT '상품번호',
`created_at`  DATETIME    NOT NULL 	COMMENT '생성일',
`modified_at` DATETIME    NULL 		COMMENT '수정일',
PRIMARY KEY (`product_id`)
);


DROP TABLE IF EXISTS `tbl_category`;
CREATE TABLE `tbl_category` (
`category_id`   INT         NOT NULL 	AUTO_INCREMENT 	COMMENT '카테고리번호',
`category_name` VARCHAR(30) NOT NULL 	UNIQUE			COMMENT '카테고리명',
`created_at`    DATETIME    NOT NULL 					COMMENT '생성일',
`modified_at`   DATETIME    NULL 						COMMENT '수정일',
PRIMARY KEY (`category_id`)
);


DROP TABLE IF EXISTS `tbl_promotion_product`;
CREATE TABLE `tbl_promotion_product` (
`product_id`        INT             NOT NULL 	COMMENT '상품번호',
`promotion_id`      INT             NOT NULL 	COMMENT '할인번호',
`created_at`        DATETIME        NOT NULL 	COMMENT '등록일',
`promotion_status`  ENUM('Y','N')   NOT NULL 	COMMENT '할인여부',
PRIMARY KEY (`product_id`, `promotion_id`)
);

DROP TABLE IF EXISTS `tbl_promotion`;
CREATE TABLE `tbl_promotion` (
`promotion_id`      INT             NOT NULL 	AUTO_INCREMENT 	COMMENT '할인번호',
`promotion_name`    VARCHAR(30)     NOT NULL 					COMMENT '할인명',
`promotion_type`    VARCHAR(30)     NOT NULL 					COMMENT '할인타입',
`promotion_value`   INT             NULL 						COMMENT '할인값',
`start_date`        DATETIME        NULL 						COMMENT '시작일',
`end_date`          DATETIME        NULL 						COMMENT '종료일',
PRIMARY KEY (`promotion_id`)
);


DROP TABLE IF EXISTS `tbl_order`;
CREATE TABLE `tbl_order` (
`order_id`                INT         NOT NULL 	AUTO_INCREMENT 	COMMENT '주문번호',
`store_id`                INT         NOT NULL 					COMMENT '상점번호',
`user_number`             INT         NOT NULL 					COMMENT '회원번호',
`total_order_quantity`    INT         NOT NULL 					COMMENT '총구매수량',
`total_order_price`       INT         NOT NULL 					COMMENT '총구매가격',
`payment_method`          VARCHAR(30) NOT NULL 					COMMENT '결제방법',
`order_at`                DATETIME    NOT NULL 					COMMENT '주문일시',
`order_status`            VARCHAR(30) NOT NULL 					COMMENT '주문상태',
PRIMARY KEY (`order_id`)
);

DROP TABLE IF EXISTS `tbl_order_detail`;
CREATE TABLE `tbl_order_detail` (
`order_id`         INT             NOT NULL 	COMMENT '주문번호',
`product_id`       INT             NOT NULL 	COMMENT '상품번호',
`order_quantity`   INT             NOT NULL 	COMMENT '구매수량',
`order_price`      INT             NOT NULL 	COMMENT '구매가격',
`promotion_yn`     ENUM('Y','N')   NOT NULL 	COMMENT '할인적용여부',
PRIMARY KEY (`order_id`, `product_id`)
);


DROP TABLE IF EXISTS `tbl_cart`;
CREATE TABLE `tbl_cart` (
`cart_id`         INT         NOT NULL 	AUTO_INCREMENT 	COMMENT '장바구니번호',
`store_id`        INT         NOT NULL 					COMMENT '상점번호',
`user_number`     INT         NOT NULL 					COMMENT '회원번호',
`total_price`     INT         NOT NULL 					COMMENT '총가격',
`total_quantity`  INT         NOT NULL 					COMMENT '총수량',
`created_at`      DATETIME    NOT NULL 					COMMENT '생성일',
PRIMARY KEY (`cart_id`)
);

DROP TABLE IF EXISTS `tbl_cart_detail`;
CREATE TABLE `tbl_cart_detail` (
`cart_id`      INT         NOT NULL 	COMMENT '장바구니번호',
`product_id`   INT         NOT NULL 	COMMENT '상품번호',
`quantity`     INT         NOT NULL 	COMMENT '수량',
`price`        INT         NOT NULL 	COMMENT '가격',
PRIMARY KEY (`cart_id`, `product_id`)
);


ALTER TABLE `tbl_product_detail`
ADD CONSTRAINT `FK_tbl_product_TO_tbl_product_detail_1`
FOREIGN KEY (`product_id`)
REFERENCES `tbl_product` (`product_id`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_order`
ADD CONSTRAINT `FK_tbl_store_TO_tbl_order_1`
FOREIGN KEY (`store_id`)
REFERENCES `tbl_store` (`store_id`)
ON UPDATE CASCADE;


ALTER TABLE `tbl_order`
ADD CONSTRAINT `FK_tbl_user_TO_tbl_order_1`
FOREIGN KEY (`user_number`)
REFERENCES `tbl_user` (`user_number`)
ON UPDATE CASCADE;


ALTER TABLE `tbl_promotion_product`
ADD CONSTRAINT `FK_tbl_product_TO_tbl_promotion_product_1`
FOREIGN KEY (`product_id`)
REFERENCES `tbl_product` (`product_id`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_promotion_product`
ADD CONSTRAINT `FK_tbl_promotion_TO_tbl_promotion_product_1`
FOREIGN KEY (`promotion_id`)
REFERENCES `tbl_promotion` (`promotion_id`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_user_type`
ADD CONSTRAINT `FK_tbl_user_TO_tbl_user_type_1`
FOREIGN KEY (`user_number`)
REFERENCES `tbl_user` (`user_number`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_cart_detail`
ADD CONSTRAINT `FK_tbl_cart_TO_tbl_cart_detail_1`
FOREIGN KEY (`cart_id`)
REFERENCES `tbl_cart` (`cart_id`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_cart_detail`
ADD CONSTRAINT `FK_tbl_product_TO_tbl_cart_detail_1`
FOREIGN KEY (`product_id`)
REFERENCES `tbl_product` (`product_id`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_product`
ADD CONSTRAINT `FK_tbl_category_TO_tbl_product_1`
FOREIGN KEY (`category_id`)
REFERENCES `tbl_category` (`category_id`)
ON UPDATE CASCADE;


ALTER TABLE `tbl_user_wallet`
ADD CONSTRAINT `FK_tbl_user_TO_tbl_user_wallet_1`
FOREIGN KEY (`user_number`)
REFERENCES `tbl_user` (`user_number`)
ON UPDATE CASCADE;


ALTER TABLE `tbl_cart`
ADD CONSTRAINT `FK_tbl_store_TO_tbl_cart_1`
FOREIGN KEY (`store_id`)
REFERENCES `tbl_store` (`store_id`)
ON DELETE CASCADE
ON UPDATE CASCADE;


ALTER TABLE `tbl_cart`
ADD CONSTRAINT `FK_tbl_user_TO_tbl_cart_1`
FOREIGN KEY (`user_number`)
REFERENCES `tbl_user` (`user_number`)
ON UPDATE CASCADE;


ALTER TABLE `tbl_order_detail`
ADD CONSTRAINT `FK_tbl_order_TO_tbl_order_detail_1`
FOREIGN KEY (`order_id`)
REFERENCES `tbl_order` (`order_id`)
ON UPDATE CASCADE;


ALTER TABLE `tbl_order_detail`
ADD CONSTRAINT `FK_tbl_product_TO_tbl_order_detail_1`
FOREIGN KEY (`product_id`)
REFERENCES `tbl_product` (`product_id`)
ON UPDATE CASCADE;

COMMIT;
