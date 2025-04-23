package model;

import java.sql.Date;

public class financasModel {
    
    private int idValor;
    private String nome;
    private String classificacao;
    private Double valor;
    private Date dataRealizado;
    private Date dataCadastrado;

    public financasModel(int idValor, String nome, String classificacao, Date dataRealizado, Date dataCadastrado, Double valor) {
        this.idValor = idValor;
        this.nome = nome;
        this.classificacao = classificacao;
        this.dataRealizado = dataRealizado;
        this.dataCadastrado = dataCadastrado;
        this.valor = valor;
    }
    
    public int getIdValor() {
        return idValor;
    }

    public void setIdValor(int idValor) {
        this.idValor = idValor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public Date getDataRealizado() {
        return dataRealizado;
    }

    public void setDataRealizado(Date dataRealizado) {
        this.dataRealizado = dataRealizado;
    }

    public Date getDataCadastrado() {
        return dataCadastrado;
    }

    public void setDataCadastrado(Date dataCadastrado) {
        this.dataCadastrado = dataCadastrado;
    }
    
    @Override
    public String toString(){
        return "id: " + idValor +
                "\nnome: " + nome +
                "\nvalor: R$" + valor + 
                "\nclassificacao: " + classificacao +
                "\ndata realizado: " + dataRealizado +
                "\ndata de Cadastro: " + dataCadastrado;
    }
    
}
