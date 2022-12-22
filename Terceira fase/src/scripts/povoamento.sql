USE `Simulação`;
-- DELETE FROM `Simulação`.`Campeonatos`;
-- SELECT * FROM `Simulação`.`Campeonatos`;
INSERT INTO `Simulação`.`Campeonatos`
(Id,Nome)
VALUES
('1','CampeUMnato'),
('2','BragaCup'),
('3','RallyMinho'),
('4','MinhoRace'),
('5','CompetitionBraga'),
('6','MinhoPrize');


-- DELETE FROM `Simulação`.`Circuitos`;
-- SELECT * FROM `Simulação`.`Circuitos`;
INSERT INTO `Simulação`.`Circuitos`
(Id,Nome,Clima,Voltas, Comprimento)
VALUES
('1','Gualtar Campus','Tempo Seco','9','20'),
('2','Azurém Campus','Chuva','8','18'),
('3','Sameiro - Bom Jesus','Tempo Seco','10','30'),
('4','Avenida Central','Chuva','7','16'),
('5','Parque Rodovia','Tempo Seco','6','12'),
('6','Palmeira - Estação CF','Chuva','11','40');

-- DELETE FROM `Simulação`.`Carros`;
-- SELECT * FROM `Simulação`.`Carros`;
INSERT INTO `Simulação`.`Carros`
(Id,Marca,Modelo)
VALUES
('1','Mazda','Miata'),
('2','Toyota','GR86'),
('3','Toyota','Supra'),
('4','Chevy','Camaro'),
('5','Ford','Mustang'),
('6','Porsche','Cayman'),
('7','Porsche','911'),
('8','Ferrari','F8'),
('9','Maserati','MC20'),
('10','Chevy','Corvette');

-- DELETE FROM `Simulação`.`Pilotos`;
-- SELECT * FROM `Simulação`.`Pilotos`;
INSERT INTO `Simulação`.`Pilotos`
(Id, Nome, CTS,SVA)
VALUES
('1','Lando','0.6','0.4'),
('2','Charles','0.4','0.6'),
('3','Yuki','0.7','0.2'),
('4','Max','0.4','0.3'),
('5','Lewis','0.8','0.5'),
('6','Maria','0.6','0.8'),
('7','Lucy','0.3','0.2'),
('8','Kate','0.5','0.7'),
('9','Juliana','0.8','0.5'),
('10','Grace','0.6','0.6');

-- DELETE FROM `Simulação`.`ClassesCarros`;
-- SELECT * FROM `Simulação`.`ClassesCarros`;
INSERT INTO `Simulação`.`ClassesCarros`
(Id, minCilindrada, maxCilindrada)
VALUES
('C1','6000','6000'),
('C2','3000','5000'),
('GT','2000','4000'),
('ST','2500','2500');

-- DELETE FROM `Simulação`.`Utilizadores`;
-- SELECT * FROM `Simulação`.`Utilizadores`;
INSERT INTO `Simulação`.`Utilizadores`
(username, pass,pontuaçãoGeral,isAdmin)
VALUES
('anarita','ritagrupo30','97','true'),
('miguel','miguelgrupo30','99','true'),
('orlando','orlandogrupo30','98','true'),
('pedro','pedrogrupo30','96','true');
('joão','joaogrupo30','95','true');

