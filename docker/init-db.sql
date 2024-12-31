CREATE DATABASE dnd_rest_db;
CREATE USER dnd_rest_user WITH ENCRYPTED PASSWORD 'dndrest!!!';
\c dnd_rest_db

GRANT ALL PRIVILEGES ON DATABASE dnd_rest_db TO dnd_rest_user;

