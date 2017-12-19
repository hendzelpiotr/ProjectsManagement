ALTER TABLE user_project
    DROP COLUMN ready_to_grade;

ALTER TABLE user_project
    RENAME completion_date TO scheduled_completion_date;

ALTER TABLE user_project
    ALTER COLUMN scheduled_completion_date TYPE TIMESTAMP WITHOUT TIME ZONE;