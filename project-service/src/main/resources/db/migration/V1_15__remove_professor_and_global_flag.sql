ALTER TABLE user_detail
    DROP FOREIGN KEY user_detail_ibfk_1,
    DROP COLUMN professor_login;

ALTER TABLE user_setting
    DROP COLUMN global;