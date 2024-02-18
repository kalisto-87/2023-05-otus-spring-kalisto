drop table if exists author_books;
create table author_books (
        author_id bigint not null,
        book_id bigint not null
);