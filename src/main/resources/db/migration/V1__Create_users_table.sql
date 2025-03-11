CREATE TABLE users
(
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL
);

INSERT INTO users (username)
VALUES ('Артем');
INSERT INTO users (username)
VALUES ('Шафа');