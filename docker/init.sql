CREATE ROLE "project_service_db_user" with LOGIN PASSWORD 'admin1';
CREATE ROLE "user_service_db_user" WITH LOGIN PASSWORD 'admin2';
CREATE ROLE "authentication_server_db_user" WITH LOGIN PASSWORD 'admin3';
CREATE ROLE "user_service_db_user_read_only" WITH LOGIN PASSWORD 'admin4';

create database "projects_management_db" owner "project_service_db_user" encoding 'UTF8' template template0;
create database "user_service_db" owner "user_service_db_user" encoding 'UTF8' template template0;
create database "authentication_db" owner "authentication_server_db_user" encoding 'UTF8' template template0;