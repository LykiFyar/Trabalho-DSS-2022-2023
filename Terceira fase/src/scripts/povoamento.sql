USE `Simulação`;

-- DELETE FROM `Simulação`.`Campeonatos`;
-- SELECT * FROM `Simulação`.`Campeonatos`;
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
(Nome,Clima,Voltas, Comprimento)
VALUES
('Gualtar Campus','Tempo Seco',9,20),
('Azurém Campus','Chuva',8,18),
('Sameiro - Bom Jesus','Tempo Seco',10,30),
('Avenida Central','Chuva',7,16),
('Parque Rodovia','Tempo Seco',6,12),
('Palmeira - Estação CF','Chuva',11,40);

-- DELETE FROM `Simulação`.`Carros`;
-- SELECT * FROM `Simulação`.`Carros`;
INSERT INTO `Simulação`.`Carros`
(Marca,Modelo)
VALUES
('Mazda','Miata'),
('Toyota','GR86'),
('Toyota','Supra'),
('Chevy','Camaro'),
('Ford','Mustang'),
('Porsche','Cayman'),
('Porsche','911'),
('Ferrari','F8'),
('Maserati','MC20'),
('Chevy','Corvette');

-- DELETE FROM `Simulação`.`Pilotos`;
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

-- DELETE FROM `Simulação`.`ClassesCarros`;
-- SELECT * FROM `Simulação`.`ClassesCarros`;
INSERT INTO `Simulação`.`ClassesCarros`
(Id, minCilindrada, maxCilindrada)
VALUES
('C1',6000,6000),
('C2',3000,5000),
('GT',2000,4000),
('ST',2500,2500);

-- DELETE FROM `Simulação`.`Utilizadores`;
-- SELECT * FROM `Simulação`.`Utilizadores`;
INSERT INTO `Simulação`.`Utilizadores`
(user, pass,pontuaçãoGeral,isAdmin)
VALUES
('anarita','ritagrupo30',97,1),
('miguel','miguelgrupo30',99,1),
('orlando','orlandogrupo30',98,1),
('pedro','pedrogrupo30',96,1),
('joão','joaogrupo30',95,1);

