CREATE TABLE IF NOT EXISTS user_details
(
  login            VARCHAR(100) NOT NULL,
  name             VARCHAR(100),
  surname          VARCHAR(100),
  professor_login  VARCHAR(100),
  laboratory_group INTEGER,
  PRIMARY KEY (login),
  FOREIGN KEY (professor_login) REFERENCES user_details (login)
);

ALTER TABLE user_project
    RENAME user_id TO user_login;

ALTER TABLE user_project
    ALTER COLUMN user_login TYPE VARCHAR(100),
    ALTER COLUMN user_login SET NOT NULL;

ALTER TABLE user_project
  ADD FOREIGN KEY (user_login) REFERENCES user_details (login);

INSERT INTO user_details
(login, name, surname)
VALUES
  ('adminprz', 'Piotr', 'Hendzel');

INSERT INTO user_details
(login, name, surname, laboratory_group, professor_login)
VALUES
  ('kgracik', 'Kamila', 'Gracik', 2, 'adminprz'),
  ('jsadnik', 'Jarosław', 'Stadnik', 7, 'adminprz');