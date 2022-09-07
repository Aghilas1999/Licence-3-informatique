CREATE TABLE Hotel (
    NumHo INTEGER NOT NULL,
    NomHo VARCHAR(100) ,
    RueAdrHo VARCHAR(100),
    VilleHo VARCHAr(100),
    NbEtoileHo integer,
    PRIMARY KEY (NumHo) 
);
CREATE TABLE TypesChambre (
    NumTy INTEGER NOT NULL ,
    NomTy VARCHAR NOT NUll,
    Prixty DECIMAL(10,2),
    CHECK (Prixty>0),
    PRIMARY KEY (NumTy)
);

CREATE TABLE Chambre (
    NumCh INTEGER NOT NULL,
    NumHo INTEGER NOT NULL,
    NumTy INTEGER NOT NULL,
    PRIMARY KEY (NumCh,NumHo)
);
CREATE TABLE Client  (
    NumCl INTEGER NOT NULL,
    NomCl VARCHAR NOT NULL,
    PrenomCL VARCHAR NOT NULL,
    RueAdrCl VARCHAR NOT NULL,
    VilleCl VARCHAR NOT NULL,
    PRIMARY KEY (NumCl)
);

CREATE TABLE Reservation (
    NumCl INTEGER REFERENCES Client(NumCl) ,
    NumHo VARCHAR REFERENCES Chambre(NumHo),
    NumTy INTEGER REFERENCES TypesChambre(NumTy),
    DateA TIMESTAMP,
    NbJour INTERVAL DAY TO SECOND (0),
    NbChambres INTEGER NOT NULL,
    PRIMARY KEY (NumCl,NumHo,NumTy,DateA)
);
CREATE TABLE Occupation (
    NumCl INTEGER NOT NULL,
    NumHo INTEGER REFERENCES Hotel(NumHo),
    NumCh INTEGER REFERENCES Chambres(NumCh),
    DateA TIMESTAMP,
    DateD TIMESTAMP,
    PRIMARY KEY (NumHo,NumCh,DateA)
);

INSERT INTO TABLE Hotel(1,'Casa','Marseille','Luminy',5),

