package services;

import models.Emprestimo;
import models.MaterialBiblioteca;
import models.Usuario;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmprestimoService {
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
}
