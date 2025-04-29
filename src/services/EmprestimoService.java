package services;

import models.Emprestimo;
import models.MaterialBiblioteca;
import models.Usuario;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmprestimoService {
    String path = "./Emprestimos";
    private BibliotecaService bibliotecaService;

    ArrayList<Emprestimo> emprestimos = new ArrayList<>();

    public EmprestimoService(BibliotecaService bibliotecaService) {
        this.bibliotecaService = bibliotecaService;
    }

    public void CriarEmprestimo(String CPF, String materialBiblioteca){
        MaterialBiblioteca materialBusca = bibliotecaService.BuscarTitulo(materialBiblioteca);
        if(bibliotecaService.UsuarioExiste(CPF)){
            Emprestimo emprestimo = new Emprestimo(CPF, materialBusca);
            MudarStatus(materialBiblioteca);
            emprestimos.add(emprestimo);
        }
        else{
            System.out.println("Usuário não encontrado!");
        }
    }

    public void TodosOsEmprestimos(){
        for (Emprestimo emp : emprestimos){
            System.out.println(emp);
        }
    }

    public void MudarStatus(String titulo){
        MaterialBiblioteca material = bibliotecaService.BuscarTitulo(titulo);
        boolean novoStatus = !material.getDisponibilidade();
        material.setDisponibilidade(novoStatus);
    }

    public void Devolucao(String CPF, String titulo){
        for(Emprestimo em : emprestimos){
            if(em.getUsuario().equalsIgnoreCase(CPF) && em.getMaterialBiblioteca().getTitulo().equalsIgnoreCase(titulo)){
                em.setDevolucao(LocalDate.now());
                em.CalculaMulta();
                String material = em.getMaterialBiblioteca().getTitulo();
                MudarStatus(material);
                System.out.println("Multa: " + em.getMulta());
            }
        }
    }

    public void exportEmprestimo() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (Emprestimo emprestimo : emprestimos) {
                String linha = emprestimo.getUsuario() + "##" +
                        emprestimo.getMaterialBiblioteca().getTitulo() + "##" +
                        emprestimo.getDataEmprestimo().toString() + "##" +
                        (emprestimo.getDevolucao() != null ?
                                emprestimo.getDevolucao().toString() : "null") + "##" +
                        (emprestimo.getMulta() != null ?
                                emprestimo.getMulta().toString() : "null");
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importEmprestimo() {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split("##");
                String CPF = dados[0];
                String tituloMaterial = dados[1];
                LocalDate dataEmprestimo = LocalDate.parse(dados[2]);
                LocalDate dataDevolucao = dados[3].equals("null") ? null : LocalDate.parse(dados[3]);
                Double multa = dados[4].equals("null") ? null : Double.parseDouble(dados[4]);

                MaterialBiblioteca material = bibliotecaService.BuscarTitulo(tituloMaterial);

                if (material != null && bibliotecaService.UsuarioExiste(CPF)) {
                    Emprestimo emprestimo = new Emprestimo(CPF, material, dataEmprestimo, dataDevolucao, multa);
                    emprestimos.add(emprestimo);

                    if (dataDevolucao == null) {
                        MudarStatus(tituloMaterial);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao importar empréstimos: ");
        }
    }
}
