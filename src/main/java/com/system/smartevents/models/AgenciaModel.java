//classe AGENCIA

package com.system.smartevents.models;

import com.system.smartevents.models.enums.TipoAgencia;


import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

//criação da tabela no banco de dados
@Entity
@Table(name="TB_AGENCIA")
public class AgenciaModel extends RepresentationModel<AgenciaModel> implements Serializable {

    private static final long serialVersionUID = 1L;

    //Declaração de atributos

    //Anotação @Id para identificar chave primaria do banco de dados
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer   idAgencia;
    private Integer tipo;
    private String cnpj;
    private String nomeRegistro;
    private String nomeFantasia;
    private String email;
    private String whatsApp;
    private String telefone;

   // @JoinColumn(name="id_agencia")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agenciaModel")
    private List<MembroModel> membroModels;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "agenciaModel")
    private List<EventoModel> eventoModels;


    //Declaração de GETs e SETs para estabelecer relações e chamadas no decorrer da execução da aplicação

    public void setEventoModels(List<EventoModel> eventoModels) {
        this.eventoModels = eventoModels;
    }

    public List<MembroModel> getMembroModels() {
        return membroModels;
   }

    public void setMembroModels(List<MembroModel> membroModels) {
        this.membroModels = membroModels;
    }

    public Integer getIdAgencia() {
        return idAgencia;
    }

    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }

    public String getTipo() {
        TipoAgencia x = TipoAgencia.toEnum(tipo);
        return x.getDescricao();
    }

    public void setTipo(Integer tipo) {
        TipoAgencia x = TipoAgencia.toEnum(tipo);
        this.tipo = x.getCod();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
    	
//    	if (ValidaCNPJ.isCNPJ(cnpj)) {
//        this.cnpj = cnpj;
//    	}else{
//    		System.out.println("CNPJ INVALIDO");    	}
    	this.cnpj = cnpj;
    }

    public String getNomeRegistro() {
        return nomeRegistro;
    }

    public void setNomeRegistro(String nomeRegistro) {
        this.nomeRegistro = nomeRegistro;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWhatsApp() {
        return whatsApp;
    }

    public void setWhatsApp(String whatsApp) {
        this.whatsApp = whatsApp;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}