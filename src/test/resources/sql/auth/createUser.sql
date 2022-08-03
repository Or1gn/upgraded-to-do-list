ALTER SEQUENCE users_id_seq RESTART 50;
INSERT INTO users (username, email, activation_code, activated_status, password, role)
VALUES ('testuser', 'bavoy62502@aregods.com', '62cb778d-8791-49b5-87c3-93b54c841e6d',
        false, '$2a$10$akwthanNso6Og8T0oDeQhOcyeIty9Zng0HjD7qtJmoMSC4G945/9C', 'USER');