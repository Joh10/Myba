-- CREATE TABLE

CREATE TABLE PermissionTable(
   id_Per NUMBER(10) NOT NULL,
   nom VARCHAR2(35) NOT NULL
);

CREATE TABLE RoleTable (
   id_Rol NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL
);

CREATE TABLE Utilisateur (
   id_Uti NUMBER(10) NOT NULL,
   -- TODO enable c'est quoi?
   email VARCHAR2(254 char) NOT NULL,
   password VARCHAR2(254 char) NOT NULL,
   nom VARCHAR2(35) NOT NULL,
   prenom VARCHAR2(35) NOT NULL,
   telephone VARCHAR2(10),
   annee NUMBER(1) NOT NULL,
   doublant

);

CREATE TABLE SuiviEcheance (
   id_Sui NUMBER(10) NOT NULL
);

CREATE TABLE Defense (
   id_Def NUMBER(10) NOT NULL
);

CREATE TABLE Technologie (
   id_Tec NUMBER(10) NOT NULL
);

CREATE TABLE Stage (
   id_Sta NUMBER(10) NOT NULL
);

CREATE TABLE PropositionStage (
   id_Pro NUMBER(10) NOT NULL
);

CREATE TABLE Evaluation (
   id_Eva NUMBER(10) NOT NULL,
   date DATE NOT NULL,
   note NUMBER(3) NOT NULL,
   commentaire VARCHAR2 (512 char) NOT NULL
);

CREATE TABLE Critere (
   id_Cri NUMBER(10) NOT NULL,
   type VARCHAR2 (35 char) NOT NULL,
   nom VARCHAR2 (35 char) NOT NULL,
   noteMax NUMBER(3) NOT NULL
);

CREATE TABLE LieuStage (
  id_Lie NUMBER(10) NOT NULL,
  entreprise VARCHAR2 (35 char) NOT NULL,
  adresse VARCHAR2 (256 char) NOT NULL,
  personneContact VARCHAR2 (70 char) NOT NULL,
  telephone VARCHAR2 (10 char) NOT NULL,
  email VARCHAR2 (254 char) NOT NULL
);

CREATE TABLE TFE(
   id_Tfe NUMBER(10) NOT NULL,
   titre VARCHAR2 (35 char) NOT NULL,
   pointsTotaux NUMBER(3,2) NOT NULL,
   anneeAcadDebut DATE NOT NULL,
   anneeAcadFin DATE NOT NULL
);

CREATE TABLE Echeance (
   id_Ech NUMBER(10) NOT NULL,
   dateCreation DATE NOT NULL,
   dateEcheance DATE NOT NULL,
   description VARCHAR2 (1024 char) NOT NULL,
   --TODO annexe ?
);

-- CREATE SEQUENCE

CREATE SEQUENCE seq_Permission;
CREATE SEQUENCE seq_Role;
CREATE SEQUENCE seq_Utilisateur;
CREATE SEQUENCE seq_SuiviEcheance;
CREATE SEQUENCE seq_Defense;
CREATE SEQUENCE seq_Technologie;
CREATE SEQUENCE seq_Stage;
CREATE SEQUENCE seq_PropositionStage;
CREATE SEQUENCE seq_Evaluation;
CREATE SEQUENCE seq_Critere;
CREATE SEQUENCE seq_LieuStage;
CREATE SEQUENCE seq_TFE;
CREATE SEQUENCE seq_Echeance;

--CREATE TRIGGER INCREMENT SEQUENCE

CREATE OR REPLACE TRIGGER  id_gen_Permission
BEFORE INSERT ON PermissionTable
FOR EACH ROW
   BEGIN
      SELECT seq_Permission.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Role
BEFORE INSERT ON RoleTable
FOR EACH ROW
   BEGIN
      SELECT seq_Role.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Utilisateur
BEFORE INSERT ON Utilisateur
FOR EACH ROW
   BEGIN
      SELECT seq_Utilisateur.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_SuiviEcheance
BEFORE INSERT ON SuiviEcheance
FOR EACH ROW
   BEGIN
      SELECT seq_SuiviEcheance.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Defense
BEFORE INSERT ON SuiviDefense
FOR EACH ROW
   BEGIN
      SELECT seq_Defense.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Technologie
BEFORE INSERT ON Technologie
FOR EACH ROW
   BEGIN
      SELECT seq_Technologie.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Stage
BEFORE INSERT ON Stage
FOR EACH ROW
   BEGIN
      SELECT seq_Stage.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_PropositionStage
BEFORE INSERT ON PropositionStage
FOR EACH ROW
   BEGIN
      SELECT seq_PropositionStage.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Evaluation
BEFORE INSERT ON Evaluation
FOR EACH ROW
   BEGIN
      SELECT seq_Evaluation.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Critere
BEFORE INSERT ON Critere
FOR EACH ROW
   BEGIN
      SELECT seq_Critere.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_LieuStage
BEFORE INSERT ON LieuStage
FOR EACH ROW
   BEGIN
      SELECT seq_LieuStage.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_TFE
BEFORE INSERT ON TFE
FOR EACH ROW
   BEGIN
      SELECT seq_TFE.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Echeance
BEFORE INSERT ON Echeance
FOR EACH ROW
   BEGIN
      SELECT seq_Echeance.NEXTVAL INTO :new./*ID COL*/ FROM dual;
   END;







