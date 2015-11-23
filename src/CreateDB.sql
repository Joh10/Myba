-- CREATE TABLE

CREATE TABLE PermissionTable(
   id_Per NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   constraint id_Per primary key (Id_Per)
);

create table PermissionTableXRoleTable (
   id_Per NUMBER(10) NOT NULL,
   id_Rol NUMBER(10) NOT NULL,
   CONSTRAINT ID_PerXRol PRIMARY KEY (id_Per, id_Rol));

CREATE TABLE RoleTable (
   id_Rol NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   constraint id_Rol primary key (Id_Rol)
);

CREATE TABLE Utilisateur (
   id_Uti NUMBER(10) NOT NULL,
   enable NUMBER(1) NOT NULL, -- TODO enable c'est quoi? utilisateur est activ� ou pas
   email VARCHAR2(254 char) NOT NULL,
   password VARCHAR2(254 char) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   prenom VARCHAR2(35 char) NOT NULL,
   telephone VARCHAR2(10 char) NOT NULL,
   annee NUMBER(1) NOT NULL,
   doublant NUMBER(1) NOT NULL,
   constraint id_Uti primary key (Id_Uti)
);

CREATE TABLE SuiviEcheance (
   id_Sui NUMBER(10) NOT NULL,
   dateRemis DATE NOT NULL,
   commentaire VARCHAR2(512 char) NOT NULL , -- TODO correctionProf c'est quoi?
   -- TODO documentProf c'est quoi?
   valide NUMBER(1) NOT NULL,
   constraint id_Sui primary key (Id_Sui)
);

CREATE TABLE Defense (
   id_Def NUMBER(10) NOT NULL,
   date DATE NOT NULL,
   local VARCHAR2(5 char) NOT NULL,
   constraint id_Def primary key (Id_Def)
);

CREATE TABLE Technologie (
   id_Tec NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   version VARCHAR2(35 char) NOT NULL,
   constraint id_Tec primary key (Id_Tec)
);

CREATE TABLE Stage (
   id_Sta NUMBER(10) NOT NULL,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   pointsTotaux NUMBER(3,2) NOT NULL,
   commentaire VARCHAR2(512 char) NOT NULL,
   constraint id_Sta primary key (Id_Sta)
);

CREATE TABLE PropositionStage (
   id_Pro NUMBER(10) NOT NULL,
   valide NUMBER(1) NOT NULL,
   sujet VARCHAR2(1024 char) NOT NULL,
   -- TODO annexe quel type
   constraint id_Pro primary key (Id_Pro)
);

CREATE TABLE Evaluation (
   id_Eva NUMBER(10) NOT NULL,
   date DATE NOT NULL,
   note NUMBER(3) NOT NULL,
   commentaire VARCHAR2 (512 char) NOT NULL,
   constraint id_Eva primary key (Id_Eva)

);

CREATE TABLE Critere (
   id_Cri NUMBER(10) NOT NULL,
   type VARCHAR2 (35 char) NOT NULL,
   nom VARCHAR2 (35 char) NOT NULL,
   noteMax NUMBER(3) NOT NULL,
   constraint id_Cri primary key (Id_Cri)
);

CREATE TABLE LieuStage (
  id_Lie NUMBER(10) NOT NULL,
  entreprise VARCHAR2 (35 char) NOT NULL,
  adresse VARCHAR2 (256 char) NOT NULL,
  personneContact VARCHAR2 (70 char) NOT NULL,
  telephone VARCHAR2 (10 char) NOT NULL,
  email VARCHAR2 (254 char) NOT NULL,
  constraint id_Lie primary key (Id_Lie)
);

CREATE TABLE TFE(
   id_Tfe NUMBER(10) NOT NULL,
   titre VARCHAR2 (35 char) NOT NULL,
   pointsTotaux NUMBER(3,2) NOT NULL,
   anneeAcadDebut DATE NOT NULL,
   anneeAcadFin DATE NOT NULL,
   constraint id_TFE primary key (Id_TFE)
);

CREATE TABLE Echeance (
   id_Ech NUMBER(10) NOT NULL,
   dateCreation DATE NOT NULL,
   dateEcheance DATE NOT NULL,
   description VARCHAR2 (1024 char) NOT NULL,
   --TODO annexe ?
   constraint id_Ech primary key (Id_Ech)
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
      SELECT seq_Permission.NEXTVAL INTO :new.id_Per FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Role
BEFORE INSERT ON RoleTable
FOR EACH ROW
   BEGIN
      SELECT seq_Role.NEXTVAL INTO :new.id_Rol FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Utilisateur
BEFORE INSERT ON Utilisateur
FOR EACH ROW
   BEGIN
      SELECT seq_Utilisateur.NEXTVAL INTO :new.id_Uti FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_SuiviEcheance
BEFORE INSERT ON SuiviEcheance
FOR EACH ROW
   BEGIN
      SELECT seq_SuiviEcheance.NEXTVAL INTO :new.id_Sui FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Defense
BEFORE INSERT ON Defense
FOR EACH ROW
   BEGIN
      SELECT seq_Defense.NEXTVAL INTO :new.id_Def FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Technologie
BEFORE INSERT ON Technologie
FOR EACH ROW
   BEGIN
      SELECT seq_Technologie.NEXTVAL INTO :new.id_Tec FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Stage
BEFORE INSERT ON Stage
FOR EACH ROW
   BEGIN
      SELECT seq_Stage.NEXTVAL INTO :new.id_Sta FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_PropositionStage
BEFORE INSERT ON PropositionStage
FOR EACH ROW
   BEGIN
      SELECT seq_PropositionStage.NEXTVAL INTO :new.id_Pro FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Evaluation
BEFORE INSERT ON Evaluation
FOR EACH ROW
   BEGIN
      SELECT seq_Evaluation.NEXTVAL INTO :new.id_Eva FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Critere
BEFORE INSERT ON Critere
FOR EACH ROW
   BEGIN
      SELECT seq_Critere.NEXTVAL INTO :new.id_Cri FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_LieuStage
BEFORE INSERT ON LieuStage
FOR EACH ROW
   BEGIN
      SELECT seq_LieuStage.NEXTVAL INTO :new.id_Lie FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_TFE
BEFORE INSERT ON TFE
FOR EACH ROW
   BEGIN
      SELECT seq_TFE.NEXTVAL INTO :new.id_Tfe FROM dual;
   END;

CREATE OR REPLACE TRIGGER  id_gen_Echeance
BEFORE INSERT ON Echeance
FOR EACH ROW
   BEGIN
      SELECT seq_Echeance.NEXTVAL INTO :new.id_Ech FROM dual;
   END;

--CREATE TRIGGER BOOLEAN CHECK

alter table Utilisateur add constraint CHK_Enable check (enable = 0 or enable = 1);
alter table Utilisateur add constraint CHK_Doublant check (doublant = 0 or doublant = 1);
alter table SuiviEcheance add constraint CHK_Valide check (valide = 0 or valide = 1);
alter table PropositionStage add constraint CHK_Valide check (valide = 0 or valide = 1);

--CREATE TRIGGER MAIL

--CREATE TRIGGER TELEPHONE
alter table Utilisateur add constraint CHK_UtilisateurTel check (regexp_like(telephone,'^(([+]32|0032)\\s\\(0\\)([0-9]{9})|([+]32|0032)\\s0([0-9]{9})|0([0-9]{9}))$'));
alter table lieuDeStage add constraint CHK_telephoneLie check (regexp_like(per_telephone,'^(([+]32|0032)\\s\\(0\\)([0-9]{9})|([+]32|0032)\\s0([0-9]{9})|0([0-9]{9}))$'));

--CREATE TRIGGER PRIMARY KEY



