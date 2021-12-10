CREATE TABLE users (
id BIGSERIAL PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255),
email VARCHAR(255),
password VARCHAR(255),
constituency_id INT,
token VARCHAR(255));