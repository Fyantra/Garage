        DROP TABLE QuantiteCommande CASCADE ;
        DROP TABLE Commande CASCADE ;
        DROP TABLE SpecialiteRequise CASCADE ;
        DROP TABLE SpecialiteEmployer CASCADE ;
        DROP TABLE employer CASCADE ;
        DROP TABLE reparation CASCADE ;
        DROP TABLE specialite CASCADE ;
        DROP TABLE genre CASCADE ;

        DROP SEQUENCE Commandeseq CASCADE;
        DROP SEQUENCE Specialiterequiseseq CASCADE;
        DROP SEQUENCE Employerseq CASCADE;
        DROP SEQUENCE Reparationseq CASCADE;
        DROP SEQUENCE specialiteSeq CASCADE;

        drop VIEW ssrs;
        drop VIEW totalsalaire;
        drop VIEW totalService;
        drop VIEW prixTotal;

        Create database garage;
        \c garage;
        -------Donner deja existant
        CREATE TABLE Genre(
            idGenre INTEGER NOT NULL PRIMARY KEY,
            genre VARCHAR(50) NOT NULL
        );
        CREATE SEQUENCE Specialiteseq;
        CREATE TABLE Specialite(
            idSpecialite VARCHAR(100) NOT NULL DEFAULT 'SPE'||nextval('Specialiteseq') PRIMARY KEY,
            specialite VARCHAR(100) NOT NULL,
            salaire double precision
        );
        CREATE SEQUENCE Reparationseq;
        CREATE TABLE Reparation(
            idReparation VARCHAR(100) NOT NULL DEFAULT 'REP'||nextval('Reparationseq') PRIMARY KEY,
            reparation VARCHAR(100) NOT NULL,
            prix double precision NOT NULL

        );

        ---------------Donnee a inserer -------------
        CREATE SEQUENCE Employerseq;
        CREATE TABLE Employer(
            idEmployer VARCHAR(100) NOT NULL DEFAULT 'EMP'||nextval('Employerseq') PRIMARY KEY,
            nom VARCHAR(100) NOT NULL,
            prenom VARCHAR(100) NOT NULL,
            addresse VARCHAR(100) NOT NULL,
            contact VARCHAR(100) NOT NULL,
            idGenre INTEGER NOT NULL REFERENCES Genre(idGenre),
            datedenaissance date not null
        );

        -----------------------------------------------------------
       
        ------------------------------------------------------



        CREATE TABLE SpecialiteEmployer(
            idEmployer VARCHAR(100) NOT NULL REFERENCES Employer(idEmployer),
            idSpecialite VARCHAR(100) NOT NULL REFERENCES Specialite(idSpecialite)
        );


        CREATE SEQUENCE Specialiterequiseseq;

        CREATE TABLE SpecialiteRequise(
            idSpecialiteRequise VARCHAR(100) NOT NULL DEFAULT 'SPE'||nextval('Specialiterequiseseq') PRIMARY KEY,
            idSpecialite VARCHAR(100) NOT NULL REFERENCES  Specialite(idSpecialite),
            idReparation VARCHAR(100) NOT NULL REFERENCES Reparation(idReparation),
            dure  double precision NOT NULL,
            nombre INTEGER NOT NULL
        );

        -------------Commande----------
        CREATE SEQUENCE Commandeseq;
        CREATE TABLE Commande(
            idCommande VARCHAR(100) NOT NULL DEFAULT 'CMD'||nextval('Commandeseq') PRIMARY KEY,  
            datecommade DATE DEFAULT current_date
        );

        CREATE TABLE QuantiteCommande(
            idCommande VARCHAR(100) NOT NULL REFERENCES Commande(idCommande),
            idReparation VARCHAR(100) REFERENCES Reparation(idReparation) 
        );

CREATE OR REPLACE VIEW ssrs as 
(
    SELECT 
        sr.idreparation,
        reparation.reparation as materiel,
        sr.dure as dure,
        sr.nombre as nombre,
        specialite.salaire as salaire,
        reparation.prix as prix
    
    FROM SpecialiteRequise as sr 
        JOIN Specialite ON sr.idSpecialite=Specialite.idSpecialite 
        JOIN reparation ON reparation.idreparation=sr.idReparation
);

CREATE OR REPLACE VIEW totalsalaire as SELECT idReparation,materiel,sum(ssrs.dure*ssrs.salaire*ssrs.nombre) as coutReparation FROM ssrs group by idReparation,materiel;
CREATE OR REPLACE VIEW totalService as SELECT idReparation,materiel,sum(ssrs.prix) as prixMateriel FROM ssrs group by idReparation,materiel;

CREATE OR REPLACE VIEW prixTotal as
( 
    SELECT 
        tss.idreparation,   
        tss.materiel,
        ts.coutreparation+tss.prixMateriel+20*100/(tss.prixMateriel+ts.coutReparation) as cout 
    FROM totalsalaire as ts 
        JOIN totalService as tss on ts.idReparation=tss.idreparation
);