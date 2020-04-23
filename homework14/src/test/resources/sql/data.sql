SET FOREIGN_KEY_CHECKS = 0;

TRUNCATE TABLE warehouse_movement;
TRUNCATE TABLE warehouses;
TRUNCATE TABLE products;

SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO products (name)
VALUES ('first'),
       ('second'),
       ('third'),
       ('fourth'),
       ('fifth'),
       ('sixth'),
       ('seventh'),
       ('eighth'),
       ('ninth'),
       ('tenth');

INSERT INTO warehouses (name)
VALUES ('ware_one'),
       ('ware_two');

INSERT INTO warehouse_movement (date, product_id, warehouse_id, quantity)
VALUES (CURDATE() - 3, 1, 1, 11),
       (CURDATE() - 3, 2, 1, 3),
       (CURDATE() - 2, 3, 1, 10),
       (CURDATE() - 2, 4, 1, 6),
       (CURDATE(), 3, 2, -4),
       (CURDATE(), 2, 2, -3),
       (CURDATE(), 5, 1, 8),
       (CURDATE(), 6, 1, 12),
       (CURDATE(), 7, 1, 5),
       (CURDATE(), 8, 1, -8),
       (CURDATE(), 5, 1, 36),
       (CURDATE() + 2, 9, 1, 4),
       (CURDATE() + 2, 5, 2, 4),
       (CURDATE() + 2, 6, 1, 45),
       (CURDATE() + 2, 5, 2, 4),
       (CURDATE() + 3, 6, 1, 23),
       (CURDATE() + 3, 7, 2, -4),
       (CURDATE() + 4, 8, 1, 4),
       (CURDATE() + 4, 9, 1, 4),
       (CURDATE() + 4, 10, 1, -4);