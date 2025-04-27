package models;

import Enums.TiposMaterial;

public class Livro extends MaterialBiblioteca {

    private String autor;
    private Integer ISBN;

    public Livro(){}

    public Livro(String autor, Integer ISBN, String titulo, Integer anoPublicacao) {
        super(titulo, anoPublicacao, true, TiposMaterial.LIVRO);
        this.autor = autor;
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return autor;
    }

    public Integer getISBN() {
        return ISBN;
    }

    @Override
    public String toString() {
      return getTipoMaterial() + " - " + getTitulo() + " - " + getAutor() + " - " + getAnoPublicacao() + " - " + getISBN();
    }
}
