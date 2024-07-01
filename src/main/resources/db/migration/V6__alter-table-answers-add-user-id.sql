ALTER TABLE tb_answers ADD COLUMN user_id BIGINT NOT NULL;
ALTER TABLE tb_answers ADD constraint fk_tb_answers_user_id FOREIGN KEY(user_id) REFERENCES tb_users(id);
