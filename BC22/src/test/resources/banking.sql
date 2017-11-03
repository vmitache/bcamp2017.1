drop schema bank if exists;
create schema bank;
create table bank.country(ID IDENTITY PRIMARY KEY,name varchar(64) NOT NULL UNIQUE);
create table bank.city(ID IDENTITY PRIMARY KEY,name varchar(64) NOT NULL,
  country_id bigint REFERENCES bank.country(id));
create table bank.address(ID IDENTITY PRIMARY KEY, 
   city_id bigint REFERENCES bank.city(id),
   street varchar(256), 
   number varchar(16),
   postal_code varchar(16));
create table bank.bank(ID IDENTITY PRIMARY KEY,
   name varchar(128) NOT NULL,
   address_id bigint REFERENCES bank.address(id),
   fiscal_code varchar(32));
create table bank.customer(ID IDENTITY PRIMARY KEY,
   name varchar(128) NOT NULL,
   ssn varchar(16),
   address_id bigint REFERENCES bank.address(id),
   age integer,
   sex char);
create table bank.currency(ID IDENTITY PRIMARY KEY,
   name varchar(12) NOT NULL);
create table bank.account(ID IDENTITY PRIMARY KEY,
   iban varchar(64) NOT NULL,
   bank_id bigint REFERENCES bank.bank(id),
   customer_id bigint REFERENCES bank.customer(id),
   amount numeric(10,2) NOT NULL,
   account_type varchar(32) NOT NULL,
   currency_id bigint REFERENCES bank.currency(id));
create table bank.transaction(ID IDENTITY PRIMARY KEY,
   account_id bigint REFERENCES bank.account(id),
   ttype char NOT NULL,
   amount numeric(10,2) NOT NULL,
   ttime TIMESTAMP);
      
INSERT INTO bank.currency(name) values('RON'); // id==1
INSERT INTO bank.currency(name) values('EUR'); // id==2
INSERT INTO bank.currency(name) values('USD'); // id==3
INSERT INTO bank.currency(name) values('GBP'); // id==4

INSERT INTO bank.country(name) VALUES('ROMANIA'); // id == 1
INSERT INTO bank.country(name) VALUES('HUNGARY'); // id ==2 

INSERT INTO bank.city(name, country_id) VALUES ('BUCHAREST',1);
INSERT INTO bank.city(name, country_id) VALUES ('CLUJ',1);
INSERT INTO bank.city(name, country_id) VALUES ('BRASOV',1);
INSERT INTO bank.city(name, country_id) VALUES ('CONSTANTA',1);

INSERT INTO bank.address(city_id,street,number,postal_code) VALUES(
  1,'Masina de paine','13Bis','077190');
INSERT INTO bank.address(city_id,street,number,postal_code) VALUES(
  4,'Tomis','12Bis','077190');
INSERT INTO bank.address(city_id,street,number,postal_code) VALUES(
  2,'Iancu De Hunedoara','1xxx','077190');
  
INSERT INTO bank.bank(name,address_id) VALUES('BCR',1);

INSERT INTO bank.customer(name,address_id,sex) VALUES('Popescu',2,'M');
INSERT INTO bank.customer(name,address_id,sex) VALUES('Ionescu Ana',3,'F');

INSERT INTO bank.account(iban,bank_id,customer_id,amount,account_type,currency_id) VALUES('RNCB0101010101101', 1, 1, 100.50, 'DEBIT', 1);
INSERT INTO bank.account(iban,bank_id,customer_id,amount,account_type,currency_id) VALUES('RNCB020202020202', 1, 1, 11, 'SAVINGS', 2);
INSERT INTO bank.account(iban,bank_id,customer_id,amount,account_type,currency_id) VALUES('RNCB01030303030303', 1, 2, 314.14, 'DEBIT', 1);
INSERT INTO bank.account(iban,bank_id,customer_id,amount,account_type,currency_id) VALUES('RNCB01030303030404', 1, 2, 314.14, 'DEBIT', 2);


