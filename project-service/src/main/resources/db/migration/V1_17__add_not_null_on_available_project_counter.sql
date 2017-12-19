ALTER TABLE project
  ALTER COLUMN available_projects_counter TYPE INTEGER,
  ALTER COLUMN available_projects_counter SET NOT NULL;