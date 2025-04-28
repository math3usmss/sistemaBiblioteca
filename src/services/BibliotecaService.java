package services;

import models.*;

import java.util.ArrayList;

public class BibliotecaService {

    ArrayList<MaterialBiblioteca> materiasBibliotecas = new ArrayList<>();
    ArrayList<Usuario> usuarios = new ArrayList<>();

    public void CriarLivro(String autor, Integer ISBN, String titulo, Integer anoPublicacao) {
        try {
            Livro livro = new Livro(autor, ISBN, titulo, anoPublicacao);
            materiasBibliotecas.add(livro);
            System.out.println("Livro Cadastrado!");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void CriarRevista(String titulo, Integer anoPublicacao, String editora, Integer edicao) {
        try {
            Revista revista = new Revista(titulo, anoPublicacao, editora, edicao);
            materiasBibliotecas.add(revista);
            System.out.println("Revista cadastrada!");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void CriarArtigoCientifico(String titulo, Integer anoPublicacao, String publicacao, String DOI, String autores) {
        try {
            ArtigoCientifico artigoCientifico = new ArtigoCientifico(titulo, anoPublicacao, publicacao, DOI, autores);
            materiasBibliotecas.add(artigoCientifico);
            System.out.println("Artigo científico Cadastrado!");

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void materiaisDisponiveis() {
        if (!materiasBibliotecas.isEmpty()) {
            for (MaterialBiblioteca material : materiasBibliotecas) {
                if (material.getDisponibilidade()) {
                    System.out.println(material);
                }
                else {
                    System.out.println("Não possui materiais disponíveis!");
                }
            }
        } else {
            System.out.println("Não possui materiais disponíveis!");
        }
    }

    public void TodosMateriais() {
        if (!materiasBibliotecas.isEmpty()) {
            for (MaterialBiblioteca material : materiasBibliotecas) {
                System.out.println(material);
            }
        } else {
            System.out.println("Não possui materiais cadastrados!");
        }
    }

    public MaterialBiblioteca BuscarTitulo(String titulo) {
        if (!materiasBibliotecas.isEmpty()) {
            for (MaterialBiblioteca material : materiasBibliotecas) {
                if (material.getTitulo().equalsIgnoreCase(titulo)) {
                    return material;
                }
            }
        } else {
            System.out.println("Sem materiais cadastrados!");
            return null;
        }
        return null;
    }

    public void CriarUsuario(String nomeLeitor, String CPF, String email){
        if(!UsuarioExiste(CPF)){
            Usuario novoUsuario = new Usuario(nomeLeitor, CPF, email);
            usuarios.add(novoUsuario);
        }
        else {
            System.out.println("Usuario já cadastrado");
        }
    }

    public boolean UsuarioExiste(String CPF){
        for(Usuario usuario : usuarios){
            if(usuario.getCPF().equalsIgnoreCase(CPF)){
                return true;
            }
        }
        return false;
    }

}
