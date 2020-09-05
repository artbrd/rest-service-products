--Создание таблицы
create table t_product_rules(
     product_id     bigint
    ,rule_id        bigint
    ,FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
    ,FOREIGN KEY (rule_id) REFERENCES rules_rpoduct(id) ON DELETE CASCADE
);
--Вставка в таблицу
INSERT INTO t_product_rules (product_id, rule_id)
    VALUES (7, 1);
INSERT INTO t_product_rules (product_id, rule_id)
    VALUES (8, 2);
INSERT INTO t_product_rules (product_id, rule_id)
    VALUES (9, 3);