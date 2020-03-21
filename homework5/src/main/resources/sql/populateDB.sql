SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE users;
TRUNCATE TABLE accounts;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO accounts (email, account_status) VALUES
('qwerty123@asd.com', 'ACTIVE'),
('gord_o@asd.com', 'BANNED');

INSERT INTO users (full_name, account_id) VALUES
('James Gordon', (SELECT a.id FROM accounts a WHERE a.email = 'qwerty123@asd.com')),
('Barbara Gordon', (SELECT a.id FROM accounts a WHERE a.email = 'gord_o@asd.com'));