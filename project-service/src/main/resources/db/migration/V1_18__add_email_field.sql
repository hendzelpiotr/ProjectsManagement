ALTER TABLE user_detail
  ADD COLUMN email VARCHAR(150);

UPDATE user_detail
SET email = 'admin@prz.com';

ALTER TABLE user_detail
  MODIFY email VARCHAR(150) NOT NULL;