INSERT INTO projects_management_db.role (name)
VALUES ('ROLE_ADMIN'), ('ROLE_STUDENT');

INSERT INTO projects_management_db.user (login, password, name, surname, role_id)
VALUES ('adminprz', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 'Piotr', 'Hendzel', 1);

INSERT INTO projects_management_db.user (login, password, name, surname, role_id, laboratory_group, professor_id)
VALUES ('kgracik', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 'Kamila', 'Gracik', 2, 2, 1),
  ('jsadnik','$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.','Jarosław','Stadnik',2,7,1);

INSERT INTO projects_management_db.project (name, description)
VALUES
  ('Aplikacja JavaFX - kalkulator', 'Aplikacja okienkowa wykonana w JavaFX. Kalkulator rozbudowany, oparty na ONP.'),
  ('Apliacja Swing - paint', 'Funkcjonalności podobne do typowego painta. Wymagane użycie swinga');