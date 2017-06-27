UPDATE user_project
SET user_project.scheduled_completion_date = '2018-06-06 08:15:00';

ALTER TABLE user_project
  MODIFY scheduled_completion_date DATETIME NOT NULL;