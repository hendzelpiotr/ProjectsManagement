CREATE TABLE IF NOT EXISTS role
(
  id INTEGER NOT NULL auto_increment,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS user
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  login VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  role_id INTEGER NOT NULL,
  enabled BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  FOREIGN KEY (role_id) REFERENCES role(id)
);

INSERT INTO role
(name)
VALUES
  ('ROLE_ADMIN'), ('ROLE_STUDENT');

INSERT INTO user
(login, password, role_id, enabled)
VALUES
  ('adminprz', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 1, 1);

INSERT INTO user
(login, password, role_id, enabled)
VALUES
  ('kgracik', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 2, 1),
  ('jsadnik', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 2, 1);
