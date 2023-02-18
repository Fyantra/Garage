INSERT INTO Genre(idgenre,genre) VALUES (1,'homme'),
(2,'Femme');

INSERT INTO Specialite (specialite, salaire) VALUES 
('Mécanicien', 45000),
('Carrossier', 35000),
('Peintre', 30000),
('Electricien', 40000),
('Tôlier', 32000),
('Mécanicien de maintenance', 42000),
('Mécanicien de moteur', 48000),
('Mécanicien de transmission', 46000),
('Mécanicien de climatisation', 35000),
('Mécanicien de freins', 38000),
('Mécanicien de suspension', 39000),
('Mécanicien de direction', 37000),
('Mécanicien de démarrage', 35000),
('Mécanicien de refroidissement', 40000),
('Mécanicien de carrosserie', 33000),
('Mécanicien de transmission automatique', 47000),
('Mécanicien de transmission manuelle', 45000),
('Mécanicien de moteur diesel', 49000),
('Mécanicien de moteur à essence', 47000),
('Mécanicien de climatisation automobile', 34000),
('Mécanicien de freins ABS', 39000),
('Mécanicien de suspension pneumatique', 40000),
('Mécanicien de direction assistée', 38000),
('Mécanicien de démarrage électrique', 36000),
('Mécanicien de refroidissement liquide', 41000),
('Mécanicien de carrosserie légère', 32000),
('Mécanicien de transmission à variation continue', 48000),
('Mécanicien de transmission à variation gérée', 46000),
('Mécanicien de moteur hybride', 50000),
('Mécanicien de moteur électrique', 45000);


INSERT INTO Reparation (reparation, prix) VALUES 
('Remplacement des freins', 225000.0),
('Remplacement des pneus', 300000.0),
('Remplacement de la batterie', 150000.0),
('Vidange d''huile', 75000.0),
('Remplacement des plaquettes de freins', 112500.0),
('Remplacement des filtres', 60000.0),
('Remplacement des amortisseurs', 375000.0),
('Remplacement des disques de frein', 180000.0),
('Remplacement des balais d''essuie-glace', 30000.0),
('Remplacement des phares', 112500.0),
('Remplacement des bougies d''allumage', 45000.0),
('Remplacement des courroies', 75000.0),
('Remplacement des rotules', 120000.0),
('Remplacement des silent-blocs', 45000.0),
('Remplacement des cardans', 225000.0),
('Remplacement des roulements', 75000.0),
('Remplacement des joints', 60000.0),
('Remplacement des rotules de suspension', 120000.0),
('Remplacement des silent-blocs de suspension', 45000.0);




INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Dupont', 'Jean', '12 rue de la paix', '0612345678', 1, '1995-03-20');
INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Martin', 'Sophie', '34 avenue des Champs-Elysées', '0623456789', 2, '1997-05-10');
INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Leblanc', 'Emilie', '56 rue de Rivoli', '0634567890', 2, '1999-07-15');
INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Dupont', 'Jacques', '12 rue de la paix', '0612345678', 1, '1995-03-20');
INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Martin', 'Laure', '34 avenue des Champs-Elysées', '0623456789', 2, '1997-05-10');
INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Leblanc', 'Nathalie', '56 rue de Rivoli', '0634567890', 2, '1999-07-15');
INSERT INTO Employer (nom, prenom, addresse, contact, idGenre, datedenaissance) VALUES ('Durand', 'Michel', '78 avenue des Ternes', '0645678901', 1, '2001-09-20');

Insert into SpecialiteRequise(idSpecialite,idReparation,dure,nombre) VALUES ('SPE1', 'REP1', 1, 2);
Insert into SpecialiteRequise(idSpecialite,idReparation,dure,nombre) VALUES ('SPE1', 'REP2', 3, 2);
Insert into SpecialiteRequise(idSpecialite,idReparation,dure,nombre) VALUES ('SPE1', 'REP3', 4, 1);
Insert into SpecialiteRequise(idSpecialite,idReparation,dure,nombre) VALUES ('SPE2', 'REP4', 2, 3);
Insert into SpecialiteRequise(idSpecialite,idReparation,dure,nombre) VALUES ('SPE2', 'REP5', 1, 2);
Insert into SpecialiteRequise(idSpecialite,idReparation,dure,nombre) VALUES ('SPE2', 'REP6', 3, 1);