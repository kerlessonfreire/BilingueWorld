package br.com.projeto.BelingueWorld.dto;

public class MensagemDTO {
    private String id;
    private String conteudo;
    private String data;
    private String idRemetente;
    private String nomeRemetente;
    private String idNivel;

    public MensagemDTO(){
        this.id = id;
        this.conteudo = conteudo;
        this.data = data;
        this.idNivel = idNivel;
        this.idRemetente = idRemetente;
        this.nomeRemetente = nomeRemetente;
    }

    public String getIdNivel() {
        return idNivel;
    }

    public void setIdNivel(String idNivel) {
        this.idNivel = idNivel;
    }

     public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIdRemetente() {
        return idRemetente;
    }

    public void setIdRemetente(String idRemetente) {
        this.idRemetente = idRemetente;
    }

    public String getNomeRemetente(){
        return this.nomeRemetente;
    }

    public void setNomeRemetente(String nomeRemetente) {
        this.nomeRemetente = nomeRemetente;
    }


}
