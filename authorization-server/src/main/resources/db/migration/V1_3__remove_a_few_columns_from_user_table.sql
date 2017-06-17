ALTER TABLE user
  DROP FOREIGN KEY user_ibfk_1;

ALTER TABLE user
  DROP name,
  DROP laboratory_group,
  DROP professor_id,
  DROP surname;
