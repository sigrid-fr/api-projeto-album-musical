CREATE TABLE album (
  id INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NULL,
  ano INT NULL,
  PRIMARY KEY (id)
) engine=InnoDB;

INSERT INTO album (nome, ano) VALUES ('Bad', 1987);
INSERT INTO album (nome, ano) VALUES ('Thriller', 1982);
INSERT INTO album (nome, ano) VALUES ('We are the World', 1985);
