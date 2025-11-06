CREATE DATABASE IF NOT EXISTS shopping_cart_db;
USE shopping_cart_db;

CREATE TABLE IF NOT EXISTS items
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    price    DOUBLE NOT NULL,
    quantity INT    NOT NULL
);

CREATE TABLE IF NOT EXISTS item_translations
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    item_id INT          NOT NULL,
    lang    VARCHAR(5)   NOT NULL,
    name    VARCHAR(255) NOT NULL,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);

ALTER TABLE item_translations MODIFY name VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
ALTER TABLE item_translations CONVERT TO CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;