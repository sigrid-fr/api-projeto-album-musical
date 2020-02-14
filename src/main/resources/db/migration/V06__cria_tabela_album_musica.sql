CREATE TABLE album_musica (
  album_id INT NOT NULL,
  musica_id INT(11) NOT NULL,
  PRIMARY KEY (album_id, musica_id),

INDEX fk_album_has_musica_idx (musica_id ASC),
  INDEX fk_album_has_musica_album_idx (album_id ASC)

) ENGINE = InnoDB ;

INSERT INTO album_musica (album_id, musica_id) VALUES (1, 1);