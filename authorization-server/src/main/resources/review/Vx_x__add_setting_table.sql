CREATE TABLE IF NOT EXISTS setting
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  value TEXT NOT NULL,
  data_type VARCHAR(50) NOT NULL,
  description TEXT,
  PRIMARY KEY (id),
  CONSTRAINT UNIQUE_NAME UNIQUE (name)
);

INSERT INTO setting (name, value, data_type)
  VALUE ('completion_date', '01.01.2018', 'LocalDate');

CREATE TABLE IF NOT EXISTS user_setting
(
  id INTEGER NOT NULL AUTO_INCREMENT,
  user_id INTEGER NOT NULL,
  setting_id INTEGER NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES user(id),
  FOREIGN KEY (setting_id) REFERENCES setting(id)
);

INSERT INTO user_setting (user_id, setting_id)
  VALUE (1, 1);
