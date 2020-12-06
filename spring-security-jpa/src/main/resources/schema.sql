create table user_profile
(
    username varchar_ignorecase(50) not null primary key,
    password varchar_ignorecase(50) not null,
    active  boolean not null,
    first_name varchar2(30),
    last_name varchar2(40),
    email_address varchar2(60)
);

create table user_roles
(
    username  varchar_ignorecase(50) not null,
    authority varchar_ignorecase(50) not null,
    constraint fk_authorities_users foreign key (username) references user_profile (username)
);
create
unique index ix_auth_username on user_roles (username,authority);