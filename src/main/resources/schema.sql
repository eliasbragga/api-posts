CREATE SCHEMA IF NOT EXISTS post;

DROP TABLE IF EXISTS post.users CASCADE;

CREATE TABLE IF NOT EXISTS post.user (
                                          id SERIAL PRIMARY KEY,
                                          name VARCHAR(100) NOT NULL
    );

CREATE TABLE IF NOT EXISTS post.post (
                                         id SERIAL PRIMARY KEY,
                                         title VARCHAR(100) NOT NULL,
    content VARCHAR(5000) NOT NULL,
    user_id INTEGER NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES post.user(id)
    );

