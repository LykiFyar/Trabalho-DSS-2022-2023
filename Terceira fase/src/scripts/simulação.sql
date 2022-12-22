CREATE DATABASE Simulação;

CREATE USER 'grupo30'@'localhost' IDENTIFIED BY 'Grupo30#pass!';
GRANT ALL PRIVILEGES ON * . * TO 'grupo30'@'localhost';
FLUSH PRIVILEGES;