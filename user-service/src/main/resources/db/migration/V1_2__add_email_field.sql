ALTER TABLE user
    ADD COLUMN email VARCHAR(150);

UPDATE user
SET email = 'admin@prz.com';

ALTER TABLE user
    MODIFY email VARCHAR(150) NOT NULL;