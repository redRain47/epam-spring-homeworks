CREATE TABLE IF NOT EXISTS users (
    id      INT         NOT NULL PRIMARY KEY AUTO_INCREMENT,
    fullName    VARCHAR(30) NOT NULL,
    email   VARCHAR(30) NOT NULL,
    balance INT         NOT NULL
);