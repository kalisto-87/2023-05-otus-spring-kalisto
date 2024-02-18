drop table if exists books;
create table if not exists books (
        id bigint not null,
        name varchar(255) not null,
        primary key (id)
);
create sequence if not exists book_seq increment by 1 start with 1;