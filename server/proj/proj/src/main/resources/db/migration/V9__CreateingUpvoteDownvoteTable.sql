CREATE TABLE userCommentVotes (
id BIGSERIAL PRIMARY KEY,
comment_id INT,
user_id INT,
upvoted INT,
downvoted INT);