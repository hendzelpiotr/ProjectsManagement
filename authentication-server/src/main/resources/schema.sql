drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
);

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication LONG VARBINARY,
  refresh_token VARCHAR(255)
);

drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
  token LONG VARBINARY,
  authentication LONG VARBINARY
);

drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), authentication LONG VARBINARY
);

drop table if exists oauth_approvals;
create table oauth_approvals (
  userId VARCHAR(255),
  clientId VARCHAR(255),
  scope VARCHAR(255),
  status VARCHAR(10),
  expiresAt TIMESTAMP,
  lastModifiedAt TIMESTAMP
);

drop table if exists ClientDetails;
create table ClientDetails (
  appId VARCHAR(255) PRIMARY KEY,
  resourceIds VARCHAR(255),
  appSecret VARCHAR(255),
  scope VARCHAR(255),
  grantTypes VARCHAR(255),
  redirectUrl VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additionalInformation VARCHAR(4096),
  autoApproveScopes VARCHAR(255)
);

drop TABLE if EXISTS user;
DROP TABLE IF EXISTS role;
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
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  professor_id INTEGER,
  role_id INTEGER NOT NULL,
  laboratory_group INTEGER,
  enabled BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  FOREIGN KEY (professor_id) REFERENCES user(id),
  FOREIGN KEY (role_id) REFERENCES role(id)
);