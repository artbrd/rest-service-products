--Создание последовательности
CREATE SEQUENCE product_id_seq;

--Создание таблицы
create table product(
     id             bigint PRIMARY KEY DEFAULT nextval('product_id_seq'::regclass)
    ,name           varchar(500)
    ,start_sum_cred numeric(17,2)
    ,end_sum_cred   numeric(17,2)
    ,percent        numeric(5,2)
    ,term           integer
    ,date_create    date
    ,date_update    date
    ,is_active      boolean
);

--Вставка в таблицу
INSERT INTO product (name, start_sum_cred, end_sum_cred, percent, term, date_create, date_update, is_active)
    VALUES ('product_1', 0, 200000, 6, 36, '2020-09-02', '2020-09-02', true);
INSERT INTO product (name, start_sum_cred, end_sum_cred, percent, term, date_create, date_update, is_active)
    VALUES ('product_2', 0, null, 15, 0, '2020-09-02', '2020-09-02', true);
INSERT INTO product (name, start_sum_cred, end_sum_cred, percent, term, date_create, date_update, is_active)
    VALUES ('product_3', 0, 1000000, 12, 60, '2020-09-02', '2020-09-02', true);