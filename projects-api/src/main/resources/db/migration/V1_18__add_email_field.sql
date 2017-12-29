ALTER TABLE user_detail
  ADD COLUMN email VARCHAR(150);

UPDATE user_detail
SET email = 'admin@prz.com';

ALTER TABLE user_detail
  ALTER COLUMN email TYPE VARCHAR(150),
  ALTER COLUMN email SET NOT NULL;