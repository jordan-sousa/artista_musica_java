package com.artistaEmusica.artistasmusicas.repository;

import com.artistaEmusica.artistasmusicas.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    List<Musica> findByArtistaNomeContainingIgnoreCase(String nomeArtista);
}
