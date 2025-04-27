package models;

import Enums.TiposMaterial;

import java.util.ArrayList;

public class ArtigoCientifico extends MaterialBiblioteca{

    private String publicacao;
    private String DOI;

    private ArrayList listaAutores = new ArrayList();

    public ArtigoCientifico(){}

    public ArtigoCientifico(String titulo, Integer anoPublicacao, String publicacao, String DOI, String autoresArtigo) {
        super(titulo, anoPublicacao,true, TiposMaterial.ARTIGO_CIENTIFICO);
        this.publicacao = publicacao;
        this.DOI = DOI;

        String[] autoresartigo = autoresArtigo.split("\\s*,\\s*");
        for (String autores : autoresartigo){
            listaAutores.add(autores);
        }

    }

    public String getPublicacao() {
        return publicacao;
    }

    public String getDOI() {
        return DOI;
    }

    public ArrayList getAutores() {
        return listaAutores;
    }

    @Override
    public String toString() {
        return getTipoMaterial() + " - " + getTitulo() + " - " + getPublicacao() + " - " + getAnoPublicacao() + " - " + getDOI() + " - " + getAutores();
    }
}
