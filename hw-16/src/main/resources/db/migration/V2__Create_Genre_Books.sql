drop table if exists genre_books;
create table genre_books (
        book_id bigint not null,
        genre_id bigint not null
);