package com.artistaEmusica.artistasmusicas.repository;

import com.artistaEmusica.artistasmusicas.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    List<Artista> findByNomeContainingIgnoreCase(String nome);

}
