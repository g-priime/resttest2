DROP DATABASE IF EXISTS DogsDB;
CREATE DATABASE DogsDB;

USE DogsDB;

-- users table
CREATE TABLE users (
    USERNAME VARCHAR(20) PRIMARY KEY,
    PASSWORD VARCHAR(20) NOT NULL,
    FIRST_NAME VARCHAR(20) NOT NULL,
    LAST_NAME VARCHAR(20) NOT NULL,
    EMAIL VARCHAR(40) NOT NULL,
    PHONE_NUMBER VARCHAR(10) NOT NULL,
    EMERGENCY_PHONE VARCHAR(10) NOT NULL,
    EMERGENCY_NAME VARCHAR(40) NOT NULL,
    ISACTIVE BOOLEAN NOT NULL,
    ISDISABLED BOOLEAN NOT NULL
);

INSERT INTO users
VALUES ('admin', 'password', 'Carsen', 'Johns', 'example@email.com', '4031234567', '4037654321', 'PeepeepoopooMan', true, false);

-- user_address table
CREATE TABLE user_address (
    USERNAME VARCHAR(20),
    BUILDING_NUM VARCHAR(5),
    HOUSE_APT_NUM VARCHAR(10),
    STREET VARCHAR(20),
    CITY VARCHAR(20),
    PROVINCE VARCHAR(20),
    POSTAL VARCHAR(6)
);

ALTER TABLE user_address
ADD FOREIGN KEY (USERNAME)
REFERENCES users (USERNAME);

INSERT INTO user_address
VALUES ('admin', '111a', '123b', 'Senator Burns', 'Calgary', 'Alberta', 'A1A1A1');

-- account_type table, never inserted into
CREATE TABLE account_type (
    USER_TYPE TINYINT PRIMARY KEY,
    DESCRIPTION VARCHAR(10) NOT NULL
);

INSERT INTO account_type
VALUES (1, 'Admin');

INSERT INTO account_type
VALUES (2, 'Customer');

-- account_type to users bridge table
CREATE TABLE user_accttype (
    USERNAME VARCHAR(20),
    USER_TYPE TINYINT,
    PRIMARY KEY (USERNAME, USER_TYPE),
    FOREIGN KEY (USERNAME) REFERENCES users (USERNAME),
    FOREIGN KEY (USER_TYPE) REFERENCES account_type (USER_TYPE)
);

INSERT INTO user_accttype
VALUES ('admin', 1);

-- dogs table
CREATE TABLE dogs (
    PET_ID INT AUTO_INCREMENT PRIMARY KEY,
    OWNER VARCHAR(20),
    NAME VARCHAR(20),
    BREED VARCHAR(20),
    WEIGHT DECIMAL(5, 2),
    BIRTH_DATE DATE,
    GENDER VARCHAR(6),
    SPAYED_NEUTERED BOOLEAN,
    STRANGER_FRIENDLY BOOLEAN,
    LARGE_FRIENDLY BOOLEAN,
    SMALL_FRIENDLY BOOLEAN,
    PUPPY_FRIENDLY BOOLEAN,
    FOREIGN KEY (OWNER) REFERENCES users (USERNAME)
);

INSERT INTO dogs (NAME, OWNER, BREED, WEIGHT, BIRTH_DATE, GENDER, SPAYED_NEUTERED, STRANGER_FRIENDLY, LARGE_FRIENDLY, SMALL_FRIENDLY, PUPPY_FRIENDLY)
VALUES ('Max', 'admin' ,'Boston Terrier', 4.20, '2019-12-25', 'Male', true, true, true, true, true);

-- dogs_allergy table
CREATE TABLE dogs_allergy (
    PET_ID INT,
    ALLERGY VARCHAR(20),
    FOREIGN KEY (PET_ID) REFERENCES dogs(PET_ID)
);

INSERT INTO dogs_allergy
VALUES (1, 'Coffee');

-- dogs_vaccine table
CREATE TABLE dogs_vaccine (
    PET_ID INT,
    VACCINE VARCHAR(20),
    EXPIRATION DATE,
    FOREIGN KEY (PET_ID) REFERENCES dogs(PET_ID)
);

INSERT INTO dogs_vaccine
VALUES (1, 'Corona Virus Vaccine', '2023-12-25');

INSERT INTO dogs_vaccine
VALUES (1, 'Swing Flu Vaccine', '2022-01-01');

-- dogs_medication table
CREATE TABLE dogs_medication (
    PET_ID INT,
    MEDICATION VARCHAR(20),
    FOREIGN KEY (PET_ID) REFERENCES dogs(PET_ID)
);

INSERT INTO dogs_medication
VALUES (1, 'Advil');

-- appointments table
CREATE TABLE appointments (
    APPT_ID INT AUTO_INCREMENT PRIMARY KEY,
    DATE_START DATETIME,
    DATE_END DATETIME,
    TOTAL_COST DECIMAL(7, 2),
    AMOUNT_PAID DECIMAL(7, 2),
    ISAPPROVED BOOLEAN,
    COMMENTS VARCHAR(100)
);

-- users to appointments bridge table
CREATE TABLE users_apppointments (
    USERNAME VARCHAR(20),
    APPT_ID INT,
    PRIMARY KEY (USERNAME, APPT_ID),
    FOREIGN KEY (USERNAME) REFERENCES users(USERNAME),
    FOREIGN KEY (APPT_ID) REFERENCES appointments(APPT_ID)
);

-- appointment_type table, never inserted into
CREATE TABLE appointment_type (
    APPT_TYPE TINYINT PRIMARY KEY,
    APPT_DESC VARCHAR(20)
);

-- appointment to appointment_type bridge table
CREATE TABLE appt_appttype (
    APPT_ID INT,
    APPT_TYPE TINYINT,
    PRIMARY KEY (APPT_ID, APPT_TYPE),
    FOREIGN KEY (APPT_ID) REFERENCES appointments(APPT_ID),
    FOREIGN KEY (APPT_TYPE) REFERENCES appointment_type(APPT_TYPE)
);

-- veterinarians table
CREATE TABLE veterinarians (
    VET_ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(30),
    CLINIC VARCHAR(30),
    PHONE_NUMBER VARCHAR(10)
);

-- users veterinarians bridge table
CREATE TABLE dogs_vets (
    PET_ID INT,
    VET_ID INT,
    PRIMARY KEY (PET_ID, VET_ID),
    FOREIGN KEY (PET_ID) REFERENCES dogs(PET_ID),
    FOREIGN KEY (VET_ID) REFERENCES veterinarians(VET_ID)
);

COMMIT;