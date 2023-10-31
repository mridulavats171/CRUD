create table projects(
	id int primary key not  null unique,
    project_name varchar(30) not null
    
);
truncate projects;
drop table projects;

insert into projects values (id, project_name),
(1, "mobile app"),
(2, "web page"),
(3, "tv app")


select * from projects;