CREATE TABLE IF NOT EXISTS user_details
(
  login            VARCHAR(100) NOT NULL,
  name             VARCHAR(100) NOT NULL,
  surname          VARCHAR(100) NOT NULL,
  professor_login  VARCHAR(100),
  laboratory_group INTEGER,
  PRIMARY KEY (login),
  FOREIGN KEY (professor_login) REFERENCES user_details (login)
);

ALTER TABLE user_project
  CHANGE user_id user_login VARCHAR(100) NOT NULL;

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