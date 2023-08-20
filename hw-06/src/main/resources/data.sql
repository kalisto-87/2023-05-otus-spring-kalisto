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
values(1, 2);

insert into genre_books(genre_id, book_id)
values(2, 2);