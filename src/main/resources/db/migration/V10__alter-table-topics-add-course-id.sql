ALTER TABLE tb_topics ADD COLUMN course_id INT NOT NULL;
ALTER TABLE tb_topics ADD CONSTRAINT fk_tb_topics_course_id FOREIGN KEY(course_id) REFERENCES tb_courses(id);