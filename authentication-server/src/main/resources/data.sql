INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	("fooClientIdPassword", "secret", "foo,read,write",
	"password,authorization_code,refresh_token", null, null, 36000, 36000, null, true);
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	("sampleClientId", "secret", "read,write,foo,bar",
	"implicit", null, null, 36000, 36000, null, false);
INSERT INTO oauth_client_details
	(client_id, client_secret, scope, authorized_grant_types,
	web_server_redirect_uri, authorities, access_token_validity,
	refresh_token_validity, additional_information, autoapprove)
VALUES
	("barClientIdPassword", "secret", "bar,read,write",
	"password,authorization_code,refresh_token", null, null, 36000, 36000, null, true);
INSERT INTO role (name)
VALUES ('ROLE_ADMIN'), ('ROLE_STUDENT');

INSERT INTO user (login, password, name, surname, role_id, enabled)
VALUES ('adminprz', '$2a$10$MTLoYDtNVuM2DeOrwglck.l.BaIEeSBRZf5G4ghwSHgrq4Zf04c5.', 'Piotr', 'Hendzel', 1, 1);