CREATE TABLE IF NOT EXISTS tb_users_roles(
    user_id BIGINT NOT NULL,
    role_id TINYINT NOT NULL,
    PRIMARY KEY(user_id, role_id),
    CONSTRAINT fk_user_id FOREIGN KEY(user_id) REFERENCES tb_users(id),
    CONSTRAINT fk_role_id FOREIGN KEY(role_id) REFERENCES tb_roles(id)
)