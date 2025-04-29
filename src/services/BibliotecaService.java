package services;

import Enums.TiposMaterial;
import models.*;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BibliotecaService {

    String pathMaterial = "./Materias.txt";
    String pathUsuario = "./Usuario.txt";

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

    public void exportMaterial(){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathMaterial))){
            for(MaterialBiblioteca material : materiasBibliotecas){
               if(material.getTipoMaterial() == TiposMaterial.LIVRO){
                   Livro livro = (Livro) material;
                   String linha = material.getTipoMaterial() + "##" + material.getTitulo()+ "##" + ((Livro) material).getAutor()+ "##" + ((Livro) material).getISBN() + "##" + material.getAnoPublicacao() + "##" + material.getDisponibilidade();
                   bw.write(linha);
                   bw.newLine();
               } else if(material.getTipoMaterial() == TiposMaterial.REVISTA){
                    Revista revista = (Revista) material;
                    String linha = material.getTipoMaterial() + "##" + material.getTitulo() + "##" + ((Revista) material).getEditora() + "##" + ((Revista) material).getEdicao() + "##" + material.getAnoPublicacao() + "##" + material.getDisponibilidade();
                    bw.write(linha);
                    bw.newLine();
               } else if (material.getTipoMaterial() == TiposMaterial.ARTIGO_CIENTIFICO) {
                   ArtigoCientifico artigo = (ArtigoCientifico) material;
                   String linha = material.getTipoMaterial() + "##" + material.getTitulo() + "##" + ((ArtigoCientifico) material).getDOI() + "##" + material.getAnoPublicacao() + ((ArtigoCientifico) material).getPublicacao() + "##" + ((ArtigoCientifico) material).getAutores() + "##" + material.getDisponibilidade();
                   bw.write(linha);
                   bw.newLine();
               }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importMaterial(){
        try(BufferedReader br = new BufferedReader(new FileReader(pathMaterial))){
            String linha;
            while((linha = br.readLine()) != null){
                String[] materialSalvo = linha.split("##");
                switch (materialSalvo[0]){
                    case "LIVRO":
                        Livro livro = new Livro(materialSalvo[2], Integer.parseInt(materialSalvo[3]), materialSalvo[1], Integer.parseInt(materialSalvo[4]), Boolean.parseBoolean(materialSalvo[5]));
                        materiasBibliotecas.add(livro);
                        break;
                    case "REVISTA":
                        Revista revista = new Revista(materialSalvo[1], Integer.parseInt(materialSalvo[4]), materialSalvo[2], Integer.parseInt(materialSalvo[3]), Boolean.parseBoolean(materialSalvo[5]));
                        materiasBibliotecas.add(revista);
                        break;
                    case "ARTIGO_CIENTIFICO":
                        ArrayList<String> autores = new ArrayList<String>(List.of(materialSalvo[5].split(", ")));
                        ArtigoCientifico artigo = new ArtigoCientifico(materialSalvo[1], Integer.parseInt(materialSalvo[4]), materialSalvo[3], materialSalvo[2], String.join(", ", autores), Boolean.parseBoolean(materialSalvo[6]));
                        materiasBibliotecas.add(artigo);
                        break;
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void exportUsuario() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathUsuario))) {
            for (Usuario usuario : usuarios) {
                String linha = usuario.getNome() + "##" +
                        usuario.getCPF() + "##" +
                        usuario.getEmail();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao exportar usuários: ");
        }
    }

    public void importUsuario() {
        try (BufferedReader br = new BufferedReader(new FileReader(pathUsuario))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split("##");

                if (dados.length == 3) {
                    String nome = dados[0];
                    String CPF = dados[1];
                    String email = dados[2];

                    Usuario novoUsuario = new Usuario(nome, CPF, email);
                    usuarios.add(novoUsuario);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao importar usuários: ");
        }
    }

}
