-- DDL For Myba Project
-- Malloc Team
-- 14.06.2015
-- Remise au propre du code :)

CREATE TABLE Critere
(
  ID_CRI  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Nom     VARCHAR(256) NOT NULL,
  Type    VARCHAR(256) NOT NULL,
  NoteMax INT          NOT NULL
);

CREATE TABLE Defense
(
  ID_DEF      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DateEtHeure DATE         NOT NULL,
  Local       VARCHAR(256) NOT NULL,
  AdresseMail_Eval VARCHAR(256) NOT NULL,
  ID_STA      INT,
  ID_TFE      INT
);

CREATE TABLE LieuDeStage
(
  ID_LIEU         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Entreprise      VARCHAR(256) NOT NULL,
  Adr_Rue         VARCHAR(256) NOT NULL,
  Adr_Numero      INT          NOT NULL,
  Adr_Boite       VARCHAR(256),
  Adr_CodePostal  INT          NOT NULL,
  Adr_Ville       VARCHAR(256) NOT NULL,
  Adr_Pays        VARCHAR(256) NOT NULL,
  Per_Prenom      VARCHAR(256) NOT NULL,
  Per_Nom         VARCHAR(256) NOT NULL,
  Per_Telephone   VARCHAR(256),
  Per_AdresseMail VARCHAR(256)
);

-- Relation entre Role et Etudiant
CREATE TABLE assumer
(
  Nom_Role         VARCHAR(256) NOT NULL,
  AdresseMail_Etud VARCHAR(256) NOT NULL,
  PRIMARY KEY (Nom_Role, AdresseMail_Etud)
);

-- Relation entre Evaluateur et Echeance
CREATE TABLE corriger
(
  ID_SUI      INT          NOT NULL AUTO_INCREMENT,
  AdresseMail_Eval VARCHAR(256) NOT NULL,
  PRIMARY KEY (ID_SUI, AdresseMail_Eval)
);

-- Relation entre Technologie et TFE
CREATE TABLE implementer
(
  ID_TEC INT NOT NULL,
  ID_TFE INT NOT NULL,
  PRIMARY KEY (ID_TEC, ID_TFE)
);

-- Relation entre Evaluateur et Role
CREATE TABLE intituler
(
  Nom_Role         VARCHAR(256) NOT NULL,
  AdresseMail_Eval VARCHAR(256) NOT NULL,
  PRIMARY KEY (AdresseMail_Eval, Nom_Role)
);

CREATE TABLE PropositionStage
(
  ID_PRO          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Sujet            VARCHAR(256) NOT NULL,
  AnnexeEventuelle VARCHAR(256),
  Valide           BOOLEAN,
  AdresseMail_Etud      VARCHAR(256),
  AdresseMail_Eval  VARCHAR(256),
  ID_LIEU          INT          NOT NULL
);

CREATE TABLE Role
(
  Nom VARCHAR(256) PRIMARY KEY NOT NULL
);

CREATE TABLE Stage
(
  ID_STA          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_PRO          INT          NOT NULL,
  DateDebut       DATE         NOT NULL,
  DateFin         DATE         NOT NULL,
  Commentaire     VARCHAR(2048),
  AdresseMail_Etud     VARCHAR(256) NOT NULL,
  AdresseMail_MaitreStage VARCHAR(256) NOT NULL,
  AdresseMail_Eval VARCHAR(256) NOT NULL,

  CONSTRAINT FKconcretiser_ID UNIQUE (ID_PRO)
);

CREATE TABLE SuiviEcheance
(
  ID_SUI            INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DocumentRendu      VARCHAR(256) NOT NULL,
  DateEtHeureRemise  DATETIME     NOT NULL,
  Correction         VARCHAR(256),
  DocumentCorrection VARCHAR(256),
  Valide             BOOLEAN,
  AdresseMail_Etud        VARCHAR(256) NOT NULL,
  ID_ECH             INT          NOT NULL
);

-- Relation entre Evaluateur et TFE
create table surveiller
(
    ID_TFE int not null,
    AdresseMail_Eval VARCHAR(256) not null,
	PRIMARY KEY (ID_TFE, AdresseMail_Eval)
);

CREATE TABLE Technologie
(
  ID_TEC  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Nom     VARCHAR(256) NOT NULL,
  Version VARCHAR(256)
);

CREATE TABLE TFE
(
  ID_TFE         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Titre          VARCHAR(256) NOT NULL,
  AnnexeEventuelle VARCHAR(256) NOT NULL,
  AnneeAcadDebut INT          NOT NULL,
  AnneeAcadFin   INT,
  AdresseMail_Etud    VARCHAR(256) NOT NULL
);

CREATE TABLE Telephone
(
  AdresseMail_Eval VARCHAR(256) NOT NULL,
  Telephone   VARCHAR(256) NOT NULL,
  PRIMARY KEY (AdresseMail_Eval, Telephone)
);

-- Relation entre Technologie et Proposition de stage
CREATE TABLE utiliser
(
  ID_PRO INT NOT NULL,
  ID_TEC INT NOT NULL,
  PRIMARY KEY (ID_TEC, ID_PRO)
);

-- Relation entre Etudiant et Echeance
CREATE TABLE viser
(
  ID_ECH      INT          NOT NULL,
  AdresseMail_Etud VARCHAR(256) NOT NULL,
  PRIMARY KEY (AdresseMail_Etud, ID_ECH)
);

CREATE TABLE Echeance
(
  ID_ECH      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DateHeure   DATETIME     NOT NULL,
  Titre       VARCHAR(256) NOT NULL,
  Description VARCHAR(256),
  Annexe      VARCHAR(256),
  ID_STA      INT,
  ID_TFE      INT
);

CREATE TABLE Etudiant
(
  AdresseMail VARCHAR(256) NOT NULL PRIMARY KEY,
  MotDePasse  VARCHAR(256) NOT NULL,
  Prenom      VARCHAR(256) NOT NULL,
  Nom         VARCHAR(256) NOT NULL,
  Matricule   VARCHAR(256),
  Annee       INT          NOT NULL,
  estDoublant BOOLEAN,
  estDispenseTFE BOOLEAN,
  estDispenseStage BOOLEAN
);

CREATE TABLE Evaluateur
(
  AdresseMail VARCHAR(256) NOT NULL PRIMARY KEY,
  MotDePasse  VARCHAR(256) NOT NULL,
  Prenom      VARCHAR(256) NOT NULL,
  Nom         VARCHAR(256) NOT NULL
);

CREATE TABLE Evaluation
(
  Note        VARCHAR(256) NOT NULL,
  Commentaire VARCHAR(256),
  DateEval    DATE         NOT NULL,
  AdresseMail_Eval VARCHAR(256) NOT NULL,
  ID_STA      INT,
  ID_TFE      INT,
  ID_CRI      INT          NOT NULL,
  ID_DEF      INT
);

-- Contraintes

ALTER TABLE Defense ADD CONSTRAINT FKpresider
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE Defense ADD CONSTRAINT FKplanifier
FOREIGN KEY (ID_STA)
REFERENCES Stage (ID_STA);

ALTER TABLE Defense ADD CONSTRAINT FKdefendre
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE);

ALTER TABLE assumer ADD CONSTRAINT FKass_Etu
FOREIGN KEY (AdresseMail_Etud)
REFERENCES Etudiant (AdresseMail);

ALTER TABLE assumer ADD CONSTRAINT FKass_Rol
FOREIGN KEY (Nom_Role)
REFERENCES Role (Nom);

ALTER TABLE corriger ADD CONSTRAINT FKcor_Eva
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE corriger ADD CONSTRAINT FKcor_Sui
FOREIGN KEY (ID_SUI)
REFERENCES SuiviEcheance (ID_SUI);

ALTER TABLE implementer ADD CONSTRAINT FKimp_TFE_FK
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE);

ALTER TABLE implementer ADD CONSTRAINT FKimp_Tec
FOREIGN KEY (ID_TEC)
REFERENCES Technologie (ID_TEC);

ALTER TABLE intituler ADD CONSTRAINT FKint_Eva
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE intituler ADD CONSTRAINT FKint_Rol
FOREIGN KEY (Nom_Role)
REFERENCES Role (Nom);

ALTER TABLE PropositionStage ADD CONSTRAINT FKproposer
FOREIGN KEY (AdresseMail_Etud)
REFERENCES Etudiant (AdresseMail);

ALTER TABLE PropositionStage ADD CONSTRAINT FKoffrir
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE PropositionStage ADD CONSTRAINT FKsituer
FOREIGN KEY (ID_Lieu)
REFERENCES LieuDeStage (ID_Lieu);

ALTER TABLE Stage ADD CONSTRAINT FKsuperviser
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE Stage ADD CONSTRAINT FKmonitorer
FOREIGN KEY (AdresseMail_MaitreStage)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE Stage ADD CONSTRAINT FKeffectuer
FOREIGN KEY (AdresseMail_Etud)
REFERENCES Etudiant (AdresseMail);

ALTER TABLE Stage ADD CONSTRAINT FKconcretiser
FOREIGN KEY (ID_PRO)
REFERENCES PropositionStage (ID_PRO);

ALTER TABLE SuiviEcheance ADD CONSTRAINT FKposter
FOREIGN KEY (AdresseMail_Etud)
REFERENCES Etudiant (AdresseMail);

ALTER TABLE SuiviEcheance ADD CONSTRAINT FKsuivre
FOREIGN KEY (ID_ECH)
REFERENCES Echeance (ID_ECH);

ALTER TABLE TFE ADD CONSTRAINT FKecrire
FOREIGN KEY (AdresseMail_Etud)
REFERENCES Etudiant (AdresseMail);

ALTER TABLE Telephone ADD CONSTRAINT FKEva_Tel
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE utiliser ADD CONSTRAINT FKuti_Tec
FOREIGN KEY (ID_TEC)
REFERENCES Technologie (ID_TEC);

ALTER TABLE utiliser ADD CONSTRAINT FKuti_Pro
FOREIGN KEY (ID_PRO)
REFERENCES PropositionStage (ID_PRO);

ALTER TABLE viser ADD CONSTRAINT FKvis_Etu
FOREIGN KEY (AdresseMail_Etud)
REFERENCES Etudiant (AdresseMail);

ALTER TABLE viser ADD CONSTRAINT FKvis_Ech
FOREIGN KEY (ID_ECH)
REFERENCES Echeance (ID_ECH);

ALTER TABLE surveiller ADD CONSTRAINT FKsur_Eva
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE surveiller ADD CONSTRAINT FKsur_TFE
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE);

ALTER TABLE Echeance ADD CONSTRAINT FKjalloner
FOREIGN KEY (ID_STA)
REFERENCES Stage (ID_STA);

ALTER TABLE Echeance ADD CONSTRAINT FKappliquer
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE);

ALTER TABLE Evaluation ADD CONSTRAINT FKdonner
FOREIGN KEY (AdresseMail_Eval)
REFERENCES Evaluateur (AdresseMail);

ALTER TABLE Evaluation ADD CONSTRAINT FKsanctionner
FOREIGN KEY (ID_STA)
REFERENCES Stage (ID_STA);

ALTER TABLE Evaluation ADD CONSTRAINT FKevaluer
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE);

ALTER TABLE Evaluation ADD CONSTRAINT FKdiscriminer
FOREIGN KEY (ID_CRI)
REFERENCES Critere (ID_CRI);

ALTER TABLE Evaluation ADD CONSTRAINT FKconcerner
FOREIGN KEY (ID_DEF)
REFERENCES Defense (ID_DEF);
