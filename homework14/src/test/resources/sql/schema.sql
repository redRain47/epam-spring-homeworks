CREATE TABLE IF NOT EXISTS products
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS warehouses
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS warehouse_movement
(
    id           INT PRIMARY KEY AUTO_INCREMENT,
    date         DATE NOT NULL,
    product_id   VARCHAR(36) REFERENCES products,
    warehouse_id VARCHAR(36) REFERENCES warehouses,
    quantity     INT  NOT NULL
);