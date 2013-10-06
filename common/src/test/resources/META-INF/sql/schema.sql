drop table if exists ACCOUNT_OBJECTIVE;
drop table if exists ACCOUNT_CREDIT_CARD;
drop table if exists ACCOUNT;
drop table if exists MERCHANT;
drop table if exists PAYBACK;
drop sequence if exists SEQ_PAYBACK_CONFIRMATION_NUMBER;

create table ACCOUNT (
  ID integer identity primary key,
  NUMBER varchar(9),
  NAME varchar(50) not null,
  unique(NUMBER));

create table ACCOUNT_CREDIT_CARD (
  ID integer identity primary key,
  ACCOUNT_ID integer,
  NUMBER varchar(16),
  unique(ACCOUNT_ID, NUMBER));

create table ACCOUNT_OBJECTIVE (
  ID integer identity primary key,
  ACCOUNT_ID integer,
  NAME varchar(50),
  ALLOCATION double not null,
  SAVINGS double not null,
  unique(ACCOUNT_ID, NAME));

create table MERCHANT (
  ID integer identity primary key,
  NUMBER varchar(10) not null,
  NAME varchar(80) not null,
  PAYBACK double not null,
  unique(NUMBER));

create table PAYBACK (
  ID integer identity primary key,
  NUMBER varchar(25) not null,
  AMOUNT double not null,
  DATE date not null,
  ACCOUNT_NUMBER varchar(9) not null,
  MERCHANT_NUMBER varchar(10) not null,
  TRANSACTION_AMOUNT double not null,
  TRANSACTION_DATE date not null,
  unique(NUMBER));

create sequence SEQ_PAYBACK_CONFIRMATION_NUMBER start with 1;

alter table ACCOUNT_OBJECTIVE add constraint FK_ACCOUNT_OBJECTIVE
  foreign key (ACCOUNT_ID) references ACCOUNT(ID) on delete cascade;

alter table ACCOUNT_CREDIT_CARD add constraint FK_ACCOUNT_CREDIT_CARD
  foreign key (ACCOUNT_ID) references ACCOUNT(ID) on delete cascade;
