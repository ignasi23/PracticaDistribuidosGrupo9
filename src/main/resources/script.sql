
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
                                order_id VARCHAR(255),
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


