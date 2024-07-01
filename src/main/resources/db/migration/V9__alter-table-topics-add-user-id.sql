ALTER TABLE tb_topics ADD COLUMN user_id BIGINT NOT NULL;
ALTER TABLE tb_topics ADD CONSTRAINT fk_tb_topics_user_id FOREIGN KEY(user_id) REFERENCES tb_users(id);