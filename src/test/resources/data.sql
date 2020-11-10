INSERT INTO employee (id, lastname, firstname, birthdate, street, zipcode, city, country, email, phone, position, password) VALUES
(157314099170601, 'Stark', 'Tony', '1978-05-03', '9 rue du chene germain', '22700', 'Lannion', 'France', 'tony.stark@marvel.com', '+33612482274', 'Responsable location', '$2a$10$TCdrvG6zWUwrryQ.7Id6Qe9lJCWXaykTFH1vfRyrSRkmF5KK0DoCa'),
(157314099170602, 'Odinson', 'Thor', '1945-05-03', '5 avenue Asgardian ', '22700', 'Lannion', 'France', 'thor@marvel.com', '+33612482274', 'Gestionnaire commercial', '$2a$10$i/8ECxz8OTwlvVxHwKzEaOq7kWYy/OWNDVo8JcOQsb5yk.Je.9gFW'),
(157314099170603, 'Jonathan', 'Henry', '1958-05-03', 'rue', '0000', 'Nebraska', 'USA', 'antman@marvel.com', '+33612482274', 'Gestionnaire commercial', '$2a$10$P0OMU1.5XRSCab/ovZ11JuhBdPsYLaL54CrV9rqVGAAIC23lWvlo2'),
(157314099170604, 'Banner', 'Bruce', '1975-05-03', '9 rue du labo', '58695', 'Secret', 'USA', 'hulk@marvel.com', '+33612482274', 'Gestionnaire technique', '$2a$10$3RqwnPGFwpiBfp.0XTWy5ubcoqWlNugLtDlicYmFG4PFhHvMyZwZS'),
(157314099170605, 'Rogers', 'Steve', '1985-05-03', '0 rue du pole nord', '0000', 'PoleNord', 'Danemark', 'captain@marvel.com', '+33612482274', 'Gestionnaire technique', '$2a$10$DEdu0Y32xX3vOqpJZnQPVOzC/4FhpcgNzQmhVzDtyAJBUOe6ajYKe'),
(157314099170606, 'Barton', 'Clinton', '1947-05-03', '5 branche', '124578', 'Arbre', 'Terre', 'hawkeye@marvel.com', '+33612482274', 'Gestionnaire client', '$2a$10$YP8uoFzmus3jmKBXssoQUuunhmzsLbrTqm0UlLMQS0J/40hb9RIEm'),
(157314099170607, 'Challa', 'T', '1987-05-03', '1 tour', '1234', 'Wakanda', 'Africa', 'blackpanther@marvel.com', '+33612482274', 'Gestionnaire client', '$2a$10$PnNN.7lBHi7RBzyb8F2k6e4A8LIofAy5fEa.lfv/a5ALnkyi6aaFa'),
(157314099170608, 'Romanoff', 'Natasha', '1954-05-03', '1 rue du fantome', '4569', 'Moscou', 'Russie', 'blackwidow@marvel.com', '+33612482274', 'Collaborateur', '$2a$10$yaAMX.0ubVOhUarMTTlWGOM2k3dyEseqj4VYzQuZuRRqrxkHK4EUC'),
(157314099170609, 'Rambeau', 'Monica', '1963-05-03', 'partout', '00', 'dans la', 'Galaxie', 'captainmarvel@marvel.com', '+33612482274', 'Collaborateur', '$2a$10$mPKK/CRsEA9Wz5K2mw3JGOJVysJV8CM0QSbm6ZLKZWKjhk6oxjOzu'),
(157314099170610, 'Parker', 'Peter', '1969-05-03', 'quelque part', '000', 'New York', 'USA', 'spiderman@marvel.com', '+33612482274', 'Collaborateur', '$2a$10$9xb3/9UAmHG4pbUI8ipsH.cx912tRPr8ybg32Ocr538tURvHoZ7gC');

INSERT INTO client (id, birthdate, city, country, email, firstname, lastname, phone, street, zipcode) VALUES
(9143686792, '1998-12-06', 'Lannion', 'France', 'tim.bradstreet@gmail.com', 'Tim', 'Bradstreet', '+33938333613', '6 Rue des ARTILLEURS', '35288'),
(9143686793, '2001-08-07', 'New-York', 'Etats-Unis', 'garth.ennis@hotmail.eu', 'Garth', 'Ennis', '+33425464119', '5 Rue des CAMELIAS', '56925'),
(9143686794, '1956-01-14', 'Moscou', 'Russie', 'michael.straczynski@orange.ru', 'Michael', 'Straczynski', '+33786106413', '11 Rue du quai SOLIDOR', '92541');

INSERT INTO car (id, brand, maximum_speed, model, nb_seats, rent_price_per_day, state, horse_power, km) VALUES
(42, 'acura', 185, 'ilx', 7, 318.2, 'Etat excellent', 201, 150),
(43,'ferrari',300,'roma',2,600.5,'Etat bon',500,20000),
(44,'renault',160,'clio',5,100,'Etat excellent',100,15000),
(45,'mazda',200,'mx5',2,160.4,'Etat très bien',170,5000),
(46,'toyota',160,'yaris',5,70,'Etat passable',90,50000);

INSERT INTO motorbike (id, brand, maximum_speed, model, nb_seats, rent_price_per_day, state, horse_power, km) VALUES
(782,'kawasaki',190,'vulcan s',2,100.5,'Etat excellent',60,1200),
(783,'suzuki','250','cb100rr',1,350.8,'Etat bon',120,5000),
(784,'moto guzzi','160','v7III',2,200.12,'Etat très bien',70,2000),
(785,'yahma','195','mt-07',2,160.49,'Etat passable',67,15000);

INSERT INTO plane (id, brand, maximum_speed, model, nb_seats, rent_price_per_day, state, nb_engines, nb_hours) VALUES
(12679, 'robin',300,'dr400',4,900,'Etat bon',1,1000),
(12680, 'cessna',350,'172sp',4,1500,'Etat excellent',1,50),
(12681, 'beechraft',400,'baron g58',6,2500,'Etat passable',2,50000),
(12682, 'piper',370,'seneca',4,200,'Etat très bien',2,12000);

INSERT INTO booking (id, discount, end_date, expected_number_km, expected_price, is_discount, start_date, status, id_client, id_vehicle) VALUES
(148632579, 200.5, '2020-11-20', 2500, 502.5, true, '2020-11-15', 'Réservation prête', 9143686792, 782),
(148632580, 0, '2020-12-12', 500, 300, false, '2020-12-15', 'Réservation prête', 9143686794, 44);
