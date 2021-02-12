USE `library`;
INSERT INTO `users` (`name`, `surname`, `date_of_birth`, `date_of_registration`, `role`, `login`, `password`) VALUES
('Dima', 'Germanovich', '1979-12-10', '2020-12-10', 'ADMIN', 'admin', '4de35585e43001e7436de75dae44b67f'),
('Oleg', 'Donaldson', '1978-08-08', '2020-12-10', 'LIBRARIAN', 'Oleg455', '4de35585e43001e7436de75dae44b67f'),
('Evie', 'Bishop', '1999-11-11', '2020-12-10', 'USER', 'Evie123', '4de35585e43001e7436de75dae44b67f'),
('Jacob', 'Carey', '1992-10-08', '2020-12-10', 'USER', 'Jacob111', '4de35585e43001e7436de75dae44b67f');

INSERT INTO `books` (`title`, `author_name`, `date_of_creation`, `quantity`) VALUES
('The Master and Margarita', 'Mikhail Bulgakov', '1971-02-07 00:00:00', '4'),
('1984', 'George Orwell', '1978-01-01 00:00:00', '5'),
('Three companions', 'Erich Remark', '1978-01-01 00:00:00', '5'),
('Shantaram', 'Gregory Roberts', '2003-01-01 00:00:00', '4'),
('Anna Karenina', 'Lev Tolstoy', '1981-01-01 00:00:00', '5'),
('JAVA', 'Paulo Coelho', '2010-01-01 00:00:00', '2');

INSERT INTO `book_to_user` (`book_id`, `user_id`, `start_date`, `end_date`, `type`, `status`) VALUES
('1', '3', '2020-09-20 00:00:00', NULL, 'SUBSCRIPTION', 'NEW'),
('2', '4', '2020-09-20 00:00:00', '2020-10-01 00:00:00', 'SUBSCRIPTION', 'CLOSED'),
('2', '3', '2020-09-20 00:00:00', NULL, 'READING_ROOM', 'NEW'),
('3', '3', '2020-10-20 00:00:00', NULL, 'SUBSCRIPTION', 'IN_PROGRESS'),
('4', '4', '2020-10-20 00:00:00', NULL, 'READING_ROOM', 'IN_PROGRESS');
COMMIT;