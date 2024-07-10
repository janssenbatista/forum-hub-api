INSERT INTO tb_courses(name, category) VALUES ('Gestão Ágil: explorando conceitos da agilidade', 'Cursos de Agilidade');
INSERT INTO tb_courses(name, category) VALUES ('Java: criando a sua primeira aplicação', 'Cursos de Java');
INSERT INTO tb_courses(name, category) VALUES ('Java: trabalhando com listas e coleções de dados', 'Cursos de Java');

INSERT INTO tb_roles(name) VALUES ('ADMIN');
INSERT INTO tb_roles(name) VALUES ('USER');

INSERT INTO tb_users(name, email, password) VALUES ('user name', 'user@email.com', '$2a$12$KCECceowNAo8zzXR2LRaPeO3sMYfuxD3hr2cQgwgjD81ZKN4yAKey');
INSERT INTO tb_users(name, email, password) VALUES ('admin name', 'admin@email.com', '$2a$12$pOyKJ7W3aXXMRvros7pWVOQnN246Q6CXOm8VhAugPbaMH3CPxQJS.');

INSERT INTO tb_users_roles VALUES (1, 2);
INSERT INTO tb_users_roles VALUES (2, 1);