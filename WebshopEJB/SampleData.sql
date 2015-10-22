insert into WEBSHOP.PRODUCT(NAME, DESCRIPTION, MEDIAPATH, UNITPRICE) values('Sugar', 'Robin Schulz feat. Francesco Yates - Platz 1', 'sugar.mp3', 5.0);
insert into WEBSHOP.PRODUCT(NAME, DESCRIPTION, MEDIAPATH, UNITPRICE) values('Marvin Gaye', 'Charlie Puth feat. Meghan Trainor - Platz 2', 'marvin_gaye.mp3', 4.0);
insert into WEBSHOP.PRODUCT(NAME, DESCRIPTION, MEDIAPATH, UNITPRICE) values('Astronaut', 'Robin Schulz feat. Francesco Yates - Platz 3', 'astronaut.mp3', 3.0);
insert into WEBSHOP.PRODUCT(NAME, DESCRIPTION, MEDIAPATH, UNITPRICE) values('Photograph', 'Ed Sheeran - Platz 4', 'photograph.mp3', 2.0);
insert into WEBSHOP.PRODUCT(NAME, DESCRIPTION, MEDIAPATH, UNITPRICE) values('Locked Away', 'R. City feat. Adam Levine - Platz 5', 'locked_way.mp3', 1.0);

/*
* Create Authgroup
*/
CREATE TABLE WEBSHOP.authgroup (
    idauthgroup INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH 1 INCREMENT BY 1 MINVALUE -2147483648 MAXVALUE 2147483647 NO CYCLE CACHE 20),
    groupname VARCHAR(255) NOT NULL
);

ALTER TABLE WEBSHOP.authgroup ADD CONSTRAINT SQL150907165942130 PRIMARY KEY (idauthgroup);


/*
* Create Usergroup
*/
CREATE TABLE WEBSHOP.usergroup (
    iduser INTEGER NOT NULL,
    idgroup INTEGER NOT NULL
);

ALTER TABLE WEBSHOP.usergroup ADD CONSTRAINT SQL150907165942950 PRIMARY KEY (iduser, idgroup);

ALTER TABLE WEBSHOP.usergroup ADD CONSTRAINT AUTHGROUP_STUDENT_FK FOREIGN KEY (idgroup) REFERENCES WEBSHOP.authgroup (idauthgroup);

ALTER TABLE WEBSHOP.usergroup ADD CONSTRAINT USERGROUP_CUSTOMER_FK FOREIGN KEY (iduser) REFERENCES WEBSHOP.customer (customerid);

--AUTHGROUP
INSERT INTO WEBSHOP.AUTHGROUP (IDAUTHGROUP, GROUPNAME) VALUES (default, 'customer');

--USERGROUP
INSERT INTO WEBSHOP.USERGROUP (IDUSER, IDGROUP) VALUES ((SELECT CUSTOMERID FROM WEBSHOP.CUSTOMER WHERE USERNAME='testuser'), (SELECT IDAUTHGROUP FROM WEBSHOP.AUTHGROUP WHERE GROUPNAME='customer'));

-- Call Sysproc.admin_cmd ('reorg Table WEBSHOP.PURCHASE');