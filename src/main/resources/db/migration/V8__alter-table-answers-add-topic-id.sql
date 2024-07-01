ALTER TABLE tb_answers ADD COLUMN topic_id VARCHAR(36) NOT NULL;
ALTER TABLE tb_answers ADD constraint fk_tb_answers_topic_id FOREIGN KEY(topic_id) REFERENCES tb_topics(id);