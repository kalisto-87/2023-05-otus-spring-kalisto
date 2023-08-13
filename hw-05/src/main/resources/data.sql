insert into authors (name)
values('Jack London');
insert into authors (name)
values('Gustave Flaubert');
insert into authors (name)
values('James Joyce');

insert into genre(name) values('novel');
insert into genre(name) values('science fiction');

insert into books(name, author_id, genre_id)
values('Ulysses', 3, 1);
insert into books(name, author_id, genre_id)
values('The Star Rover', 1, 2);
insert into books(name, author_id, genre_id)
values('Martin Iden', 1, 1);
insert into books(name, author_id, genre_id)
values('Madame Bovary', 2, 1);