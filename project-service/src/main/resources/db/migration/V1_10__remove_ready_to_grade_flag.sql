ALTER TABLE user_project
    DROP COLUMN ready_to_grade;

ALTER TABLE user_project
    CHANGE completion_date scheduled_completion_date DATETIME;