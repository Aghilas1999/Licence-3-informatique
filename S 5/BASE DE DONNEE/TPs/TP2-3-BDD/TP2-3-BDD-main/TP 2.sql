
DROP TABLE ETUDIANTS;
DROP TABLE ENSEIGNANTS;
DROP TABLE SALLES;
DROP TABLE EPREUVES;
DROP TABLE INSCRIPTIONS;
DROP TABLE HORAIRES;
DROP TABLE OCCUPATIONS;
DROP TABLE SURVEILLANCES;


CREATE TABLE ETUDIANTS (
    NumEtu NUMERIC(9),
    NomEtu VARCHAR(15),
    PrenomEtu VARCHAR(15),
    PRIMARY KEY (NumETU)
);


CREATE TABLE ENSEIGNANTS (
    NumEns NUMERIC(9),
    NomEns VARCHAR(15),
    PrenomEns  VARCHAR(15),
    PRIMARY KEY (NumEns)
);

CREATE TABLE SALLES (
    NumSal NUMERIC(9),
    NomEns VARCHAR(15) NOT NUll,
    CapacitSal NUMERIC(5) NOT NULL,
    PRIMARY KEY (NumSal)
);

CREATE TABLE EPREUVES (
    NumEpr NUMERIC(9),
    NomEpr VARCHAR(25),
    DureeEpr  INTERVAL DAY TO SECOND (0) NOT NULL,
    --Quota  integer not null,
    primary key (NumEpr)  
);

CREATE TABLE INSCRIPTIONS (
    NumEtu NUMERIC(9) REFERENCES  ETUDIANTS(NumEtu),
    NumEpr NUMERIC(9) REFERENCES EPREUVES(NumEpr),
    PRIMARY KEY (NumEtu,NumEpr)
);

CREATE TABLE HORAIRES (
    NumEpr NUMERIC(9) REFERENCES EPREUVES,
    DateHeureDebut TIMESTAMP(0),
    PRIMARY KEY (NumEpr)
);
SELECT * FROM HORAIRES;

CREATE TABLE OCCUPATIONS (
    NumSal INTEGER REFERENCES SALLES(NumSal),
    NumEpr INTEGER REFERENCES EPREUVES(NumEpr),
    NbPlacesOcc INTEGER NOT NULL,
    PRIMARY KEY (NumSal,NumEpr)
);
CREATE TABLE SURVEILLANCES (
    NumEns NUMERIC(9) REFERENCES ENSEIGNANTS,
    DateHeureDebut TIMESTAMP(0),
    NumSal NUMERIC(9) REFERENCES SALLES,
    PRIMARY KEY (NumEns , DateHeureDebut)
);
--Insertion pour la table Etudiants.

INSERT INTO ETUDIANTS  VALUES (21219997 , 'AGHILAS','SMAIL');
INSERT INTO ETUDIANTS  VALUES (21219998 , 'WALID','ADDOUCHE');
INSERT INTO ETUDIANTS  VALUES (21219999 , 'ADNANE','AFIFI');
INSERT INTO ETUDIANTS  VALUES (21220000 , 'MOETEZ','JLIDI');
INSERT INTO ETUDIANTS  VALUES (21220001 , 'BOUELEM','SI LARBI');




--Insertion pour la table Enseignants.
INSERT INTO ENSEIGNANTS  VALUES (0,'COUETOUX','BASILE');
INSERT INTO ENSEIGNANTS  VALUES (1,'SABATIER','CLAUDE');
INSERT INTO ENSEIGNANTS  VALUES (2,'PY','MATTHIEU');
INSERT INTO ENSEIGNANTS  VALUES (3,'CEPOI','VICTOR');



--Insertion pour la table Salles.
INSERT INTO SALLES  VALUES(0,'T1',25);
INSERT INTO SALLES  VALUES(1,'T2',15);
INSERT INTO SALLES  VALUES(2,'T3',30);
INSERT INTO SALLES  VALUES(3,'T4',20);



--Insertion pour la table Epreuve.
INSERT INTO  EPREUVES  VALUES ( 0 ,'algo',INTERVAL '2' HOUR);
INSERT INTO  EPREUVES  VALUES ( 1 ,'PC'  ,INTERVAL '2' HOUR);
INSERT INTO  EPREUVES  VALUES ( 2 ,'base de donnee' ,INTERVAL '1' HOUR);
INSERT INTO  EPREUVES  VALUES ( 3 ,'Logique',INTERVAL '2' HOUR);
INSERT INTO  EPREUVES (NumEpr,NomEpr,DureeEpr)  VALUES ( 4 ,'SYSTEM', INTERVAL '2' HOUR);

SELECT * FROM EPREUVES;
--I_ Gestion d'une colonne dérivée.
--1);
ALTER TABLE EPREUVES 
ADD Nb_Inscrits NUMERIC(38) ;




--Insertion pour la table Inscriptions.
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21219997 , 0);
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21219998 , 1);
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21219999 , 2);
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21220000 , 3);
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21220001 , 4);
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21220001 , 0);

SELECT * FROM INSCRIPTIONS;


--Insertion pour la table Horaires.
INSERT INTO HORAIRES (NumEpr,DateHeureDebut) VALUES (0,TIMESTAMP '2021-10-05 08:00:00');
INSERT INTO HORAIRES (NumEpr,DateHeureDebut) VALUES (1,TIMESTAMP '2021-10-05 10:00:00');
INSERT INTO HORAIRES (NumEpr,DateHeureDebut) VALUES (2,TIMESTAMP '2021-10-05 13:30:00');
INSERT INTO HORAIRES (NumEpr,DateHeureDebut) VALUES (3,TIMESTAMP '2021-10-06 08:00:00');



--Insertion pour la table Occupations.
INSERT INTO OCCUPATIONS (NumSal,NumEpr,NbPlacesOcc) VALUES (0,0,25);
INSERT INTO OCCUPATIONS (NumSal,NumEpr,NbPlacesOcc) VALUES (1,1,15);
INSERT INTO OCCUPATIONS (NumSal,NumEpr,NbPlacesOcc) VALUES (2,2,30);
INSERT INTO OCCUPATIONS (NumSal,NumEpr,NbPlacesOcc) VALUES (3,3,20);
 

--Insertion pour la table Surveillances.
INSERT INTO SURVEILLANCES (NumEns,DateHeureDebut,NumSal) VALUES (0,TIMESTAMP '2021-10-05 08:00:00', 0);
INSERT INTO SURVEILLANCES (NumEns,DateHeureDebut,NumSal) VALUES (1,TIMESTAMP '2021-10-05 10:00:00', 1);
INSERT INTO SURVEILLANCES (NumEns,DateHeureDebut,NumSal) VALUES (2,TIMESTAMP '2021-10-05 13:30:00', 2);
INSERT INTO SURVEILLANCES (NumEns,DateHeureDebut,NumSal) VALUES (3,TIMESTAMP '2021-10-06 08:00:00', 3);

SELECT * FROM ETUDIANTS;
SELECT * FROM ENSEIGNANTS;
SELECT * FROM SALLES;

SELECT * FROM SURVEILLANCES;
SELECT * FROM OCCUPATIONS;
SELECT * FROM HORAIRES;
SELECT * FROM INSCRIPTIONS;
SELECT * FROM EPREUVES;
--2)
-- L'instruction SQL qui permet de mettre a jour la valeur de NB_Inscrits
UPDATE EPREUVES 
    SET Nb_Inscrits  = 
        (SELECT COUNT(NumEpr) FROM EPREUVES WHERE EPREUVES.NumEpr =
        INSCRIPTIONS.NumEpr);

INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21220001 , 1);
SELECT * FROM EPREUVES;
--3)
DROP TRIGGER InitiliseNb_Inscrits;
CREATE OR REPLACE TRIGGER InitiliseNb_Inscrits
BEFORE INSERT ON EPREUVES
FOR EACH ROW 
BEGIN 
    :NEW.Nb_Inscrits := 0;
End;
/
--INSERT INTO  EPREUVES  VALUES ( 0 ,'algo',INTERVAL '2' HOUR);

SELECT * FROM EPREUVES;
--4)
DROP TRIGGER Update_NbInscrite;
CREATE OR REPLACE TRIGGER Update_NbInscrite
AFTER INSERT OR DELETE OR UPDATE ON INSCRIPTIONS
FOR EACH ROW 
BEGIN 
 
        UPDATE EPREUVES E
        SET Nb_Inscrits = (SELECT COUNT(NumEtu) FROM INSCRIPTIONS I
        WHERE E.NumEpr = I.NumEpr);
        
END;
/


--II- Rédiger les triggers qui expriement les contraint:

/* C1 
    Les épreuve ne doit pas se terminer après 20h.
*/
DROP TRIGGER C1_Horaires;
CREATE OR REPLACE TRIGGER C1_Horaires  
AFTER INSERT OR UPDATE of NumEpr , DateHeureDebut ON HORAIRES
FOR EACH ROW
DECLARE
    N INTEGER;
BEGIN
    SELECT 1 INTO N FROM EPREUVES, HORAIRES
    WHERE HORAIRES.NumEpr = EPREUVES.NumEpr AND TRUNC(HORAIRES.DateHeureDebut) +
    EPREUVES.DureeEpr > 20;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN raise_application_error(-20000, 'C1 error');
    END;
 /   


/* C2
    Deux épreuves qui ont des etudiants en commun ne doivent pas 
     avoir lieu en meme temps.
 
/* On v�rifie avant l'insertion d'une entr�e dans la table INSCRIPTIONS */
/*
CREATE TRIGGER C2_EPREUVE_ETUDIANT
    BEFORE INSERT ON HORAIRES 
      FOR EACH ROW
        DECLARE
          N BINARY_INTEGER;
          BEGIN
           SELECT 1 INTO N 
              FROM EPREUVES E1, EPREUVES E2, INSCRIPTIONS I1 ,
              INSCRIPTIONS I2 ,HORAIRES H1,HORAIRES H2 WHERE
              E1.NumEpr = H1.NumEpr AND
              H1.NumEpr = I1.NumEpr AND
              I1.NumEpr = E1.NumEpr AND
              E2.NumEpr = H2.NumEpr AND
              H2.NumEpr = I2.NumEpr AND
              I2.NumEpr = E2.NumEpr AND
              (H1.DateHeureDebut,H1.DateHeureDebut + E1.DureeEpr) OVERLAPS (H2.DateHeureDebut,H2.DateHeureDebut + E2.DureeEpr);


          RAISE too_many_rows;
          
          EXCEPTION 
          WHEN no_data_found THEN NULL;
          WHEN too_many_rows  THEN RAISE_APPLICATION_ERROR(-20000,'Des �tudiants sont deja en epreuve');
  END;

/*Jeux de test*/
--Marche
INSERT INTO HORAIRES (NumEpr,DateHeureDebut) VALUES (6,TIMESTAMP '2021-10-06 08:00:00');
--Ne MARCHE PAS
--INSERT INTO HORAIRES (NumEpr,DateHeureDebut) VALUES (6,TIMESTAMP '2021-10-06 08:10:00')
*/
--UN TRIGGER POUR LES INSCRIPTIONS.
DROP TRIGGER VERIFIER_INSCRIPTIONS_ETUDIANT;
CREATE TRIGGER VERIFIER_INSCRIPTIONS_ETUDIANT
  BEFORE UPDATE OR INSERT ON INSCRIPTIONS
    FOR EACH ROW
      DECLARE
        N BINARY_INTEGER;
        DureeEpr INTERVAL DAY TO SECOND(0);
        DateHeureDebutGet TIMESTAMP(0);
        BEGIN
        
        SELECT DureeEpr INTO DureeEpr
        FROM EPREUVES E
        WHERE E.NumEpr = : NEW.NumEpr;
        
        SELECT DateHeureDebut INTO DateHeureDebutGet
        FROM HORAIRES H
        WHERE H.NumEpr = : New.NumEpr;
        
        SELECT 1 INTO N 
        FROM EPREUVES E , INSCRIPTIONS I , HORAIRES H
        WHERE H.NumEpr = E.NumEpr AND
              E.NumEpr = I.NumEpr AND
              H.NumEpr = I.NumEpr AND
              (H.DateHeureDebut,H.DateHeureDebut + E.DureeEpr) OVERLAPS (DateHeureDebutGet,DateHeureDebutGet + DureeEpr);

        RAISE too_many_rows;

        EXCEPTION
          WHEN no_data_found THEN NULL;
          WHEN too_many_rows THEN RAISE_APPLICATION_ERROR(-10,'l etudiant est d?ja dans une ?preuve');
END;
/
--TEST
--Marche
DELETE FROM INSCRIPTIONS WHERE NumEpr = '3' AND NumEtu = '21219997';
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21219997,3);
--Marche pas
INSERT INTO INSCRIPTIONS (NumEtu,NumEpr) VALUES (21219997,0);
/*
C3
 Tout les epreuves qui ont lieu dans une meme salle au meme moment
 doivent commencer en meme temps.
 */

    -- En parte du sens contraire .
 CREATE TRIGGER C3_Epre_meme_Temps 
     AFTER INSERT OR UPDATE of NumSal, NumEpr ON OCCUPATIONS
        DECLARE 
            N INTEGER;
        BEGIN
            SELECT 1 INTO N FROM OCCUPATIONS O1, OCCUPATIONS O2 
            WHERE O1.NumSal = O2.NumSal AND O1.NumEpr <> O2.NumEpr
            AND (SELECT DateHeureDebut FROM HORAIRES WHERE NumEpr = O1.NumEpr)
            <>(SELECT DateHeureDebut FROM HORAIRES WHERE NumEpr = O2.NumEpr);

            EXCEPTION
                WHEN NO_DATA_FOUND then null;
                WHEN TOO_MANY_ROWS THEN raise_application_error(-20000, 
                'tout les epreuve qui ont lieu dans une meme salle au meme
                moment doivent commencer en meme temps') ;
            END;
             
 

    -- Faire un test sur le trigger.
    SELECT * FROM EPREUVES;
    SELECT * FROM OCCUPATIONS;
    SELECT * FROM HORAIRES;
    
    --TEST
    UPDATE HORAIRES SET DateHeureDebut = TIMESTAMP '2021-10-07 09:00:00' WHERE NumEpr = '3';
    UPDATE HORAIRES SET DateHeureDebut = TIMESTAMP '2021-10-07 09:15:00' WHERE NumEpr = '3';

    
 /*
   C4 
    Le nombre total de places occupées par les épreuves qui ont 
    lieu dans une meme salle au meme moment , ne doit pas dépasser 
    la capacité de la salle .
*/
DROP  TRIGGER Salles_capacite; 
CREATE TRIGGER Salles_capacite 
    BEFORE UPDATE OF CapaciteSal, NumSal ON SALLES 
        FOR EACH ROW 
            DECLARE 
                N INTEGER;
               BEGIN

               SELECT 1 INTO N FROM EPREUVES E, HORAIRES H , OCCUPATIONS O
               WHERE
                        E.NumEpr = H.NumEpr AND O.NumEpr = E.NumEpr AND
                        O.NumSal = : NEW.NumSal
                        -- En fait une vérification de la capacité.

                        GROUP BY (H.DateHeureDebut)
                        HAVING SUM(O.NbPlacesOcc) > :NEW.CapaciteSal;

                
                RAISE TOO_MANY_ROWS;

                EXCEPTION 
                    WHEN NO_DATA_FOUND THEN NULL;
                    WHEN TOO_MANY_ROWS THEN raise_application_error(-20000, 'La 
                    capacité de la salle est dépassé');
                 END;
       

/*C5 
    Un Enseignant assure une surveillance dans une salle uniquement 
    lorsqu-une épreuve a lieu.
*/
DROP TRIGGER VERIFIER_SURVEIL;
CREATE TRIGGER VERIFIER_SURVEIL                                                                         
  BEFORE UPDATE OR INSERT  ON SURVEILLANCES
    FOR EACH ROW  
        DECLARE
           N BINARY_INTEGER;
          BEGIN
          SELECT 1 INTO N 
          FROM HORAIRES H, OCCUPATIONS O, SALLES S
          WHERE 
            O.NumSal  = : NEW.NumSal AND
            S.NumSal = : NEW.NumSal AND
            O.NumEpr = H.NumEpr AND 
            O.NumSal = S.NumSal AND
            H.DateHeureDebut <> : NEW.DateHeureDebut  ;
        
          RAISE too_many_rows;

          EXCEPTION
            WHEN no_data_found THEN NULL;
            WHEN too_many_rows THEN RAISE_APPLICATION_ERROR(-20238,'Le prof surveille une salle vide ! ^^');
END;
 