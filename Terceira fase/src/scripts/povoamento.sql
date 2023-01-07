-- DROP DATABASE `Simulação`;
-- CREATE DATABASE `Simulação`;
-- USE `Simulação`;


-- DELETE FROM `Simulação`.`Campeonatos`;
-- SELECT * FROM `Simulação`.`Campeonatos` Where Nome='BragaCup';
INSERT INTO `Simulação`.`Campeonatos`
(Nome)
VALUES
('CampeUMnato'),
('BragaCup'),
('RallyMinho'),
('MinhoRace'),
('CompetitionBraga'),
('MinhoPrize');


-- DELETE FROM `Simulação`.`Circuitos`;
-- SELECT * FROM `Simulação`.`Circuitos`;
INSERT INTO `Simulação`.`Circuitos`
(Nome,Clima,Voltas,Comprimento, Nsetores)
VALUES
('Gualtar Campus','Tempo Seco',9,20, 5),
('Azurém Campus','Chuva',8,18, 6),
('Sameiro - Bom Jesus','Tempo Seco',10,30, 3),
('Avenida Central','Chuva',7,16, 6),
('Parque Rodovia','Tempo Seco',6,12, 7),
('Palmeira - Estação CF','Chuva',11,40, 8);

-- DELETE FROM `Simulação`.`ClassesCarros` Where Id='ST';
-- SELECT * FROM `Simulação`.`ClassesCarros`;
INSERT INTO `Simulação`.`ClassesCarros`
(Id, minCilindrada, maxCilindrada)
VALUES
('C1',6000,6000),
('C2',3000,5000),
('GT',2000,4000),
('SC',2500,2500);

-- DELETE FROM `Simulação`.`Carros`;
-- SELECT * FROM `Simulação`.`Carros`;
INSERT INTO `Simulação`.`Carros`
(Marca,Modelo,Classe,Cilindrada,Potência)
VALUES
('Mazda','Miata','SC',2500,150),
('Toyota','GR86','SC',2500,200),
('Toyota','Supra','GT',3000,400),
('Chevy','Camaro','GT',2500,350),
('Ford','Mustang','GT',4000,600),
('Porsche','Cayman','C1',6000,800),
('Porsche','911','C1',6000,900),
('Ferrari','F8','C1',6000,1000),
('Maserati','MC20','C2',4000,750),
('Chevy','Corvette','C2',3000,650);

-- DELETE FROM `Simulação`.`Pilotos`;
-- DROP TABLE IF EXISTS `Pilotos`;
-- SELECT * FROM `Simulação`.`Pilotos`;
INSERT INTO `Simulação`.`Pilotos`
(Nome, CTS,SVA)
VALUES
('Lando',0.6,0.4),
('Charles',0.4,0.6),
('Yuki',0.7,0.2),
('Max',0.4,0.3),
('Lewis',0.8,0.5),
('Maria',0.6,0.8),
('Lucy',0.3,0.2),
('Kate',0.5,0.7),
('Juliana',0.8,0.5),
('Grace',0.6,0.6);


-- DELETE FROM `Simulação`.`Utilizadores`;	
-- UPDATE Utilizadores SET pontuaçãoGeral = '111' where user = 'miguel';
INSERT INTO `Simulação`.`Utilizadores`
(user, pass,pontuaçãoGeral,isAdmin)
VALUES
('anarita','ritagrupo30',97,1),
('miguel','miguelgrupo30',99,1),
('orlando','orlandogrupo30',98,1),
('pedro','pedrogrupo30',96,1),
('joão','joaogrupo30',95,1);