CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL
);

INSERT INTO users (username)
VALUES ('Артем');
INSERT INTO users (username)
VALUES ('Шафа');

CREATE TABLE products
(
    id BIGSERIAL PRIMARY KEY,
    account_number VARCHAR(255) UNIQUE NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    product_type VARCHAR(50) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO products (account_number, balance, product_type, user_id)
VALUES ('ACC123456', 1000.0, 'счет', 1),
       ('CARD654321', 500.0, 'карта', 1),
       ('ACC987654', 2000.0, 'счет', 2);