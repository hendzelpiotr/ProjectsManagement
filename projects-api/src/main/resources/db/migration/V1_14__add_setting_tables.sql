CREATE TABLE IF NOT EXISTS setting
(
  id       SERIAL      NOT NULL,
  name     VARCHAR(100) NOT NULL,
  data_type VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS user_setting
(
  id             SERIAL NOT NULL,
  setting_id     INTEGER NOT NULL,
  user_detail_id VARCHAR(100),
  value          TEXT    NOT NULL,
  global         BOOLEAN NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (setting_id) REFERENCES setting (id),
  FOREIGN KEY (user_detail_id) REFERENCES user_details (login)
);

INSERT INTO setting (name, data_type)
VALUES ('scheduled_completion_date', 'DATE');

INSERT INTO user_setting (setting_id, value, global)
VALUES (1, '2017-07-10', true);

ALTER TABLE user_project
    DROP scheduled_completion_date;

ALTER TABLE user_details
  RENAME TO user_detail;

