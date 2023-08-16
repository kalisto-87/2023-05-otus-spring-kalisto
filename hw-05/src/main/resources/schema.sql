create table if not exists authors (
    id bigint auto_increment,
    name varchar2(255),
    primary key(id)
);

create table if not exists genre (
    id bigint auto_increment,
    name varchar2(255),
    primary key(id)
);

create table if not exists books (
    id bigint auto_increment,
    name varchar2(255),
    author_id bigint,
    genre_id bigint,
    primary key(id),
    foreign key(author_id) references authors(id),
    foreign key(genre_id) references genre(id)
);