package models;

import Enums.TiposMaterial;

public abstract class MaterialBiblioteca {

    protected String titulo;
    protected Integer anoPublicacao;
    protected Boolean disponibilidade;
    protected TiposMaterial tipoMaterial;

    public MaterialBiblioteca(){}

    public MaterialBiblioteca(String titulo, Integer anoPublicacao, Boolean disponibilidade, TiposMaterial tipoMaterial) {
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.disponibilidade = disponibilidade;
        this.tipoMaterial = tipoMaterial;
    }

    public String getTitulo() {
        return titulo;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public TiposMaterial getTipoMaterial() {
        return tipoMaterial;
    }

    public Boolean getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(Boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    @Override
    public String toString() {
        return "MaterialBiblioteca{" +
                "titulo='" + titulo + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", disponibilidade=" + disponibilidade +
                ", tipoMaterial=" + tipoMaterial +
                '}';
    }
}
