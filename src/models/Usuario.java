package models;

import java.util.Objects;

public class Usuario {

    private String nome;
    private String CPF;
    private String email;

    public Usuario(){}

    public Usuario(String nome, String CPF, String email) {
        this.nome = nome;
        this.CPF = CPF;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getCPF() {
        return CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(nome, usuario.nome) && Objects.equals(CPF, usuario.CPF);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, CPF);
    }
}
