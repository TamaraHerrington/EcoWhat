CREATE TABLE users (
id BIGSERIAL PRIMARY KEY,
first_name VARCHAR(255),
last_name VARCHAR(255),
email VARCHAR(25),
password VARCHAR(255),
constituency_id INT,
constituency_name VARCHAR(255),
token VARCHAR(255));