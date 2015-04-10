CREATE TABLE user (
    id IDENTITY,
    first_name VARCHAR(25),
    last_name VARCHAR(25)
);

INSERT INTO user (first_name, last_name) VALUES ('Bruce', 'Wayne');
INSERT INTO user (first_name, last_name) VALUES ('Alfred', 'Pennyworth');
