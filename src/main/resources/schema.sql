CREATE TABLE USER(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  USERNAME VARCHAR(64) NOT NULL,
  PASSWORD VARCHAR(64) NOT NULL,
   IS_ADMIN BIT NOT NULL,
   SCORE INT
);
CREATE TABLE QUESTIONS(
  ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  QUESTION VARCHAR(300) NOT NULL,
  OPTION1 VARCHAR (30) NOT NULL,
  OPTION2 VARCHAR (30) NOT NULL,
  OPTION3 VARCHAR (30) NOT NULL,
  ANSWER INT NOT NULL,
  CATEGORY_ID INT,
  FREQUENCY_OPTION1 INT ,
  FREQUENCY_OPTION2 INT,
  FREQUENCY_OPTION3 INT
);

CREATE TABLE HIGH_SCORE (
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
USER_ID BIGINT NOT NULL,
POINT INT NOT NULL);

CREATE TABLE QUESTION_BOX (
ID BIGINT AUTO_INCREMENT PRIMARY KEY,
QUESTION VARCHAR (300) NOT NULL,
ANSWER VARCHAR (50) NOT NULL);

ALTER TABLE HIGH_SCORE ADD FOREIGN KEY (USER_ID) REFERENCES USER(ID);
