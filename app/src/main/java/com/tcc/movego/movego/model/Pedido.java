package com.tcc.movego.movego.model;

import java.util.Date;

/**
 * Created by solange on 07/05/2016.
 */
public class Pedido {

    private String origem, destino, destinatario, embalagem;
    private Date dataExpedicao, dataEntrega;
    private Viacao viacao;
    private TipoServico tipoServico;
    private TipoEncomenda tipoEncomenda;

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

    public Date getDataExpedicao() {
        return dataExpedicao;
    }

    public void setDataExpedicao(Date dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public Date getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(Date dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public Viacao getViacao() {
        return viacao;
    }

    public void setViacao(Viacao viacao) {
        this.viacao = viacao;
    }

    public TipoServico getTipoServico() {
        return tipoServico;
    }

    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }

    public TipoEncomenda getTipoEncomenda() {
        return tipoEncomenda;
    }

    public void setTipoEncomenda(TipoEncomenda tipoEncomenda) {
        this.tipoEncomenda = tipoEncomenda;
    }
}
