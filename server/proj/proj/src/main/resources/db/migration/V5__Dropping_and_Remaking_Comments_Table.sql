DROP TABLE IF EXISTS comments;

CREATE TABLE comments (id BIGSERIAL PRIMARY KEY, user_id INTEGER, comment VARCHAR(255),
upvotes INTEGER, downvotes INTEGER, constituency_id INTEGER,
comment_title VARCHAR(255), comment_category VARCHAR(255), post_date VARCHAR(255));