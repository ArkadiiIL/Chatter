delete from user_role;
delete from usr;

insert into usr(id, username, password, active) values
(1,'root','$2y$12$DJiQONZtn0NJiPEl5ZwSweKqpda4HqC/yQXr.ajr38jnmdQF8C7aW', true),
(2,'1','$2a$08$IH494jOw/aJONeS4yUlSyOXPgey0YkhfP06B0wAoggss0C9jzl.3S',true);

insert into user_role(user_id,roles) values
(1,'USER'), (1,'ADMIN'),
(2,'USER');