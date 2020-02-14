CREATE TABLE musica (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  duracao DOUBLE NOT NULL,
  album_id INT NOT NULL,
  PRIMARY KEY (id)
  
  )engine=InnoDB;

INSERT INTO musica (nome, duracao, album_id) VALUES ('Smooth Criminal', 250, 1);
INSERT INTO musica (nome, duracao, album_id) VALUES ('Billie Jean', 300, 2);
INSERT INTO musica (nome, duracao, album_id) VALUES ('Beat it', 240, 2);
INSERT INTO musica (nome, duracao, album_id) VALUES ('We are the World', 390, 3);
