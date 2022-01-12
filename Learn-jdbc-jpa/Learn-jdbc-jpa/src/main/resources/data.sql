create table person
(
	id integer not null,
	name varchar(255) not null,
	location varchar(255),
	birth_date timestamp,
	primary key(id)
);

insert into person (id, name, location, birth_date)
values (100001, 'Geralt', 'Kaer Morhen', sysdate());
insert into person (id, name, location, birth_date)
values (100002, 'Yennefer', 'Vengerberg', sysdate());
insert into person (id, name, location, birth_date)
values (100003, 'Ciri', 'Skellige', sysdate());
insert into person (id, name, location, birth_date)
values (100004, 'Triss', 'Maribor', sysdate());
insert into person (id, name, location, birth_date)
values (100005, 'Vesemir', 'Kaer Morhen', sysdate());
