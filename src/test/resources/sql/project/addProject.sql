ALTER SEQUENCE users_id_seq RESTART 50;
INSERT INTO users (username, email, activation_code, activated_status, password, role)
VALUES ('uebapi2101', 'uebapi2101@gmail.ru', '62cb778d-8791-49b5-87c3-93b54c841e6d',
        true, '$2a$10$akwthanNso6Og8T0oDeQhOcyeIty9Zng0HjD7qtJmoMSC4G945/9C', 'USER');
ALTER SEQUENCE project_id_seq RESTART 50;
INSERT INTO project (link)
VALUES ('randomrandomransom');
ALTER SEQUENCE user2project_id_seq RESTART 50;
INSERT INTO user2project (user_id, project_id, role)
VALUES ('50', '50', 'PROJECT_LEAD');