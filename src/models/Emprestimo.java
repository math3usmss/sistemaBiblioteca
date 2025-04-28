package models;

import Enums.TiposMaterial;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {

    private String usuarioCPF;
    private MaterialBiblioteca materialBiblioteca;
    private LocalDate dataEmprestimo;
    private LocalDate devolucao;
    private Double multa;

    public Emprestimo(){}

    public Emprestimo(String usuarioCPF, MaterialBiblioteca materialBiblioteca) {
        this.usuarioCPF = usuarioCPF;
        this.materialBiblioteca = materialBiblioteca;
        this.dataEmprestimo = LocalDate.now();
    }

    public String getUsuario() {
        return usuarioCPF;
    }

    public MaterialBiblioteca getMaterialBiblioteca() {
        return materialBiblioteca;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate getDevolucao() {
        return devolucao;
    }

    public void setDevolucao(LocalDate devolucao) {
        this.devolucao = devolucao;
    }

    public Double getMulta() {
        return multa;
    }

    public void CalculaMulta(){
        int valorMulta = 0;
        int diaMulta = 0;

        if(getMaterialBiblioteca().getTipoMaterial() == TiposMaterial.LIVRO){
            diaMulta = 14;
            long diferencaDias = ChronoUnit.DAYS.between(getDataEmprestimo(), getDevolucao());
            if(Math.abs(diferencaDias) >= diaMulta){
                this.multa = 15.00;
            }
        }

        if(getMaterialBiblioteca().getTipoMaterial() == TiposMaterial.REVISTA){
            diaMulta = 7;
            long diferencaDias = ChronoUnit.DAYS.between(getDataEmprestimo(), getDevolucao());
            if(Math.abs(diferencaDias) >= diaMulta){
                this.multa = 10.00;
            }
        }

        if(getMaterialBiblioteca().getTipoMaterial() == TiposMaterial.ARTIGO_CIENTIFICO){
            diaMulta = 5;
            long diferencaDias = ChronoUnit.DAYS.between(getDataEmprestimo(), getDevolucao());
            if(Math.abs(diferencaDias) >= diaMulta){
                this.multa = 20.00;
            }
        }
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuarioCPF='" + usuarioCPF + '\'' +
                ", materialBiblioteca=" + materialBiblioteca +
                ", dataEmprestimo=" + dataEmprestimo +
                ", devolucao=" + devolucao +
                ", multa=" + multa +
                '}';
    }
}
