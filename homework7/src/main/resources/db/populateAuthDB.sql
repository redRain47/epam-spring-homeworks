INSERT INTO users (name, password) VALUES
('admin', '$2y$12$PYpEFII.FK1MspKHm9607eLuPfsoVxB4iI5jBAi5P2NKVLKf/tl0y'), -- password: nimda
('user', '$2y$12$Pi/l1jCelXxCLsRkYh0dUe2aTaZYuw7arnZJsVFQMXF1kJk7l/0Wq'); -- password: resu

INSERT INTO authorities (user_id, authority) VALUES
(1, 'ROLE_ADMIN'),
(2, 'ROLE_USER');