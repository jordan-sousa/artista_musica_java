package com.artistaEmusica.artistasmusicas;

import com.artistaEmusica.artistasmusicas.principal.Principal;
import com.artistaEmusica.artistasmusicas.repository.ArtistaRepository;
import com.artistaEmusica.artistasmusicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArtistasmusicasApplication implements CommandLineRunner {
	@Autowired
	private ArtistaRepository artistaRepository;
	@Autowired
	private MusicaRepository musicaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ArtistasmusicasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(musicaRepository, artistaRepository);
		principal.exibeMenu();
	}

}
