CREATE TABLE ${schemaname}.EL10LEMMJT (ENTA INTEGER, ENTB INTEGER) CCSID UNICODE;
CREATE TABLE ${schemaname}.EL10LEOMJT (ENTA INTEGER, ENTB INTEGER) CCSID UNICODE;
CREATE TABLE ${schemaname}.EL10LockEntA (ID INTEGER NOT NULL, STRDATA VARCHAR(254), VERSION INTEGER, ENTB_MTO INTEGER, ENTB_OTO INTEGER, PRIMARY KEY (ID)) CCSID UNICODE;
CREATE TABLE ${schemaname}.EL10LockEntB (ID INTEGER NOT NULL, STRDATA VARCHAR(254), VERSION INTEGER, PRIMARY KEY (ID)) CCSID UNICODE;
CREATE INDEX I_L10LMJT_ELEMENT ON ${schemaname}.EL10LEMMJT (ENTB);
CREATE INDEX I_L10LMJT_ENTA ON ${schemaname}.EL10LEMMJT (ENTA);
CREATE INDEX I_L10LMJT_ELEMENT1 ON ${schemaname}.EL10LEOMJT (ENTB);
CREATE INDEX I_L10LMJT_ENTA1 ON ${schemaname}.EL10LEOMJT (ENTA);
CREATE INDEX I_L10LKNT_LOCKENTBMANYTOONE ON ${schemaname}.EL10LockEntA (ENTB_MTO);
CREATE INDEX I_L10LKNT_LOCKENTBONETOONE ON ${schemaname}.EL10LockEntA (ENTB_OTO);