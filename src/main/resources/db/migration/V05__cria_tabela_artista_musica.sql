CREATE TABLE artista_musica (
  artista_id INT NOT NULL,
  musica_id INT(11) NOT NULL,
  PRIMARY KEY (artista_id, musica_id),

  INDEX fk_artista_has_musica_album_idx (musica_id ASC),
  INDEX fk_artista_has_musica_artista_idx (artista_id ASC)

) ENGINE = InnoDB ;

INSERT INTO artista_musica (artista_id, musica_id) VALUES (1, 1);