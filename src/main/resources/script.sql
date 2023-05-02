
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       user_name VARCHAR(255) NOT NULL UNIQUE,
                       admin_role BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE customer_order (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                order_id VARCHAR(255) NOT NULL,
                                total DECIMAL(10, 2) NOT NULL,
                                card_number VARCHAR(255) NOT NULL,
                                card_holder VARCHAR(255) NOT NULL,
                                expiry_date VARCHAR(255) NOT NULL,
                                security_code VARCHAR(255) NOT NULL,
                                reported BOOLEAN NOT NULL DEFAULT FALSE,
                                FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE product (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         order_id BIGINT,
                         title VARCHAR(255) NOT NULL,
                         price DECIMAL(10, 2) NOT NULL,
                         image_url VARCHAR(255),
                         FOREIGN KEY (order_id) REFERENCES customer_order(id)
);
CREATE TABLE response (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          name VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          subject VARCHAR(255) NOT NULL,
                          message TEXT NOT NULL
);

INSERT INTO users (first_name, last_name, password, user_name, admin_role)
VALUES ('John', 'Doe', 'password123', 'john_doe', FALSE),
       ('Jane', 'Smith', 'password456', 'jane_smith', FALSE);

INSERT INTO customer_order (user_id, order_id, total, card_number, card_holder, expiry_date, security_code, reported)
VALUES (1, 'ORD001', 100.00, '1234567812345678', 'John Doe', '12/25', '123', FALSE),
       (2, 'ORD002', 200.00, '2345678923456789', 'Jane Smith', '01/24', '456', FALSE);

INSERT INTO product (order_id, title, price, image_url)
VALUES (1, 'Product 1', 50.00, 'https://example.com/product1.jpg'),
       (1, 'Product 2', 50.00, 'https://example.com/product2.jpg'),
       (2, 'Product 3', 100.00, 'https://example.com/product3.jpg'),
       (2, 'Product 4', 100.00, 'https://example.com/product4.jpg');

INSERT INTO response (name, email, subject, message)
VALUES ('Alice', 'alice@example.com', 'Subject 1', 'This is a message from Alice.'),
       ('Bob', 'bob@example.com', 'Subject 2', 'This is a message from Bob.');
