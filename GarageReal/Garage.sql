        DROP TABLE QuantiteCommande CASCADE ;
        DROP TABLE Commande CASCADE ;
        DROP TABLE SpecialiteRequise CASCADE ;
        DROP TABLE SpecialiteEmployer CASCADE ;
        DROP TABLE employer CASCADE ;
        DROP TABLE reparation CASCADE ;
        DROP TABLE specialite CASCADE ;
        DROP TABLE genre CASCADE ;
        drop TABLE facturation CASCADE;
        drop TABLE client CASCADE;
        drop TABLE remise CASCADE;


        DROP SEQUENCE Commandeseq CASCADE;
        DROP SEQUENCE Specialiterequiseseq CASCADE;
        DROP SEQUENCE Employerseq CASCADE;
        DROP SEQUENCE Reparationseq CASCADE;
        DROP SEQUENCE specialiteSeq CASCADE;
        DROP SEQUENCE factureSeq CASCADE;
        drop SEQUENCE clientseq CASCADE;

        drop VIEW ssrs CASCADE;
        drop VIEW totalsalaire CASCADE;
        drop VIEW totalService CASCADE;
        drop VIEW prixTotal CASCADE;
        drop VIEW totalcommande CASCADE;
        drop view prixPaye CASCADE;
        drop view prixRestant CASCADE;
        drop view facturation CASCADE;
        drop view remiseDetail CASCADE;

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
            nombre INTEGER NOT NULL,
            pourcentage double precision NOT NULL
        );

        CREATE SEQUENCE clientseq;
        CREATE TABLE client(id VARCHAR(255) NOT NULL DEFAULT 'CL'||nextval('clientseq') PRIMARY KEY,
                    mail VARCHAR(255),
                    mdp VARCHAR(255),
                    datedenaissance date not null,
                    dateCreation date not null DEFAULT current_date
                    );

        -------------Commande----------
        CREATE SEQUENCE Commandeseq;
        CREATE TABLE Commande(
            idCommande VARCHAR(100) NOT NULL DEFAULT 'CMD'||nextval('Commandeseq') PRIMARY KEY,
            idClient VARCHAR(100) NOT NULL REFERENCES  client(id),
            datecommade DATE DEFAULT current_date
        );

        CREATE SEQUENCE quantitecommandeseq;
        CREATE TABLE QuantiteCommande(
            idQuantiteCommande VARCHAR(100) not null default 'QCM'||nextval('quantitecommandeseq') PRIMARY KEY,
            idCommande VARCHAR(100) NOT NULL REFERENCES Commande(idCommande),
            idReparation VARCHAR(100) REFERENCES Reparation(idReparation) 
        );

        --CREATE SEQUENCE factureSeq;
        CREATE TABLE paiment(
            idQuantiteCommande VARCHAR(100) NOT NULL REFERENCES QuantiteCommande(idQuantiteCommande),
            payee boolean not null default false
        );

        CREATE TABLE remiseQuantitecommande(
            idQuantiteCommande VARCHAR(100) NOT NULL REFERENCES QuantiteCommande(idQuantiteCommande),
            pourcentageRemisequantitecommande double precision NOT NULL
        );

        CREATE TABLE remiseCommande(
            idCommande VARCHAR(100) NOT NULL REFERENCES QuantiteCommande(idQuantiteCommande),
            pourcentageRemisecommande double precision NOT NULL
        );

CREATE OR REPLACE VIEW ssrs as 
(
    SELECT 
        sr.idreparation,
        reparation.reparation as materiel,
        sr.dure as dure,
        sr.nombre as nombre,
        specialite.salaire as salaire,
        reparation.prix as prix,
        sr.pourcentage as pourcentage
    
    FROM SpecialiteRequise as sr 
        JOIN Specialite ON sr.idSpecialite=Specialite.idSpecialite 
        JOIN reparation ON reparation.idreparation=sr.idReparation
);

CREATE OR REPLACE VIEW totalsalaire as SELECT idReparation,materiel,sum(ssrs.dure*ssrs.salaire*ssrs.nombre) as coutReparation,pourcentage FROM ssrs group by idReparation,materiel,pourcentage;
CREATE OR REPLACE VIEW totalService as SELECT idReparation,materiel,sum(ssrs.prix) as prixMateriel,pourcentage FROM ssrs group by idReparation,materiel,pourcentage;

CREATE OR REPLACE VIEW prixTotal as
( 
    SELECT 
        tss.idreparation,   
        tss.materiel,
        ts.coutreparation+tss.prixMateriel+tss.pourcentage*100/(tss.prixMateriel+ts.coutReparation) as cout 
    FROM totalsalaire as ts 
        JOIN totalService as tss on ts.idReparation=tss.idreparation
);

--total prix commande
create view totalcommande as (select q.idCommande,sum(p.cout) as coutTotal from quantiteCommande q join prixTotal p on q.idReparation=p.idReparation group by q.idCommande);  

--prix paye 
create view prixPaye as select sum(p.cout) as coutPaye from prixTotal p join reparation r on r.idReparation=p.idReparation group by q.idCommande;

--prix restant  
create view prixRestant as select tc.idCommande idCommande,tc.coutTotal-pp.coutPaye as coutRestant from totalCommande tc join prixPaye pp on tc.idCommande=pp.idCommande; 

create view facturation as select q.idquantitecommande as idquantitecommande, rep.idReparation, rep.reparation, rep.prix ,(select cl.mail as nomClient from client cl join commande c on cl.id = c.idClient) from reparation rep join QuantiteCommande q on rep.idReparation=q.idReparation ;

--create view commandeClient as (select cl.mail as nomClient from client cl join commande c on cl.id = c.idClient);

--commande avec remise
/*create view commandeRemise as (select 
    SELECT 
        r.reparation,   
        c.idCommande,
        rem.pourcentageRemise*r.+tss.pourcentage*100/(tss.prixMateriel+ts.coutReparation) as cout 
    FROM totalsalaire as ts 
        JOIN totalService as tss on ts.idReparation=tss.idreparation
);*/

--deja creee
create view commRemcom as (select c.idCommande,c.idClient,c.datecommade,r.pourcentageRemisecommande from commande  c join remiseCommande  r on c.idCommande = r.idCommande); 
create view qcRep as (select q.idquantitecommande,q.idCommande,q.idReparation,r.reparation,r.prix from quantiteCommande q join reparation r on q.idReparation = r.idReparation);
create view totalprixcommande as (select sum(prix), idCommande from qcRep group by idCommande);

**create view remiseparcommande as (select cR.idCommande,cR.idClient,cR.datecommade,cR.pourcentageRemisecommande,tc.sum from commRemcom  cR join totalprixcommande  tc on cR.idCommande = tc.idCommande);
--deja cree

--deja cree
=>create view prixremiseCommande as select (sum-(sum*pourcentageRemisecommande/100)) as prixremiseCommande from remiseparcommande;

**create view remiseparquantitecommande as select q.idquantitecommande,q.idreparation,q.reparation,q.prix,r.pourcentageRemisequantitecommande from qcRep q join remiseQuantitecommande r on q.idquantitecommande = r.idQuantiteCommande;
 
=>create view prixremisequantiteCommande as select (prix-(prix* pourcentageremisequantitecommande/100)) as prixremisequantiteCommande from remiseparquantitecommande;
--deja cree