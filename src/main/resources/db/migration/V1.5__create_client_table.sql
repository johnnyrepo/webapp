create table CLIENT (
    ID long primary key,
    FIRST_NAME varchar(100) not null,
    LAST_NAME varchar(200) not null,
    USERNAME varchar(50) not null,
    EMAIL varchar(200),
    ADDRESS varchar(500) not null,
    COUNTRY_ID long not null,
    APP_USER_ID long not null
);

alter table CLIENT add foreign key(APP_USER_ID) references APP_USER(ID);
alter table CLIENT add foreign key(COUNTRY_ID) references COUNTRY(ID);

create sequence CLIENT_SEQ;
