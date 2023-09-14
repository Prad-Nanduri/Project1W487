INSERT INTO usertable (userID, name, usertype) VALUES ('999999991', 'Ali', 'student');
INSERT INTO usertable (userID, name, usertype) VALUES ('999999992', 'Luca', 'student');
INSERT INTO usertable (userID, name, usertype) VALUES ('999999993', 'Zaka', 'student');
INSERT INTO usertable (userID, name, usertype) VALUES ('999999994', 'Nyugen', 'admin');
INSERT INTO usertable (userID, name, usertype) VALUES ('999999995', 'Verbanec', 'admin');
INSERT INTO usertable (userID, name, usertype) VALUES ('999999996', 'Ritu', 'admin');
INSERT INTO usertable (userID, name, usertype) VALUES ('999999997', 'Sindhu', 'student');

SELECT count(1) FROM usertable WHERE userID = '999999991';
SELECT count(1) FROM usertable WHERE usertype = 'student';
SELECT * FROM usertable;
SELECT * FROM usertable WHERE usertype = 'admin'



