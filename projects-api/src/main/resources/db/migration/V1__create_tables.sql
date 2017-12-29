CREATE TABLE IF NOT EXISTS role
(
  id SERIAL NOT NULL,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS "user"
(
  id SERIAL NOT NULL,
  login VARCHAR(100) NOT NULL,
  password VARCHAR(100) NOT NULL,
  name VARCHAR(100) NOT NULL,
  surname VARCHAR(100) NOT NULL,
  professor_id INTEGER,
  role_id INTEGER NOT NULL,
  laboratory_group INTEGER,
  enabled BOOLEAN NOT NULL DEFAULT FALSE,
  PRIMARY KEY (id),
  FOREIGN KEY (professor_id) REFERENCES "user" (id),
  FOREIGN KEY (role_id) REFERENCES role(id)
);

CREATE TABLE IF NOT EXISTS project
(
  id SERIAL NOT NULL,
  name VARCHAR(150) NOT NULL,
  description VARCHAR(2000),
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_project
(
  id SERIAL NOT NULL,
  user_id INTEGER NOT NULL,
  project_id INTEGER NOT NULL,
  mark VARCHAR(100),
  completion_date TIMESTAMP WITHOUT TIME ZONE,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES "user" (id),
  FOREIGN KEY (project_id) REFERENCES project(id),
  CONSTRAINT UNIQUE_USER_ID UNIQUE (user_id)
);

