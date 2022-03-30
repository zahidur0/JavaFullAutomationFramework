CREATE TABLE correct_login_details (
    id Integer PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE incorrect_login_details (
    id Integer PRIMARY KEY AUTOINCREMENT,
    username VARCHAR(255) UNIQUE,
    password VARCHAR(255) NOT NULL
);

DROP TABLE correct_login_details;
DROP TABLE incorrect_login_details;

INSERT INTO incorrect_login_details (username, password) VALUES
("lindsey_marni@email.com", "l1nds3y"),
("jude_heath@email.com", "jud3h34th"),
("rebecca_josie@email.com", "r3b3cc4"),
("morris_conway@email.com", "c0nw4y"),
("matt_rolland@email.com", "m4ttr0114nd");

INSERT INTO correct_login_details (username, password) VALUES
("123123@email.com", "123123");

SELECT COUNT(*) FROM correct_login_details;
SELECT COUNT(*) FROM incorrect_login_details;
SELECT id, username, password FROM correct_login_details;
SELECT id, username, password FROM incorrect_login_details;