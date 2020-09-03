--Создание последовательности
CREATE SEQUENCE product_id_seq;

--Создание таблицы
create table products(
     id             bigint PRIMARY KEY DEFAULT nextval('product_id_seq'::regclass)
    ,name           varchar(500)
    ,sum_cred       integer
    ,percent        numeric(5,2)
    ,term           integer
    ,date_create    date
    ,date_update    date
    ,is_active      boolean
);

--Вставка в таблицу
INSERT INTO products (name, sum_cred, percent, term, date_create, date_update, is_active)
    VALUES ('product_1', 100000, 12.9, 12, '2020-09-02', '2020-09-02', true);
INSERT INTO products (name, sum_cred, percent, term, date_create, date_update, is_active)
    VALUES ('product_2', 200000, 11.9, 24, '2020-09-02', '2020-09-02', true);
INSERT INTO products (name, sum_cred, percent, term, date_create, date_update, is_active)
    VALUES ('product_3', 300000, 10.9, 36, '2020-09-02', '2020-09-02', true);