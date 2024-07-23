package com.artistaEmusica.artistasmusicas.principal;

import com.artistaEmusica.artistasmusicas.model.Artista;
import com.artistaEmusica.artistasmusicas.model.Musica;
import com.artistaEmusica.artistasmusicas.model.TipoArtista;
import com.artistaEmusica.artistasmusicas.repository.ArtistaRepository;
import com.artistaEmusica.artistasmusicas.repository.MusicaRepository;
import com.artistaEmusica.artistasmusicas.service.ConsultaChatGPT;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);

    @Autowired
    private MusicaRepository musicaRepository;
    @Autowired
    private ArtistaRepository artistaRepository;

    private List<Musica> musicas = new ArrayList<>();

    private ConsultaChatGPT consultaChatGPT;

    public Principal(MusicaRepository musicaRepository, ArtistaRepository artistaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu =
                    """
                        1 - Cadastrar artista
                        2 - Cadastrar música
                        3 - Lista de músicas
                        4 - Pesquisar música por artista
                        5 - Pesquisar dados de artista
                        0 - Sair
                    """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadstrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    pesquisarMusicaPorArtista();
                    break;
                case 5:
                    pesquisarDadosDeArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }

    }

    private void cadstrarArtista() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("S")){
            System.out.println("Nome do artista: ");
            var nome = leitura.nextLine();
            System.out.println("Tipo de artista (SOLO, DUPLA, BANDA)");
            var tipoArt = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipoArt.toUpperCase());

            Artista artista = new Artista();
            artista.setNome(nome);
            artista.setTipo(tipoArtista);

            artistaRepository.save(artista);
            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }

    }

    private void cadastrarMusica() {
        System.out.println("Título da música: ");
        var titulo = leitura.nextLine();
        System.out.println("Duração da musica(em segundos)");
        int duracao = leitura.nextInt();
        leitura.nextLine();
        System.out.println("Nome do artista");
        var nomeArtista = leitura.nextLine();

        List<Artista> artistas = artistaRepository.findByNomeContainingIgnoreCase(nomeArtista);
        if (artistas.isEmpty()){
            System.out.println("Artista não encontrado!");
            return;
        }

        Musica musica = new Musica();
        musica.setTitulo(titulo);
        musica.setDuracao(duracao);
        musica.setArtista(artistas.get(0));

        musicaRepository.save(musica);
        System.out.println("Música cadastrada com sucesso!");
    }

    private void listarMusicas() {
        musicas = musicaRepository.findAll();
        musicas.stream()
                .sorted(Comparator.comparing(Musica::getTitulo))
                .forEach(System.out::println);
    }

    private void pesquisarMusicaPorArtista() {
        System.out.println("Nome do artista");
        var nomeArtista = leitura.nextLine();

        List<Musica> musicas = musicaRepository.findByArtistaNomeContainingIgnoreCase(nomeArtista);
        if (musicas.isEmpty()){
            System.out.println("Nenhuma música encontrada");
        } else {
            musicas.forEach(m -> {
                System.out.println("Título: " + m.getTitulo() + ", Duração: " + m.getDuracao());
            });
        }
    }

    private void pesquisarDadosDeArtista() {
        System.out.println("Pesquisar dados sobre qual artista?");
        var nome = leitura.nextLine();
        var resposta = consultaChatGPT.obterInformacoes(nome);
        System.out.println(resposta.trim());
    }
}
