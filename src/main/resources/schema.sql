CREATE TABLE IF NOT EXISTS Users (userId BIGINT PRIMARY KEY AUTO_INCREMENT, userName VARCHAR(30) UNIQUE, userDescription VARCHAR(50), password VARCHAR(50) NOT NULL);

CREATE TABLE IF NOT EXISTS Datas (
dataId BIGINT PRIMARY KEY AUTO_INCREMENT, 
data1 VARCHAR(50),
data2 VARCHAR(50),
data3 VARCHAR(50),
data4 VARCHAR(50),
data5 VARCHAR(50),
created_by VARCHAR(50) NOT NULL);

--CREATE TABLE IF NOT EXISTS UserCredentials (credentialsId BIGINT PRIMARY KEY AUTO_INCREMENT, userId BIGINT, credentials VARCHAR(30), CONSTRAINT fk_userId FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE ON UPDATE CASCADE);
--
--CREATE TABLE IF NOT EXISTS ModuleUsers (moduleUserId BIGINT PRIMARY KEY AUTO_INCREMENT, moduleId VARCHAR(50), userId BIGINT, role VARCHAR(10), permissions VARCHAR(200), CONSTRAINT fk_userId_moduleusers FOREIGN KEY (userId) REFERENCES Users(userId) ON DELETE CASCADE ON UPDATE CASCADE);

