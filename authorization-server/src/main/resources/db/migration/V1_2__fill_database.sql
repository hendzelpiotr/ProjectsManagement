INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('ResourceServer_PRZ_2017', 'zaq1@WSXzaq1@WSX', 'read,write', 'password,authorization_code,refresh_token', NULL, NULL, 36000, 36000, NULL, TRUE);

INSERT INTO oauth_client_details
(client_id, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove)
VALUES
  ('Client_PRZ_2017', 'xsw2!QAZxsw2!QAZ', 'read,write', 'password,authorization_code,refresh_token', NULL, NULL, 36000, 36000, NULL, TRUE);

INSERT INTO role
(name)
VALUES
  ('ROLE_ADMIN'), ('ROLE_STUDENT');

INSERT INTO "user"
(login, password, name, surname, role_id, enabled)
VALUES
  ('adminprz', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 'Piotr', 'Hendzel', 1, true);

INSERT INTO "user"
(login, password, name, surname, role_id, laboratory_group, professor_id, enabled)
VALUES
  ('kgracik', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 'Kamila', 'Gracik', 2, 2, 1, true),
  ('jsadnik', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 'Jarosław', 'Stadnik', 2, 7, 1, true);
