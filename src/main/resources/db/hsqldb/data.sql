INSERT INTO vets VALUES (1, 'James', 'Carter');
INSERT INTO vets VALUES (2, 'Helen', 'Leary');
INSERT INTO vets VALUES (3, 'Linda', 'Douglas');
INSERT INTO vets VALUES (4, 'Rafael', 'Ortega');
INSERT INTO vets VALUES (5, 'Henry', 'Stevens');
INSERT INTO vets VALUES (6, 'Sharon', 'Jenkins');

INSERT INTO specialties VALUES (1, 'radiología');
INSERT INTO specialties VALUES (2, 'cirugía');
INSERT INTO specialties VALUES (3, 'odontología');

INSERT INTO vet_specialties VALUES (2, 1);
INSERT INTO vet_specialties VALUES (3, 2);
INSERT INTO vet_specialties VALUES (3, 3);
INSERT INTO vet_specialties VALUES (4, 2);
INSERT INTO vet_specialties VALUES (5, 1);

INSERT INTO types VALUES (1, 'gato');
INSERT INTO types VALUES (2, 'perro');
INSERT INTO types VALUES (3, 'lagarto');
INSERT INTO types VALUES (4, 'serpiente');
INSERT INTO types VALUES (5, 'pajaro');
INSERT INTO types VALUES (6, 'hamster');

INSERT INTO owners VALUES (1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', '6085551023');
INSERT INTO owners VALUES (2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', '6085551749');
INSERT INTO owners VALUES (3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', '6085558763');
INSERT INTO owners VALUES (4, 'Harold', 'Davis', '563 Friendly St.', 'Windsor', '6085553198');
INSERT INTO owners VALUES (5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', '6085552765');
INSERT INTO owners VALUES (6, 'Jean', 'Coleman', '105 N. Lake St.', 'Monona', '6085552654');
INSERT INTO owners VALUES (7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', '6085555387');
INSERT INTO owners VALUES (8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', '6085557683');
INSERT INTO owners VALUES (9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', '6085559435');
INSERT INTO owners VALUES (10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', '6085555487');

INSERT INTO pets VALUES (1, 'Leo', '2010-09-07', 1, 1);
INSERT INTO pets VALUES (2, 'Basil', '2012-08-06', 6, 2);
INSERT INTO pets VALUES (3, 'Rosy', '2011-04-17', 2, 3);
INSERT INTO pets VALUES (4, 'Jewel', '2010-03-07', 2, 3);
INSERT INTO pets VALUES (5, 'Iggy', '2010-11-30', 3, 4);
INSERT INTO pets VALUES (6, 'George', '2010-01-20', 4, 5);
INSERT INTO pets VALUES (7, 'Samantha', '2012-09-04', 1, 6);
INSERT INTO pets VALUES (8, 'Max', '2012-09-04', 1, 6);
INSERT INTO pets VALUES (9, 'Lucky', '2011-08-06', 5, 7);
INSERT INTO pets VALUES (10, 'Mulligan', '2007-02-24', 2, 8);
INSERT INTO pets VALUES (11, 'Freddy', '2010-03-09', 5, 9);
INSERT INTO pets VALUES (12, 'Lucky', '2010-06-24', 2, 10);
INSERT INTO pets VALUES (13, 'Sly', '2012-06-08', 1, 10);

INSERT INTO visits VALUES (1, 7, '2013-01-01', 'vacuna antirrábica');
INSERT INTO visits VALUES (2, 8, '2013-01-02', 'vacuna antirrábica');
INSERT INTO visits VALUES (3, 8, '2013-01-03', 'castrado');
INSERT INTO visits VALUES (4, 7, '2013-01-04', 'esterilizada');

INSERT INTO pet_hotel VALUES (1, 1, 'info1', '2010-03-04 10:00:00', '2010-03-04 10:30:00');
INSERT INTO pet_hotel VALUES (2, 1, 'info2', '2010-03-04 22:00:00', '2010-03-04 22:30:00');

INSERT INTO causes(id,name,description,budget_target,organization,closed) VALUES (1, 'Dogs cause', 'Cause related to help homeless dogs', 2000, 'Arca de Noé', false);
INSERT INTO causes(id,name,description,budget_target,organization,closed) VALUES (2, 'Cats cause', 'Cause related to help homeless cats', 1000, 'Refugio La Candela', true);

INSERT INTO donations(id,donation_date,amount,client_id,cause_id) VALUES (1, '2019-03-04', 500, 1, 2);
INSERT INTO donations(id,donation_date,amount,client_id,cause_id) VALUES (2, '2019-03-14', 500, 2, 2);