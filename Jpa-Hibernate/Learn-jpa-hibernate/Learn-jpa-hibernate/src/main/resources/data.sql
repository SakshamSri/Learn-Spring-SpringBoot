insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(101, 'Course101', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(102, 'Course102', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(103, 'Course103', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(104, 'Spring in 104 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(105, 'Spring in 105 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(106, 'Spring in 106 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(107, 'Spring in 107 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(108, 'Spring in 108 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(109, 'Spring in 109 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(110, 'Spring in 110 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(111, 'Spring in 111 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(112, 'Spring in 112 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(113, 'Spring in 113 Steps', sysdate(), sysdate(), false);
insert into course(id, name, created_date_time, last_updated_date_time, is_deleted) values(114, 'Spring in 114 Steps', sysdate(), sysdate(), false);

insert into passport(id, number) values(301, 'Passport301');
insert into passport(id, number) values(302, 'Passport302');
insert into passport(id, number) values(303, 'Passport303');
insert into passport(id, number) values(304, 'Passport304');

insert into student(id, name, passport_id) values(201, 'Student201', 301);
insert into student(id, name, passport_id) values(202, 'Student202', 302);
insert into student(id, name, passport_id) values(203, 'Student203', 303);
insert into student(id, name, passport_id) values(204, 'Student204', 304);

insert into review(id, rating, description, course_id) values(401, 'FIVE', 'Description401', 101);
insert into review(id, rating, description, course_id) values(402, 'FOUR', 'Description402', 101);
insert into review(id, rating, description, course_id) values(403, 'ONE', 'Description403', 104);
insert into review(id, rating, description, course_id) values(404, 'THREE', 'Description404', 103);

insert into student_course(student_id, course_id) values(201, 101);
insert into student_course(student_id, course_id) values(202, 101);
insert into student_course(student_id, course_id) values(203, 101);
insert into student_course(student_id, course_id) values(201, 102);
insert into student_course(student_id, course_id) values(203, 103);