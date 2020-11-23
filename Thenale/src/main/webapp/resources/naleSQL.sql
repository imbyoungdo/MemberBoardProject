--사용자 만들기
CREATE USER ADEPTER IDENTIFIED BY "1234";
GRANT DBA TO ADEPTER;

--테이블
create table tb_member (
    m_num number(10) CONSTRAINT pk_mnum PRIMARY KEY,
    m_id nvarchar2(20),
    m_k_id nvarchar2(20),
    m_n_id nvarchar2(20),
    m_g_id nvarchar2(20),
    m_password nvarchar2(100),
    m_name nvarchar2(50),
    m_email nvarchar2(50),
    m_phone nvarchar2(20),
    m_profile nvarchar2(1000),
    m_auth nvarchar2(20),
    m_permit number(5)
    );
    
drop sequence member_seq;
create sequence member_seq
    start with 1
    increment by 1
    nocache;
commit;

-- 업체 등록
drop table tb_company;
create table tb_company (
    c_id nvarchar2(50) CONSTRAINT pk_c_id PRIMARY KEY,
    c_password nvarchar2(100) not null,
    c_name nvarchar2(100) not null,
    c_photo nvarchar2(1000),
    c_contents nvarchar2(200),
    c_address nvarchar2(100),
    c_y nvarchar2(50),
    c_x nvarchar2(50),
    c_sort nvarchar2(50),
    c_ceo_tel nvarchar2(20),
    c_ceo nvarchar2(20),
    c_area nvarchar2(100),
    c_auth nvarchar2(20)
    );

-- 식당 게시판
drop table tb_food;
create table tb_food (
    f_num number(10) CONSTRAINT pk_f_num PRIMARY KEY,
    f_c_id nvarchar2(20) CONSTRAINT PK_f_c_id REFERENCES tb_company(c_id) on delete cascade,
    f_title nvarchar2(50),
    f_detail nvarchar2(50),
    f_mainimg nvarchar2(50),
    f_x nvarchar2(100),
    f_y nvarchar2(100));

CREATE SEQUENCE food_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE;

select * from tb_food;

-- 식당 메뉴 게시판
drop table tb_food_menu;
create table tb_food_menu (
    f_m_c_id nvarchar2(20) CONSTRAINT PK_f_m_c_id REFERENCES tb_company(c_id) on delete cascade,
    f_m_price number(10),
    f_m_foodimg nvarchar2(1000),
    f_m_subimg nvarchar2(1000));
    
--장소테이블
CREATE TABLE tb_place(
    p_name NVARCHAR2(50),
    p_a_name NVARCHAR2(50),
    p_type_num number(5),
    p_with_num number(6),
    p_photo NVARCHAR2(1000),
    p_x nvarchar2(100),
    p_y NVARCHAR2(100),
    p_contents nvarchar2(1000)
);

--호텔 테이블 
    create table tb_hotel(
    h_num number(10) CONSTRAINT  pk_h__num primary key,
    h_c_id NVARCHAR2(50) CONSTRAINT PK_h_c_id REFERENCES tb_company(c_id) on delete cascade,
    h_name NVARCHAR2(50) not null,
    h_photo nvarchar2(1000),
    h_contents nvarchar2(200),
    h_x nvarchar2 (100),
    h_y nvarchar2 (100),
    h_area nvarchar2(100),
    h_address nvarchar2(500)
    );
