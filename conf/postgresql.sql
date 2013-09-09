DROP TABLE "cherry"."customer";
DROP TABLE "cherry"."history";
DROP SEQUENCE "cherry"."seq_customer";
DROP SEQUENCE "cherry"."seq_history";
DROP SCHEMA "cherry";

DROP TABLE "orange"."customer";
DROP TABLE "orange"."history";
DROP SEQUENCE "orange"."seq_customer";
DROP SEQUENCE "orange"."seq_history";
DROP SCHEMA "orange";

--------------------
--  Cherry Schema --
--------------------
CREATE SCHEMA "cherry";

CREATE SEQUENCE "cherry"."seq_customer";
CREATE SEQUENCE "cherry"."seq_history";

CREATE TABLE "cherry"."customer" (
    "id"    bigint  NOT NULL  DEFAULT nextval('cherry.seq_customer')  PRIMARY KEY,
    "name"  character varying(512)
);

CREATE TABLE "cherry"."history" (
    "id"       bigint  NOT NULL  DEFAULT nextval('cherry.seq_history')  PRIMARY KEY,
    "message"  character varying(1024)
);

SET search_path TO "cherry";
INSERT INTO "customer" (name) VALUES ('チェリーはなこ');

--------------------
--  Orange Schema --
--------------------
CREATE SCHEMA "orange";

CREATE SEQUENCE "orange"."seq_customer";
CREATE SEQUENCE "orange"."seq_history";

CREATE TABLE "orange"."customer" (
    "id"    bigint  NOT NULL  DEFAULT nextval('orange.seq_customer')  PRIMARY KEY,
    "name"  character varying(512)
);

CREATE TABLE "orange"."history" (
    "id"       bigint  NOT NULL  DEFAULT nextval('orange.seq_history')  PRIMARY KEY,
    "message"  character varying(1024)
);

SET search_path TO "orange";
INSERT INTO "customer" (name) VALUES ('オレンジたろう');
