    create sequence if not exists book_seq start with 1 increment by 1;

    create table if not exists author_books (
        author_id bigint not null,
        book_id bigint not null
    );

    create table if not exists authors (
        id bigint generated by default as identity,
        name varchar(255) not null,
        primary key (id)
    );

    create table if not exists book_comments (
        book_id bigint,
        id bigint generated by default as identity,
        text varchar(255),
        primary key (id)
    );

    create table if not exists books (
        id bigint not null,
        name varchar(255) not null,
        primary key (id)
    );

    create table if not exists genre (
        id bigint generated by default as identity,
        name varchar(255) not null,
        primary key (id)
    );

    create table if not exists users (
        id bigint generated by default as identity,
        username varchar(255) not null,
        password varchar(255) not null,
        role varchar(255) not null,
        primary key (id)
    );

    create table if not exists genre_books (
        book_id bigint not null,
        genre_id bigint not null
    );

    alter table if exists author_books
       add constraint if not exists FK_author_books_author_id
       foreign key (author_id)
       references authors;

    alter table if exists author_books
       add constraint if not exists FK_author_books_book_id
       foreign key (book_id)
       references books;

    alter table if exists book_comments
       add constraint if not exists FK_book_comments_book_id
       foreign key (book_id)
       references books;

    alter table if exists genre_books
       add constraint if not exists FK_genre_books_genre_id
       foreign key (genre_id)
       references genre;

    alter table if exists genre_books
       add constraint if not exists FK_genre_books_book_id
       foreign key (book_id)
       references books;