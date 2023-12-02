//classe AGENDAMENTO

package com.system.smartevents.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

//criação da tabela no banco de dados
@Entity
@Table(name="TB_AGENDAMENTO")
public class AgendamentoModel  extends RepresentationModel<AgendamentoModel> implements Serializable {

	private static final long serialVersionUID = 1L;

    //Declaração de atributos
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAgendamento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agencia_evento")
    private AgenciaModel agenciaEventos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitante")
    private MembroModel solicitanteEventos;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_solicitado")
    private MembroModel solicitadoMusica;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento")
    private EventoModel evento;
    private String dataAgendamento;
    private boolean confirmacao;

    //Declaração de GETs e SETs para estabelecer relações e chamadas no decorrer da execução da aplicação

    public Integer getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(Integer idAgendamento) {
        this.idAgendamento = idAgendamento;
    }


    public String getAgenciaEventos() {
        return agenciaEventos.getNomeFantasia();
    }

    public Integer getIdAgenciaEventos() {
        return agenciaEventos.getIdAgencia();
    }

    public void setAgenciaEventos(AgenciaModel agenciaEventos) {
        this.agenciaEventos = agenciaEventos;
    }


    public String getSolicitanteEventos() {
        return solicitanteEventos.getNome();
    }

    public Integer getIdSolicitanteEventos() {
        return solicitanteEventos.getIdMembro();
    }
    
    
    public void setSolicitanteEventos(MembroModel solicitanteEventos) {
        this.solicitanteEventos = solicitanteEventos;
    }

    public String getSolicitadoMusica() {
        return solicitadoMusica.getNome();
    }
    
    
    public String getImageSolicitadoMusica() {
        return solicitadoMusica.getImage();
    }
    
    public Integer getIdSolicitadoMusica() {
        return solicitadoMusica.getIdMembro();
    }

    public void setSolicitadoMusica(MembroModel solicitadoMusica) {
        this.solicitadoMusica = solicitadoMusica;
    }


    public String getEvento() {
       return evento.getNomeEvento();
   }
  
    public Integer getIdEvento() {
      return evento.getIdEvento();
  }

    public void setEvento(EventoModel evento) {
        this.evento = evento;
    }


    public String getDataAgendamento() {
        return dataAgendamento;
    }

    public void setDataAgendamento(String dataAgendamento) {
        this.dataAgendamento = dataAgendamento;
    }

    public boolean isConfirmacao() {
        return confirmacao;
    }

    public void setConfirmacao(boolean confirmacao) {
        this.confirmacao = confirmacao;
    }
}
