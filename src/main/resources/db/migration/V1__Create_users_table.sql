CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

INSERT INTO users (username)
VALUES ('Артем');
INSERT INTO users (username)
VALUES ('Шафа');

CREATE TABLE products
(
    id             SERIAL PRIMARY KEY,
    account_number VARCHAR(255)   NOT NULL,
    balance        NUMERIC(19, 2) NOT NULL,
    product_type   VARCHAR(50)    NOT NULL,
    user_id        BIGINT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id)
);