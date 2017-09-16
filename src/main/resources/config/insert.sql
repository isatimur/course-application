# --- Sample dataset

# --- !Ups

INSERT INTO course_list_course_cats (id, name) VALUES (1, 'Все курсы');
INSERT INTO course_list_course_cats (id, name) VALUES (2, 'Интернет-торговля');
INSERT INTO course_list_course_cats (id, name) VALUES (3, 'Лидерство');
INSERT INTO course_list_course_cats (id, name) VALUES (4, 'Личное развитие');
INSERT INTO course_list_course_cats (id, name) VALUES (5, 'Маркетинг');
INSERT INTO course_list_course_cats (id, name) VALUES (6, 'Менеджмент');
INSERT INTO course_list_course_cats (id, name) VALUES (7, 'Презентации');
INSERT INTO course_list_course_cats (id, name) VALUES (8, 'Продажи');
INSERT INTO course_list_course_cats (id, name) VALUES (9, 'Развитие бизнеса');
INSERT INTO course_list_course_cats (id, name) VALUES (10, 'Старт бизнеса');
INSERT INTO course_list_course_cats (id, name) VALUES (11, 'Финансы');
-- insert into category (id,name) values (  1,'Пакеты услуг для бизнеса');
-- insert into category (id,name) values (  2,'Банковские продукты для бизнеса');
-- insert into category (id,name) values (  3,'Гос.Услуги для бизнеса');
-- insert into category (id,name) values (  4,'Программное обеспечение');
-- insert into category (id,name) values (  5,'Мобильная связь, пакеты');
-- insert into category (id,name) values (  6,'Корпоративное такси');
-- insert into category (id,name) values (  7,'Снабжение офисов');
-- insert into category (id,name) values (  8,'Гурзоперевозки');
-- insert into category (id,name) values (  9,'Облачная бухгалтерия');
-- insert into category (id,name) values (  10,'Курьерская служба');
-- insert into category (id,name) values (  11,'Аренда офисов');
-- insert into category (id,name) values (  12,'Лизинг');
-- insert into category (id,name) values (  13,'Кредитование');
-- insert into category (id,name) values (  14,'РКО');
-- insert into category (id,name) values (  15,'Страхование');
-- insert into category (id,name) values (  16,'Факторинг');
-- insert into category (id,name) values (  17,'Регистрация бизнеса');
--
--
-- insert into product (id,header,description,category_id,owner_id, is_published) values (  1,'MacBook Pro 15.4 inch','MacBook Pro 15.4 inch',4, 0);
-- insert into product (id,header,description,category_id,owner_id, is_published) values (  2,'NEW - MacBook Pro 15.4 inch','NEW - MacBook Pro 15.4 inch',7, 0);

# --- !Downs
DELETE FROM course_list_course_cats;
-- delete from product;
-- delete from category;
