USE Simulação;

-- -------------------------------------
-- CREATES THE "CAMPEONATOS" TABLE
-- -------------------------------------
CREATE TABLE IF NOT EXISTS `Simulação`.`Campeonatos` (
 Nome VARCHAR(255) NOT NULL,
 PRIMARY KEY (Nome));

-- -------------------------------------
-- CREATES THE "CIRCUITOS" TABLE
-- -------------------------------------
CREATE TABLE IF NOT EXISTS `Simulação`.`Circuitos` (
 Nome VARCHAR(255) NOT NULL,
 Clima VARCHAR(255) NOT NULL,
 Voltas INT NOT NULL,
 Comprimento INT NOT NULL,
 Nsetores INT NOT NULL,
 PRIMARY KEY (Nome));

-- -------------------------------------
-- CREATES THE "CARROS" TABLE
-- -------------------------------------

CREATE TABLE IF NOT EXISTS `Simulação`.`Carros` (
 Id INT NOT NULL AUTO_INCREMENT,
 Marca VARCHAR(255) NOT NULL,
 Modelo VARCHAR(255) NOT NULL,
 Classe VARCHAR(255) NOT NULL,
 FOREIGN KEY (Classe) REFERENCES ClassesCarros(Id),
 PRIMARY KEY (Id));

-- -------------------------------------
-- CREATES THE "PILOTOS" TABLE
-- -------------------------------------

CREATE TABLE IF NOT EXISTS `Simulação`.`Pilotos` (
 Nome VARCHAR(255) NOT NULL,
 CTS DECIMAL (5,2), -- consegue guardar valores entre -999.99 a 999.99.
 SVA DECIMAL (5,2), -- consegue guardar valores entre -999.99 a 999.99.
 PRIMARY KEY (Nome));

-- -------------------------------------
-- CREATES THE "CLASSESCARROS" TABLE
-- -------------------------------------

CREATE TABLE IF NOT EXISTS `Simulação`.`ClassesCarros` (
 Id VARCHAR(255) NOT NULL,
 minCilindrada INT NOT NULL, 
 maxCilindrada INT NOT NULL, 
 PRIMARY KEY (Id));

-- -------------------------------------
-- CREATES THE "UTILIZADORES" TABLE
-- -------------------------------------

--DROP TABLE `Simulação`.`Utilizadores`;
CREATE TABLE IF NOT EXISTS `Simulação`.`Utilizadores` (
 user VARCHAR(255) NOT NULL,
 pass VARCHAR(255) NOT NULL,
 pontuaçãoGeral INT NOT NULL, 
 isAdmin INT NOT NULL, -- 1 se true, 0 se false
 PRIMARY KEY (user));

-- -------------------------------------
-- PRINT THE TABLES 
-- -------------------------------------

SELECT * FROM `Simulação`.`Campeonatos`;
SELECT * FROM `Simulação`.`Circuitos`;
SELECT * FROM `Simulação`.`Carros`;
SELECT * FROM `Simulação`.`Pilotos`;
SELECT * FROM `Simulação`.`ClassesCarros`;
SELECT * FROM `Simulação`.`Utilizadores`;





