insert into authors (name)
values('Jack London');
insert into authors (name)
values('Gustave Flaubert');
insert into authors (name)
values('James Joyce');
insert into authors (name)
values('Emile Zola');

insert into genre(name) values('novel');
insert into genre(name) values('science fiction');
insert into genre(name) values('adventure fiction');

insert into books(id, name)
values(nextval('book_seq'), 'Ulysses');
insert into books(id, name)
values(nextval('book_seq'), 'The Star Rover');
insert into books(id, name)
values(nextval('book_seq'), 'Martin Iden');
insert into books(id, name)
values(nextval('book_seq'), 'Madame Bovary');

insert into author_books(author_id, book_id)
values(3, 1);
insert into author_books(author_id, book_id)
values(1, 2);
insert into author_books(author_id, book_id)
values(1, 3);
insert into author_books(author_id, book_id)
values(2, 4);

insert into genre_books(genre_id, book_id)
values(1, 1);
insert into genre_books(genre_id, book_id)
values(2, 2);
insert into genre_books(genre_id, book_id)
values(1, 3);
insert into genre_books(genre_id, book_id)
values(1, 4);

insert into book_comments(book_id, text)
values(1, 'Comment_1');
insert into book_comments(book_id, text)
values(2, 'Comment_2');
insert into book_comments(book_id, text)
values(2, 'Comment_3');

insert into  users (username, password, role)
values('user', 'user', 'USER');

insert into  users (username, password, role)
values('admin', 'admin', 'ADMIN');