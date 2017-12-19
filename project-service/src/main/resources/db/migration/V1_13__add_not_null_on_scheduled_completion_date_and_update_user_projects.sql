UPDATE user_project
SET scheduled_completion_date = '2018-06-06 08:15:00';

ALTER TABLE user_project
  ALTER COLUMN scheduled_completion_date TYPE TIMESTAMP WITHOUT TIME ZONE,
  ALTER COLUMN scheduled_completion_date SET NOT NULL;