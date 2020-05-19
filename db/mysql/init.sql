drop table if exists  goods;
create table if not exists goods
(
    id_ bigint auto_increment
    primary key,
    count_ int null,
    describe_ varchar(255) null,
    image_ longtext null,
    name_ varchar(255) null,
    type_ varchar(255) null,
    unit_price_ double null,
    seller_id_ bigint null
    )

;

drop table if exists  order_detail;
create table if not exists order_detail
(
    id_ bigint auto_increment
    primary key,
    count_ int null,
    total_ double null,
    buyer_id_ bigint null,
    goods_id_ bigint null,
    seller_id_ bigint null
)

;

drop table if exists  shopping_cart_detail;
create table if not exists shopping_cart_detail
(
    id_ bigint auto_increment
    primary key,
    count_ int null,
    buyer_id_ bigint null,
    goods_id_ bigint null,
    price_ double null
)

;

drop table if exists  sys_user;
create table if not exists sys_user
(
    id_ bigint auto_increment
    primary key,
    login_name_ varchar(255) null,
    name_ varchar(255) null,
    password_ varchar(255) null,
    phone_ varchar(255) null,
    create_date date null,
    member_ bit null
);

drop table if exists  sys_user_role;
create table if not exists sys_user_role
(
    id_ bigint auto_increment
    primary key,
    role_ varchar(255) null,
    sys_user_id_ bigint not null
    )

;

