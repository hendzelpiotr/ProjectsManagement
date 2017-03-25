INSERT INTO projects_management_db.role (name)
VALUES ('ROLE_ADMIN'), ('ROLE_STUDENT');

INSERT INTO projects_management_db.user (login, password, name, surname, role_id)
VALUES ('adminprz', 'admin123', 'Piotr', 'Hendzel', 1);

INSERT INTO projects_management_db.user (login, password, name, surname, role_id, laboratory_group, professor_id)
VALUES ('kgracik', 'gracik123', 'Kamila', 'Gracik', 2, 2, 1),
  ('jsadnik','12345user','Jarosław','Stadnik',2,7,1);

INSERT INTO projects_management_db.project (name, description)
VALUES
  ('Aplikacja JavaFX - kalkulator', 'Aplikacja okienkowa wykonana w JavaFX. Kalkulator rozbudowany, oparty na ONP.'),
  ('Apliacja Swing - paint', 'Funkcjonalności podobne do typowego painta. Wymagane użycie swinga');