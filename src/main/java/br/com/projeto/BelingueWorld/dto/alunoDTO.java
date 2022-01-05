package br.com.projeto.BelingueWorld.dto;

public class alunoDTO {

    private String id;

    private String nome;

    private float n1;

    private float n2;

    private float media;

    private String resultado;

    public alunoDTO(String id, String nome, float n1, float n2, float media, String resultado) {
        this.id = id;
        this.nome = nome;
        this.n1 = n1;
        this.n2 = n2;
        this.media = media;
        this.resultado = resultado;
    }

    public alunoDTO(){
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getN1() {
        return n1;
    }
    public void setN1(float n1) {
        this.n1 = n1;
    }
    public float getN2() {
        return n2;
    }
    public void setN2(float n2) {
        this.n2 = n2;
    }
    public float getMedia() {
        return media;
    }
    public void setMedia(float media) {
        this.media = media;
    }
    public String getResultado() {
        return resultado;
    }
    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "alunoDTO [id=" + id + ", media=" + media + ", n1=" + n1 + ", n2=" + n2 + ", nome=" + nome
                + ", resultado=" + resultado + "]";
    }
}
