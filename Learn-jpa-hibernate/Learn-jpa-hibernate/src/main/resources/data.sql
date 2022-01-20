insert into course(id, name, created_date_time, last_updated_date_time) values(101, 'Course101', sysdate(), sysdate());
insert into course(id, name, created_date_time, last_updated_date_time) values(102, 'Course102', sysdate(), sysdate());
insert into course(id, name, created_date_time, last_updated_date_time) values(103, 'Course103', sysdate(), sysdate());
insert into course(id, name, created_date_time, last_updated_date_time) values(104, 'Spring in 100 Steps', sysdate(), sysdate());
insert into course(id, name, created_date_time, last_updated_date_time) values(105, 'Spring in 102 Steps', sysdate(), sysdate());

insert into passport(id, number) values(301, 'Passport301');
insert into passport(id, number) values(302, 'Passport302');
insert into passport(id, number) values(303, 'Passport303');
insert into passport(id, number) values(304, 'Passport304');

insert into student(id, name, passport_id) values(201, 'Student201', 301);
insert into student(id, name, passport_id) values(202, 'Student202', 302);
insert into student(id, name, passport_id) values(203, 'Student203', 303);
insert into student(id, name, passport_id) values(204, 'Student204', 304);

insert into review(id, rating, description, course_id) values(401, 'Rating401', 'Description401', 101);
insert into review(id, rating, description, course_id) values(402, 'Rating402', 'Description402', 101);
insert into review(id, rating, description, course_id) values(403, 'Rating403', 'Description403', 104);
insert into review(id, rating, description, course_id) values(404, 'Rating404', 'Description404', 103);

insert into student_course(student_id, course_id) values(201, 101);
insert into student_course(student_id, course_id) values(202, 101);
insert into student_course(student_id, course_id) values(203, 101);
insert into student_course(student_id, course_id) values(201, 102);
insert into student_course(student_id, course_id) values(203, 103);