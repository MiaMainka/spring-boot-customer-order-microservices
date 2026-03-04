CREATE DATABASE IF NOT EXISTS customer_db;
CREATE DATABASE IF NOT EXISTS order_db;

CREATE USER IF NOT EXISTS 'shop'@'localhost' IDENTIFIED BY 'shop';

GRANT ALL PRIVILEGES ON customer_db.* TO 'shop'@'localhost';
GRANT ALL PRIVILEGES ON order_db.* TO 'shop'@'localhost';

FLUSH PRIVILEGES;