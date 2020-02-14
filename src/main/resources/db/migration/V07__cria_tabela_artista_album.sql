CREATE TABLE artista_album (
  artista_id INT NOT NULL,
  album_id INT(11) NOT NULL,
  PRIMARY KEY (artista_id, album_id),

  INDEX fk_artista_has_album_album_idx (album_id ASC),
  INDEX fk_artista_has_album_artista_idx (artista_id ASC)

) ENGINE = InnoDB ;

INSERT INTO artista_album (artista_id, album_id) VALUES (1, 1);

