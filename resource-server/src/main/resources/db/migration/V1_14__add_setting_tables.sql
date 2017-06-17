CREATE TABLE IF NOT EXISTS setting
(
  id       INTEGER      NOT NULL AUTO_INCREMENT,
  name     VARCHAR(100) NOT NULL,
  data_type VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_setting
(
  id             INTEGER NOT NULL AUTO_INCREMENT,
  setting_id     INTEGER NOT NULL,
  user_detail_id VARCHAR(100),
  value          TEXT    NOT NULL,
  global         BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (setting_id) REFERENCES setting (id),
  FOREIGN KEY (user_detail_id) REFERENCES user_details (login)
);

INSERT INTO setting (name, data_type) VALUE ('scheduled_completion_date', 'DATE');

INSERT INTO user_setting (setting_id, value, global) VALUE (1, '2017-07-10', true);

ALTER TABLE user_project
    DROP scheduled_completion_date;

RENAME TABLE user_details TO user_detail;

