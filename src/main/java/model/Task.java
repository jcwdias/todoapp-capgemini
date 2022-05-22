package model;

import java.util.Date;

public class Task {

    private int id;
    private int idProject;
    private String nome;
    private String descricao;
    private String observacoes;
    private boolean completo;
    private Date prazo;
    private Date dataCriacao;
    private Date dataAtualizacao;

    public Task(int id, int idProject, String nome, String descricao, String observacoes, boolean completo, Date prazo, Date dataCriacao, Date dataAtualizacao) {
        this.id = id;
        this.idProject = idProject;
        this.nome = nome;
        this.descricao = descricao;
        this.observacoes = observacoes;
        this.completo = completo;
        this.prazo = prazo;
        this.dataCriacao = dataCriacao;
        this.dataAtualizacao = dataAtualizacao;
    }

    public Task(){
        this.dataCriacao = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProject() {
        return idProject;
    }

    public void setIdProject(int idProject) {
        this.idProject = idProject;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public boolean isCompleto() {
        return completo;
    }

    public void setCompleto(boolean completo) {
        this.completo = completo;
    }

    public Date getPrazo() {
        return prazo;
    }

    public void setPrazo(Date prazo) {
        this.prazo = prazo;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", idProject=" + idProject +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", observacoes='" + observacoes + '\'' +
                ", completo=" + completo +
                ", prazo=" + prazo +
                ", dataCriacao=" + dataCriacao +
                ", dataAtualizacao=" + dataAtualizacao +
                '}';
    }
}
