CREATE DATABASE projects_management_db;
CREATE DATABASE user_service_db;
CREATE DATABASE authentication_db;

GRANT ALL PRIVILEGES ON projects_management_db.* To 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON user_service_db.* To 'admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON authentication_db.* To 'admin'@'localhost' IDENTIFIED BY 'admin';