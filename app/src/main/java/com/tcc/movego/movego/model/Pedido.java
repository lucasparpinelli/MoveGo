package com.tcc.movego.movego.model;

import java.util.Date;

/**
 * Created by solange on 07/05/2016.
 */
public class Pedido {

    private String origem, destino, destinatario, embalagem;
    private long dataExpedicao, dataEntrega;
    private String viacao;
    private String tipoServico;
    private String tipoEncomenda;

    public Pedido(){}

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(String embalagem) {
        this.embalagem = embalagem;
    }

    public long getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(long dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public long getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(long dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getViacao() {
        return viacao;
    }

    public void setViacao(String viacao) {
        this.viacao = viacao;
    }

    public String getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(String tipoServico) {
        this.tipoServico = tipoServico;
    }

    public String getTipoEncomenda() {
        return tipoEncomenda;
    }

    public void setTipoEncomenda(String tipoEncomenda) {
        this.tipoEncomenda = tipoEncomenda;
    }
}
