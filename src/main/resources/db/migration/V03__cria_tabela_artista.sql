CREATE TABLE artista (
  id INT AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  nacionalidade VARCHAR(100) NOT NULL,
  album_id INT NOT NULL,
  PRIMARY KEY (id)
) engine=InnoDB;

INSERT INTO artista (nome, nacionalidade, album_id) VALUES ('Michael Jackson', 'USA', 3);
INSERT INTO artista (nome, nacionalidade, album_id) VALUES ('Ray Charles', 'USA', 3);
