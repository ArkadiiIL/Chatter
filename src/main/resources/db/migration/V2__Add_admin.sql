insert into usr (id,username,password,active)
values (1,'root','$2y$12$DJiQONZtn0NJiPEl5ZwSweKqpda4HqC/yQXr.ajr38jnmdQF8C7aW',true);

insert into user_role (user_id, roles)
values (1,'USER'),(1,'ADMIN');