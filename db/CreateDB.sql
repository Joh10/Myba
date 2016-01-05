-- CREATE TABLE

CREATE TABLE PermissionTable(
   id_Per NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   CONSTRAINT id_Per PRIMARY KEY (Id_Per)
);

CREATE TABLE PermissionTableXRoleTable (
   id_Per NUMBER(10) NOT NULL,
   id_Rol NUMBER(10) NOT NULL,
   CONSTRAINT ID_PerXRol PRIMARY KEY (id_Per, id_Rol));

CREATE TABLE RoleTable (
   id_Rol NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   CONSTRAINT id_Rol PRIMARY KEY (Id_Rol)
);

CREATE TABLE UtilisateurXRoleTable (
   id_Uti NUMBER(10) NOT NULL,
   id_Rol NUMBER(10) NOT NULL,
   CONSTRAINT ID_UtiXRol PRIMARY KEY (id_Uti, id_Rol));

CREATE TABLE Utilisateur (
   id_Uti NUMBER(10) NOT NULL,
   enable NUMBER(1) NOT NULL,
   email VARCHAR2(254 char) NOT NULL,
   password VARCHAR2(254 char) NOT NULL,
   matricule Number(20) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   prenom VARCHAR2(35 char) NOT NULL,
   telephone VARCHAR2(10 char) NOT NULL,
   annee NUMBER(1) NOT NULL,
   doublant NUMBER(1) NOT NULL,
   CONSTRAINT id_Uti PRIMARY KEY (Id_Uti)
);

CREATE TABLE UtilisateurXEcheance (
   id_Uti NUMBER(10) NOT NULL,
   id_Ech NUMBER(10) NOT NULL,
   CONSTRAINT ID_UtiXEch PRIMARY KEY (id_Uti, id_Ech));

CREATE TABLE UtilisateurXTFE (
   id_Uti NUMBER(10) NOT NULL,
   id_TFE NUMBER(10) NOT NULL,
   CONSTRAINT ID_UtiXTFE PRIMARY KEY (id_Uti, id_TFE));

CREATE TABLE UtilisateurXStage (
   id_Uti NUMBER(10) NOT NULL,
   id_Sta NUMBER(10) NOT NULL,
   CONSTRAINT ID_UtiXSta PRIMARY KEY (id_Uti, id_Sta));

CREATE TABLE Defense (
   id_Def NUMBER(10) NOT NULL,
   dateDefense DATE NOT NULL,
   local VARCHAR2(5 char) NOT NULL,
   ref_Stage NUMBER(10),
   ref_Utilisateur NUMBER(10),
   ref_Tfe NUMBER(10),
   CONSTRAINT id_Def PRIMARY KEY (Id_Def)
);

CREATE TABLE Technologie (
   id_Tec NUMBER(10) NOT NULL,
   nom VARCHAR2(35 char) NOT NULL,
   version VARCHAR2(35 char) NOT NULL,
   CONSTRAINT id_Tec PRIMARY KEY (Id_Tec)
);

CREATE TABLE TechnologieXTFE (
   id_Tec NUMBER(10) NOT NULL,
   id_TFE NUMBER(10) NOT NULL,
   CONSTRAINT ID_TecXTFE PRIMARY KEY (id_Tec, id_TFE));

CREATE TABLE TechnologieXSta (
   id_Tec NUMBER(10) NOT NULL,
   id_Sta NUMBER(10) NOT NULL,
   CONSTRAINT ID_TecXSta PRIMARY KEY (id_Tec, id_Sta));

CREATE TABLE Stage (
   id_Sta NUMBER(10) NOT NULL,
   dateDebut DATE NOT NULL,
   dateFin DATE NOT NULL,
   pointsTotaux NUMBER(3,2) NOT NULL,
   commentaire VARCHAR2(512 char) NOT NULL,
   ref_PropositionStage NUMBER(10),
   CONSTRAINT id_Sta PRIMARY KEY (Id_Sta)
);

CREATE TABLE StageXEcheance (
   id_Sta NUMBER(10) NOT NULL,
   id_Ech NUMBER(10) NOT NULL,
   CONSTRAINT ID_StaXEch PRIMARY KEY (id_Sta, id_Ech));

CREATE TABLE PropositionStage (
   id_Pro NUMBER(10) NOT NULL,
   valide NUMBER(1) NOT NULL,
   sujet VARCHAR2(1024 char) NOT NULL,
   annexe VARCHAR2 (1024 char),
   ref_Utilisateur NUMBER(10),
   ref_LieuStage NUMBER(10),
   CONSTRAINT id_Pro PRIMARY KEY (Id_Pro)
);

CREATE TABLE Evaluation (
   id_Eva NUMBER(10) NOT NULL,
   dateDefense DATE NOT NULL,
   note NUMBER(3) NOT NULL,
   commentaire VARCHAR2 (512 char) NOT NULL,
   ref_Utilisateur NUMBER(10),
   ref_Critere NUMBER(10),
   ref_TFE NUMBER(10),
   ref_Defense NUMBER(10),
   ref_Stage NUMBER(10),
   CONSTRAINT id_Eva PRIMARY KEY (Id_Eva)
);

CREATE TABLE Critere (
   id_Cri NUMBER(10) NOT NULL,
   type VARCHAR2 (35 char) NOT NULL,
   nom VARCHAR2 (35 char) NOT NULL,
   noteMax NUMBER(3) NOT NULL,
   CONSTRAINT id_Cri PRIMARY KEY (Id_Cri)
);

CREATE TABLE LieuStage (
  id_Lie NUMBER(10) NOT NULL,
  entreprise VARCHAR2 (35 char) NOT NULL,
  adresse VARCHAR2 (256 char) NOT NULL,
  personneContact VARCHAR2 (70 char) NOT NULL,
  telephone VARCHAR2 (10 char) NOT NULL,
  email VARCHAR2 (254 char) NOT NULL,
  ref_Utilisateur NUMBER(10),
  CONSTRAINT id_Lie PRIMARY KEY (Id_Lie)
);

CREATE TABLE TFE(
   id_Tfe NUMBER(10) NOT NULL,
   titre VARCHAR2 (35 char) NOT NULL,
   pointsTotaux NUMBER(3,2) NOT NULL,
   anneeAcadDebut DATE NOT NULL,
   anneeAcadFin DATE NOT NULL,
   CONSTRAINT id_TFE PRIMARY KEY (Id_TFE)
);

CREATE TABLE TFEXEcheance (
   id_TFE NUMBER(10) NOT NULL,
   id_Ech NUMBER(10) NOT NULL,
   CONSTRAINT ID_TFEXEch PRIMARY KEY (id_TFE, id_Ech));

CREATE TABLE Echeance (
   id_Ech NUMBER(10) NOT NULL,
   dateCreation DATE NOT NULL,
   dateEcheance DATE NOT NULL,
   description VARCHAR2 (1024 char) NOT NULL,
   annexe VARCHAR2 (1024 char),
   CONSTRAINT id_Ech PRIMARY KEY (Id_Ech)
);

--CREATE ALTER TABLE FK

Alter table PropositionStage Add constraint FK_Utilisateur_Pro FOREIGN KEY (ref_Utilisateur) References Utilisateur(id_Uti);

Alter table LieuStage Add Constraint FK_Utilisateur_Lieu FOREIGN KEY (ref_Utilisateur) References Utilisateur(id_Uti);

Alter table PropositionStage Add Constraint FK_LieuStage FOREIGN KEY (ref_LieuStage) References LieuStage(id_Lie);

Alter table Stage Add constraint FK_PropositionStage Foreign KEy (ref_PropositionStage) References PropositionStage(id_Pro);

Alter table Evaluation Add constraint FK_Utilisateur_Eval FOREIGN KEY (ref_Utilisateur) References Utilisateur(id_Uti);

Alter table Evaluation Add constraint FK_Critere Foreign Key (ref_Critere) References Critere(id_Cri);

Alter table Evaluation Add constraint FK_TFE_Eval Foreign Key (ref_TFE) References TFE(id_Tfe);

Alter table Defense Add constraint FK_Stage_Def Foreign Key (ref_Stage) References Stage(id_Sta);

Alter table Defense Add constraint FK_Utilisateur_Def Foreign Key (ref_Utilisateur) References Utilisateur(id_Uti);

Alter Table Evaluation Add constraint FK_Defense Foreign Key (ref_Defense) References Defense(id_Def);

Alter table Evaluation Add constraint FK_Stage_Eval Foreign Key (ref_Stage) References Stage(id_Sta);

Alter table Defense Add constraint FK_TFE_Defense Foreign Key (ref_Tfe) References TFE(id_Tfe);
--CREATE CONSTRAINT BOOLEAN CHECK

ALTER TABLE Utilisateur ADD CONSTRAINT CHK_Enable CHECK (enable = 0 OR enable = 1);
ALTER TABLE Utilisateur ADD CONSTRAINT CHK_Doublant CHECK (doublant = 0 OR doublant = 1);
ALTER TABLE PropositionStage ADD CONSTRAINT CHK_Valide_Propo CHECK (valide = 0 OR valide = 1);

--CREATE CONSTRAINTR MAIL

ALTER TABLE Utilisateur ADD CONSTRAINT CHK_UtilisateurMail check (regexp_like(email,'^.+@.+\..+$'));
ALTER TABLE LieuStage ADD CONSTRAINT CHK_LieuStageMail check (regexp_like(email,'^.+@.+\..+$'));

--CREATE CONSTRAINTR TELEPHONE

ALTER TABLE Utilisateur ADD CONSTRAINT CHK_UtilisateurTel CHECK (regexp_like(telephone,'^(([+]\d\d|00\d\d)\\s\\(0\\)([0-9]{9})|([+]32|0032)\\s0([0-9]{9})|0([0-9]{9}))$'));
ALTER TABLE LieuStage ADD CONSTRAINT CHK_LieuStageTel CHECK (regexp_like(telephone,'^(([+]\d\d|00\d\d)\\s\\(0\\)([0-9]{9})|([+]32|0032)\\s0([0-9]{9})|0([0-9]{9}))$'));

--CREATE CONSTRAINT FILE PATH
ALTER TABLE Echeance ADD CONSTRAINT CHK_EcheanceAnnexe CHECK (regexp_like(annexe,'^(?:[\w]\:|\\)(\\[a-z_\-\s0-9\.]+)+\.(...|....)$'));
ALTER TABLE PropositionStage ADD CONSTRAINT CHK_PropositionStageAnnexe CHECK (regexp_like(annexe,'^(?:[\w]\:|\\)(\\[a-z_\-\s0-9\.]+)+\.(...|....)$'));
