CREATE TABLE IF NOT EXISTS addresses
(
    id      VARCHAR(36) NOT NULL PRIMARY KEY,
    house   VARCHAR(10) NOT NULL,
    street  VARCHAR(30) NOT NULL,
    city    VARCHAR(30) NOT NULL,
    region  VARCHAR(30) NOT NULL,
    zipcode VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS universities
(
    id   VARCHAR(36) NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS students
(
    id            VARCHAR(36) NOT NULL PRIMARY KEY,
    first_name    VARCHAR(30) NOT NULL,
    last_name     VARCHAR(30) NOT NULL,
    address_id    VARCHAR(36) NOT NULL REFERENCES addresses,
    university_id VARCHAR(36) NOT NULL REFERENCES universities
);