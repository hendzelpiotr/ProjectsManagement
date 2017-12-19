create role "admin" with login password 'admin';

create database "projects_management_db" owner "admin" encoding 'UTF8' template template0;
create database "user_service_db" owner "admin" encoding 'UTF8' template template0;
create database "authentication_db" owner "admin" encoding 'UTF8' template template0;