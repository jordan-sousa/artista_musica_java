package com.artistaEmusica.artistasmusicas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musica")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private int duracao;

    @ManyToOne
    @JoinColumn(name = "artista_id")
    private Artista artista;

    public Musica(String titulo, int duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    public Musica(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return
                "titulo='" + titulo + '\'' +
                ", duracao=" + duracao ;
    }
}
