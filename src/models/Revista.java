package models;

import Enums.TiposMaterial;

public class Revista extends MaterialBiblioteca{

    private String editora;
    private Integer edicao;

    public Revista(){}

    public Revista(String titulo, Integer anoPublicacao, String editora, Integer edicao) {
        super(titulo, anoPublicacao, true, TiposMaterial.REVISTA);
        this.editora = editora;
        this.edicao = edicao;
    }

    public Revista(String titulo, Integer anoPublicacao, String editora, Integer edicao, Boolean disponibilidade) {
        super(titulo, anoPublicacao, disponibilidade, TiposMaterial.REVISTA);
        this.editora = editora;
        this.edicao = edicao;
    }

    public Integer getEdicao() {
        return edicao;
    }

    public String getEditora() {
        return editora;
    }

    @Override
    public String toString() {
        return getTipoMaterial() + " - " + getTitulo() + " - " + getEditora() + " - " + getAnoPublicacao() + " - " + getEdicao();
    }
}
