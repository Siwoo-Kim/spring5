
DROP TABLE IF EXISTS SINGER_INSTRUMENT;
DROP TABLE IF EXISTS INSTRUMENT;
DROP TABLE IF EXISTS ALBUM;
DROP TABLE IF EXISTS SINGER;

CREATE TABLE SINGER(
  ID INT NOT NULL AUTO_INCREMENT,
  FIRST_NAME VARCHAR(60) NOT NULL,
  LAST_NAME VARCHAR(60) NOT NULL,
  BIRTH_DATE DATE,
  VERSION INT NOT NULL DEFAULT 0,
  UNIQUE UQ_SINGER_1 (FIRST_NAME,LAST_NAME),
  PRIMARY KEY (ID)
);

CREATE TABLE ALBUM(
  ID INT NOT NULL AUTO_INCREMENT,
  SINGER_ID INT NOT NULL,
  TITLE VARCHAR(100) NOT NULL,
  RELEASE_DATE DATE,
  VERSION INT NOT NULL DEFAULT 0,
  UNIQUE UQ_SINGER_ALBUM_1 (SINGER_ID, TITLE),
  CONSTRAINT FK_ALBUM_SINGER FOREIGN KEY(SINGER_ID)
  REFERENCES SINGER(ID),
  PRIMARY KEY(ID)
);

CREATE TABLE INSTRUMENT(
  INSTRUMENT_ID VARCHAR(20) NOT NULL, PRIMARY KEY (INSTRUMENT_ID)
);

CREATE TABLE SINGER_INSTRUMENT(
  SINGER_ID INT NOT NULL,
  INSTRUMENT_ID VARCHAR(20) NOT NULL,
  PRIMARY KEY(SINGER_ID,INSTRUMENT_ID),
  CONSTRAINT FK_SINGER_INSTRUMENT_1
  FOREIGN KEY(SINGER_ID)
  REFERENCES SINGER (ID) ON DELETE CASCADE,
  CONSTRAINT FK_SINGER_INSTRUMENT_2
  FOREIGN KEY(INSTRUMENT_ID)
  REFERENCES INSTRUMENT (INSTRUMENT_ID)
);


insert into singer (first_name, last_name, birth_date) values ('John', 'Mayer', '1977-10-16');
insert into singer (first_name, last_name, birth_date) values ('Eric', 'Clapton', '1945-03-30');
insert into singer (first_name, last_name, birth_date) values ('John', 'Butler', '1975-04-01');

insert into album (singer_id, title, release_date) values (1, 'The Search For Everything', '2017-01-20');
insert into album (singer_id, title, release_date) values (1, 'Battle Studies', '2009-11-17');
insert into album (singer_id, title, release_date) values (2, 'From The Cradle ', '1994-09-13');


insert into instrument (instrument_id) values ('Guitar');
insert into instrument (instrument_id) values ('Piano');
insert into instrument (instrument_id) values ('Voice');
insert into instrument (instrument_id) values ('Drums');
insert into instrument (instrument_id) values ('Synthesizer');

insert into singer_instrument(singer_id, instrument_id) values (1, 'Guitar');
insert into singer_instrument(singer_id, instrument_id) values (1, 'Piano');
insert into singer_instrument(singer_id, instrument_id) values (2, 'Guitar');
