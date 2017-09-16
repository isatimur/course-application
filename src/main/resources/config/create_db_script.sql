# --- First database schema
CREATE SCHEMA IF NOT EXISTS courses DEFAULT CHARACTER SET utf8;
# --- !Ups

CREATE TABLE course_list_course_cats (
    id   BIGSERIAL NOT NULL
    ,
    name VARCHAR(500)
    ,
    CONSTRAINT pk_course_list_course_cats PRIMARY KEY (id)
);
CREATE TABLE course_list_course_statuses (
    id   BIGSERIAL NOT NULL
    ,
    name VARCHAR(500)
    ,
    CONSTRAINT pk_course_list_course_statuses PRIMARY KEY (id)
);
CREATE TABLE course_list_course_conttypes (
    id   BIGSERIAL NOT NULL
    ,
    name VARCHAR(500)
    ,
    CONSTRAINT pk_course_list_course_conttypes PRIMARY KEY (id)
);
CREATE TABLE course_data_courses (
  id           BIGSERIAL NOT NULL
  ,
  id_cat       BIGINT    NOT NULL
  ,
  name         VARCHAR(500)
  ,
  title        VARCHAR(255)
  ,
  description  VARCHAR(4000)
  --id_company(->general_data_companies)
  ,
  id_company   BIGINT
  -- is_published (1/0)
  ,
  is_published SMALLINT
  ,
  CONSTRAINT pk_course_data_courses PRIMARY KEY (id)
);
CREATE TABLE course_data_videos (
  id          BIGSERIAL NOT NULL
  ,
  name        VARCHAR(500)
  ,
  title       VARCHAR(500)
  ,
  description VARCHAR(4000)
  --, id_company(->general_data_companies)
  ,
  id_company  BIGINT
  ,
  CDN_ID      VARCHAR(255)
  ,
  CONSTRAINT pk_course_data_videos PRIMARY KEY (id)
);
CREATE TABLE course_data_presentations (
  id          BIGSERIAL NOT NULL
  ,
  name        VARCHAR(500)
  ,
  title       VARCHAR(500)
  ,
  description VARCHAR(4000)
  --  , id_company(->general_data_companies)
  ,
  id_company  BIGINT
  ,
  CDN_ID      VARCHAR(255)
  ,
  CONSTRAINT pk_course_data_presentations PRIMARY KEY (id)
);
CREATE TABLE course_ref_course_built (
  id           BIGSERIAL NOT NULL
  ,
  order_id     BIGINT
  -- , content_type(1-material, 2-presentation)
  ,
  content_type VARCHAR(12)
  -- , id_source(id и таблички course_data_courses либо course_data_videos, в зависимости от значения content_type)
  ,
  id_source    BIGINT
  -- , order (порядок элесента контента в курсе)
  ,
  "order"      INT
  ,
  CONSTRAINT pk_course_ref_course_built PRIMARY KEY (id)
);
CREATE TABLE course_ref_course_users (
  id           BIGSERIAL NOT NULL
  --   , id_user (-> general_data_users
  ,
  id_user      BIGINT
  --   , id_course (-> course_data_courses
  ,
  id_course    BIGINT
  --   , is_completed(1/0)
  ,
  is_completed SMALLINT
  ,
  CONSTRAINT pk_course_ref_course_users PRIMARY KEY (id)
);
CREATE TABLE course_ref_videos_users (
  id           BIGSERIAL NOT NULL
  --   , id_user (-> general_data_users
  ,
  id_user      BIGINT
  --   , id_video (-> course_data_videos
  ,
  id_video     BIGINT
  ,
  is_completed SMALLINT
  ,
  CONSTRAINT pk_course_ref_videos_users PRIMARY KEY (id)
);
CREATE TABLE course_ref_presentations_users (
  id           BIGSERIAL NOT NULL
  --   , id_user (->general_data_users
  ,
  id_user      BIGINT
  --   , id_present (->course_data_presentations
  ,
  id_present   SMALLINT
  --   , is_completed(1/0)
  ,
  is_completed SMALLINT
  ,
  CONSTRAINT pk_course_ref_presentations_users PRIMARY KEY (id)
);
CREATE SEQUENCE course_list_course_cats_id_seq START WITH 1000;
CREATE SEQUENCE course_data_courses_id_seq START WITH 1000;
CREATE SEQUENCE course_data_videos_id_seq START WITH 1000;
CREATE SEQUENCE course_data_presentations_id_seq START WITH 1000;
CREATE SEQUENCE course_ref_course_built_id_seq START WITH 1000;
CREATE SEQUENCE course_ref_course_users_id_seq START WITH 1000;
CREATE SEQUENCE course_ref_presentations_users_id_seq START WITH 1000;
CREATE SEQUENCE course_ref_videos_users_id_seq START WITH 1000;

# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

DROP TABLE IF EXISTS course_list_course_cats;
DROP TABLE IF EXISTS course_data_courses;
DROP TABLE IF EXISTS course_data_videos;
DROP TABLE IF EXISTS course_data_presentations;
DROP TABLE IF EXISTS course_ref_course_built;
DROP TABLE IF EXISTS course_ref_course_users;
DROP TABLE IF EXISTS course_ref_videos_users;
DROP TABLE IF EXISTS course_ref_presentations_users;

DROP SEQUENCE IF EXISTS course_list_course_cats_id_seq ;
DROP SEQUENCE IF EXISTS course_data_courses_id_seq ;
DROP SEQUENCE IF EXISTS course_data_videos_id_seq ;
DROP SEQUENCE IF EXISTS course_data_presentations_id_seq ;
DROP SEQUENCE IF EXISTS course_ref_course_built_id_seq ;
DROP SEQUENCE IF EXISTS course_ref_course_users_id_seq ;
DROP SEQUENCE IF EXISTS course_ref_presentations_users_id_seq ;
DROP SEQUENCE IF EXISTS course_ref_videos_users_id_seq;
-- DROP TABLE IF EXISTS category;
-- DROP TABLE IF EXISTS product;

SET REFERENTIAL_INTEGRITY TRUE;




DROP SCHEMA IF EXISTS courses;
