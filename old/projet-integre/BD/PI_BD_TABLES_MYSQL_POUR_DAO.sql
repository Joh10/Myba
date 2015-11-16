CREATE TABLE Critere
(
  ID_Cri  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Nom     VARCHAR(256) NOT NULL,
  Type    VARCHAR(256) NOT NULL,
  NoteMax INT          NOT NULL
);

CREATE TABLE Defense
(
  ID_Def      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DateEtHeure DATE         NOT NULL,
  Local       VARCHAR(256) NOT NULL,
  AdresseMail VARCHAR(256) NOT NULL,
  ID_Sta      INT,
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
  Nom         VARCHAR(256) NOT NULL,
  AdresseMail VARCHAR(256) NOT NULL,
  PRIMARY KEY (Nom, AdresseMail)
);

-- Relation entre Evaluateur et Echeance
CREATE TABLE corriger
(
  ID_Sui      INT          NOT NULL AUTO_INCREMENT,
  AdresseMail VARCHAR(256) NOT NULL,
  PRIMARY KEY (ID_Sui, AdresseMail)
);

-- Relation entre Technologie et TFE
CREATE TABLE implementer
(
  ID_Tec INT NOT NULL,
  ID_TFE INT NOT NULL,
  PRIMARY KEY (ID_Tec, ID_TFE)
);

-- Relation entre Evaluateur et Role
CREATE TABLE intituler
(
  Nom         VARCHAR(256) NOT NULL,
  AdresseMail VARCHAR(256) NOT NULL,
  PRIMARY KEY (AdresseMail, Nom)
);

CREATE TABLE PropositionStage
(
  ID_Pro           INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Sujet            VARCHAR(256) NOT NULL,
  AnnexeEventuelle VARCHAR(256),
  Valide           BOOLEAN,
  AdresseMail      VARCHAR(256),
  Off_AdresseMail  VARCHAR(256),
  ID_Lieu          INT          NOT NULL
);

CREATE TABLE Role
(
  Nom VARCHAR(256) PRIMARY KEY NOT NULL
);

CREATE TABLE Stage
(
  ID_Sta          INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  ID_Pro          INT          NOT NULL,
  DateDebut       DATE         NOT NULL,
  DateFin         DATE         NOT NULL,
  AdresseMail     VARCHAR(256) NOT NULL,
  Commentaire     VARCHAR(2048),
  Mon_AdresseMail VARCHAR(256) NOT NULL,
  Eff_AdresseMail VARCHAR(256) NOT NULL,

  CONSTRAINT FKconcretiser_ID UNIQUE (ID_Pro)
);

CREATE TABLE SuiviEcheance
(
  ID_Sui             INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DocumentRendu      VARCHAR(256) NOT NULL,
  DateEtHeureRemise  DATETIME     NOT NULL,
  Correction         VARCHAR(256),
  DocumentCorrection VARCHAR(256),
  Valide             BOOLEAN,
  AdresseMail        VARCHAR(256) NOT NULL,
  ID_Ech             INT          NOT NULL
);

CREATE TABLE Technologie
(
  ID_Tec  INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Nom     VARCHAR(256) NOT NULL,
  Version VARCHAR(256)
);

CREATE TABLE TFE
(
  ID_TFE         INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  Titre          VARCHAR(256) NOT NULL,
  AnneeAcadDebut INT          NOT NULL,
  AnneeAcadFin   INT          NOT NULL,
  AdresseMail    VARCHAR(256) NOT NULL,
  AnnexeEventuelle VARCHAR(256)
);

CREATE TABLE Telephone
(
  AdresseMail VARCHAR(256) NOT NULL,
  Telephone   VARCHAR(256) NOT NULL,
  PRIMARY KEY (AdresseMail, Telephone)
);

-- Relation entre Technologie et Proposition de stage
CREATE TABLE utiliser
(
  ID_Pro INT NOT NULL,
  ID_Tec INT NOT NULL,
  PRIMARY KEY (ID_Tec, ID_Pro)
);

-- Relation entre Etudiant et Echeance
CREATE TABLE viser
(
  ID_Ech      INT          NOT NULL,
  AdresseMail VARCHAR(256) NOT NULL,
  PRIMARY KEY (AdresseMail, ID_Ech)
);

CREATE TABLE Echeance
(
  ID_Ech      INT          NOT NULL AUTO_INCREMENT PRIMARY KEY,
  DateHeure   DATETIME     NOT NULL,
  Titre       VARCHAR(256) NOT NULL,
  Description VARCHAR(256),
  Annexe      VARCHAR(256),
  ID_Sta      INT,
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
  estDispense BOOLEAN
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
  AdresseMail VARCHAR(256) NOT NULL,
  ID_Sta      INT,
  ID_TFE      INT,
  ID_Cri      INT          NOT NULL,
  ID_Def      INT
);

-- Contraintes

ALTER TABLE Defense ADD CONSTRAINT FKpresider_FK
FOREIGN KEY (AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE Defense ADD CONSTRAINT FKplanifier_FK
FOREIGN KEY (ID_Sta)
REFERENCES Stage (ID_Sta)
ON DELETE CASCADE;

ALTER TABLE Defense ADD CONSTRAINT FKdefendre_FK
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE)
ON DELETE CASCADE;

ALTER TABLE assumer ADD CONSTRAINT FKass_Etu_FK
FOREIGN KEY (AdresseMail)
REFERENCES Etudiant (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE assumer ADD CONSTRAINT FKass_Rol
FOREIGN KEY (Nom)
REFERENCES Role (Nom)
ON DELETE CASCADE;

ALTER TABLE composer ADD CONSTRAINT FKcom_Rol_FK
FOREIGN KEY (Nom)
REFERENCES Role (Nom)
ON DELETE CASCADE;

ALTER TABLE corriger ADD CONSTRAINT FKcor_Eva_FK
FOREIGN KEY (AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE corriger ADD CONSTRAINT FKcor_Sui
FOREIGN KEY (ID_Sui)
REFERENCES SuiviEcheance (ID_Sui)
ON DELETE CASCADE;

ALTER TABLE implementer ADD CONSTRAINT FKimp_TFE_FK
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE)
ON DELETE CASCADE;

ALTER TABLE implementer ADD CONSTRAINT FKimp_Tec
FOREIGN KEY (ID_Tec)
REFERENCES Technologie (ID_Tec)
ON DELETE CASCADE;

ALTER TABLE intituler ADD CONSTRAINT FKint_Eva
FOREIGN KEY (AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE intituler ADD CONSTRAINT FKint_Rol_FK
FOREIGN KEY (Nom)
REFERENCES Role (Nom)
ON DELETE CASCADE;

ALTER TABLE PropositionStage ADD CONSTRAINT FKproposer_FK
FOREIGN KEY (AdresseMail)
REFERENCES Etudiant (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE PropositionStage ADD CONSTRAINT FKoffrir_FK
FOREIGN KEY (Off_AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE PropositionStage ADD CONSTRAINT FKsituer_FK
FOREIGN KEY (ID_Lieu)
REFERENCES LieuDeStage (ID_Lieu)
ON DELETE CASCADE;

ALTER TABLE Stage ADD CONSTRAINT FKsuperviser_FK
FOREIGN KEY (AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE Stage ADD CONSTRAINT FKmonitorer_FK
FOREIGN KEY (Mon_AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE Stage ADD CONSTRAINT FKeffectuer_FK
FOREIGN KEY (Eff_AdresseMail)
REFERENCES Etudiant (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE Stage ADD CONSTRAINT FKconcretiser_FK
FOREIGN KEY (ID_Pro)
REFERENCES PropositionStage (ID_Pro)
ON DELETE CASCADE;

ALTER TABLE SuiviEcheance ADD CONSTRAINT FKposter_FK
FOREIGN KEY (AdresseMail)
REFERENCES Etudiant (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE SuiviEcheance ADD CONSTRAINT FKsuivre_FK
FOREIGN KEY (ID_Ech)
REFERENCES Echeance (ID_Ech)
ON DELETE CASCADE;

ALTER TABLE TFE ADD CONSTRAINT FKecrire_FK
FOREIGN KEY (AdresseMail)
REFERENCES Etudiant (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE Telephone ADD CONSTRAINT FKEva_Tel
FOREIGN KEY (AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE utiliser ADD CONSTRAINT FKuti_Tec
FOREIGN KEY (ID_Tec)
REFERENCES Technologie (ID_Tec)
ON DELETE CASCADE;

ALTER TABLE utiliser ADD CONSTRAINT FKuti_Pro_FK
FOREIGN KEY (ID_Pro)
REFERENCES PropositionStage (ID_Pro)
ON DELETE CASCADE;

ALTER TABLE viser ADD CONSTRAINT FKvis_Etu
FOREIGN KEY (AdresseMail)
REFERENCES Etudiant (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE viser ADD CONSTRAINT FKvis_Ech_FK
FOREIGN KEY (ID_Ech)
REFERENCES Echeance (ID_Ech)
ON DELETE CASCADE;

ALTER TABLE Echeance ADD CONSTRAINT FKjalloner_FK
FOREIGN KEY (ID_Sta)
REFERENCES Stage (ID_Sta)
ON DELETE CASCADE;

ALTER TABLE Echeance ADD CONSTRAINT FKappliquer_FK
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE)
ON DELETE CASCADE;

ALTER TABLE Evaluation ADD CONSTRAINT FKdonner_FK
FOREIGN KEY (AdresseMail)
REFERENCES Evaluateur (AdresseMail)
ON DELETE CASCADE;

ALTER TABLE Evaluation ADD CONSTRAINT FKsanctionner_FK
FOREIGN KEY (ID_Sta)
REFERENCES Stage (ID_Sta)
ON DELETE CASCADE;

ALTER TABLE Evaluation ADD CONSTRAINT FKevaluer_FK
FOREIGN KEY (ID_TFE)
REFERENCES TFE (ID_TFE)
ON DELETE CASCADE;

ALTER TABLE Evaluation ADD CONSTRAINT FKdiscriminer_FK
FOREIGN KEY (ID_Cri)
REFERENCES Critere (ID_Cri)
ON DELETE CASCADE;

ALTER TABLE Evaluation ADD CONSTRAINT FKconcerner_FK
FOREIGN KEY (ID_Def)
REFERENCES Defense (ID_Def)
ON DELETE CASCADE;
