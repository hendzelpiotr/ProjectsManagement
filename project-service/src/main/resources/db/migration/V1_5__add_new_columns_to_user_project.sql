ALTER TABLE user_project
  ADD COLUMN datetime_of_project_selection TIMESTAMP WITHOUT TIME ZONE,
  ADD COLUMN programming_language VARCHAR(200),
  ADD COLUMN technologies VARCHAR(2000),
  ADD COLUMN database VARCHAR(200),
  ADD COLUMN additional_information VARCHAR(4000),
  ADD COLUMN repository_link VARCHAR(2000);