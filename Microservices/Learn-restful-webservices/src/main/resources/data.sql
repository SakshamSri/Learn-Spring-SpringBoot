insert into user(id, name, birth_date) values(101, 'Name101', sysdate());
insert into user(id, name, birth_date) values(102, 'Name102', sysdate());
insert into user(id, name, birth_date) values(103, 'Name103', sysdate());
insert into user(id, name, birth_date) values(104, 'Name104', sysdate());
insert into user(id, name, birth_date) values(105, 'Name105', sysdate());

insert into post(id, description, user_id) values(201, 'Description201', 101);
insert into post(id, description, user_id) values(202, 'Description202', 101);
insert into post(id, description, user_id) values(203, 'Description203', 101);
insert into post(id, description, user_id) values(204, 'Description204', 103);
insert into post(id, description, user_id) values(205, 'Description205', 103);
insert into post(id, description, user_id) values(206, 'Description206', 104);
insert into post(id, description, user_id) values(207, 'Description207', 105);