//classe EVENTO
package com.system.smartevents.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;


import java.io.Serializable;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//criação da tabela no banco de dados
@Entity
@Table(name="TB_EVENTO")
public class EventoModel extends RepresentationModel<EventoModel> implements Serializable {

    private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int  idEvento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agencia")
    private AgenciaModel agenciaModel;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "TB_EVENTO_MEMBRO",
//                   joinColumns = { @JoinColumn(name = "id_evento",referencedColumnName = "idEvento",nullable = false,updatable = false)},
//            inverseJoinColumns = {@JoinColumn( name = "id_membro",referencedColumnName = "idMembro",nullable = false,updatable = false)}
//    )
//   // private List<MembroModel> membroModels = new ArrayList<MembroModel>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_membro")
    private MembroModel membroModel;
    private String nomeEvento;
    private String dataInicial;
    private String dataFinal;
    private int capacidade;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "evento")
    private List<AgendamentoModel> agendamentoModels;
    
    
    public List<AgendamentoModel> getAgendamentoModel() {
    	
    	return agendamentoModels;
   }


	public String getAgenciaModel() {
        return agenciaModel.getNomeFantasia();
   }
   	

 public void setAgenciaModel(AgenciaModel agenciaModel) {
      this.agenciaModel = agenciaModel;
  }

       public String getMembroModel() {
		return membroModel.getNome();
	}


	public void setMembroModel(MembroModel membroModel) {
		this.membroModel = membroModel;
	}



	public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }



    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public String getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(String dataInicial) {
        this.dataInicial = dataInicial;
    }

    public String getDataFinal() {
   
        return dataFinal;
    }

    public void setDataFinal(String dataFinal) {
        this.dataFinal = dataFinal;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }
}