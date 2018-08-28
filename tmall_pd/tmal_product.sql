/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/6/17/周日 20:52:28                        */
/*==============================================================*/


drop table if exists tmall_brand;

drop table if exists tmall_category;

drop table if exists tmall_category_property;

drop table if exists tmall_picture;

drop table if exists "tmall_product(spu)";

drop table if exists tmall_product_picture;

drop table if exists tmall_product_property2;

drop table if exists tmall_property;

drop table if exists tmall_property_value;

drop table if exists tmall_sku;

/*==============================================================*/
/* Table: tmall_brand                                           */
/*==============================================================*/
create table tmall_brand
(
   brand_id             int not null,
   picture_id           varchar(64),
   brand_name           varchar(16),
   brand_desc           varchar(128,
   primary key (brand_id)
);

/*==============================================================*/
/* Table: tmall_category                                        */
/*==============================================================*/
create table tmall_category
(
   category_id          int not null,
   category_name        varchar(32),
   category_display_seq int,
   category_pid         int,
   status               tinyint,
   create_date          timestamp,
   update_date          timestamp,
   primary key (category_id)
);

/*==============================================================*/
/* Table: tmall_category_property                               */
/*==============================================================*/
create table tmall_category_property
(
   category_id          int,
   property_id          int
);

/*==============================================================*/
/* Table: tmall_picture                                         */
/*==============================================================*/
create table tmall_picture
(
   picture_id           varchar(64) not null,
   picture_url          varchar(128),
   primary key (picture_id)
);

/*==============================================================*/
/* Table: "tmall_product(spu)"                                  */
/*==============================================================*/
create table "tmall_product(spu)"
(
   product_id           bigint not null,
   category_id          int,
   brand_id             int,
   picture_id           varchar(64),
   product_name         varchar(32),
   product_status       tinyint,
   product_desc         varchar(255),
   product_total_selled int,
   product_is_hot       tinyint,
   primary key (product_id)
);

/*==============================================================*/
/* Table: tmall_product_picture                                 */
/*==============================================================*/
create table tmall_product_picture
(
   product_id           bigint,
   picture_id           varchar(64),
   picture_type         tinyint comment '图片类型,是商品详情图,还是下方大图 '
);

alter table tmall_product_picture comment '商品-图片关联表,非前台显示的表,是商品详情等图片';

/*==============================================================*/
/* Table: tmall_product_property2                               */
/*==============================================================*/
create table tmall_product_property2
(
   property_id          int,
   sku_id               bigint
);

/*==============================================================*/
/* Table: tmall_property                                        */
/*==============================================================*/
create table tmall_property
(
   property_id          int not null,
   property_name        varchar(32),
   primary key (property_id)
);

/*==============================================================*/
/* Table: tmall_property_value                                  */
/*==============================================================*/
create table tmall_property_value
(
   property_value_id    bigint not null,
   property_id          int,
   property_value       varchar(32),
   primary key (property_value_id)
);

/*==============================================================*/
/* Table: tmall_sku                                             */
/*==============================================================*/
create table tmall_sku
(
   sku_id               bigint not null,
   product_id           bigint,
   picture_id           varchar(64),
   product_price        decimal(6,2),
   product_stock        int,
   product_promote_price decimal(6,2),
   product_status       tinyint,
   primary key (sku_id)
);

alter table tmall_brand add constraint FK_Reference_4 foreign key (picture_id)
      references tmall_picture (picture_id) on delete restrict on update restrict;

alter table tmall_category_property add constraint FK_Reference_6 foreign key (category_id)
      references tmall_category (category_id) on delete set null on update cascade;

alter table tmall_category_property add constraint FK_Reference_7 foreign key (property_id)
      references tmall_property (property_id) on delete set null on update cascade;

alter table "tmall_product(spu)" add constraint FK_Reference_1 foreign key (category_id)
      references tmall_category (category_id) on delete set null on update cascade;

alter table "tmall_product(spu)" add constraint FK_Reference_2 foreign key (brand_id)
      references tmall_brand (brand_id) on delete restrict on update restrict;

alter table "tmall_product(spu)" add constraint FK_Reference_3 foreign key (picture_id)
      references tmall_picture (picture_id) on delete set null on update cascade;

alter table tmall_product_picture add constraint FK_Reference_10 foreign key (product_id)
      references "tmall_product(spu)" (product_id) on delete restrict on update restrict;

alter table tmall_product_picture add constraint FK_Reference_11 foreign key (picture_id)
      references tmall_picture (picture_id) on delete restrict on update restrict;

alter table tmall_product_property2 add constraint FK_Reference_12 foreign key (property_id)
      references tmall_property (property_id) on delete restrict on update restrict;

alter table tmall_product_property2 add constraint FK_Reference_15 foreign key (sku_id)
      references tmall_sku (sku_id) on delete restrict on update restrict;

alter table tmall_property_value add constraint FK_Reference_5 foreign key (property_id)
      references tmall_property (property_id) on delete set null on update cascade;

alter table tmall_sku add constraint FK_Reference_13 foreign key (product_id)
      references "tmall_product(spu)" (product_id) on delete cascade on update cascade;

alter table tmall_sku add constraint FK_Reference_14 foreign key (picture_id)
      references tmall_picture (picture_id) on delete restrict on update restrict;

