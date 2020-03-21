CREATE TABLE IF NOT EXISTS users (
    id         INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    full_name   VARCHAR(30) NOT NULL,
    account_id INT         NULL,
    FOREIGN KEY (account_id)
        REFERENCES accounts (id),
    UNIQUE (full_name, account_id)
);

CREATE TABLE IF NOT EXISTS accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(50),
    account_status VARCHAR(15),
    UNIQUE (email, account_status)
);