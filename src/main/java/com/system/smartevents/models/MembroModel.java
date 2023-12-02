
package com.system.smartevents.models;

import com.system.smartevents.models.enums.Funcao;

import com.system.smartevents.models.enums.UserRole;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;


@Entity
@Table(name="TB_MEMBRO")
public class MembroModel extends RepresentationModel<MembroModel> implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMembro;
    private Integer funcao;
    private String nome;
    private String email;
    private String whatsApp;
    private String telefone;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agencia")
    private AgenciaModel agenciaModel;
//    
//    @OneToOne
//    @JoinColumn(name = "usuario_id", unique = true)
//    private UsuarioModel usuarioModel;
//
//    public String getUsuarioModel() {
//        return usuarioModel.getLogin();
//    }
//
//    public void setUsuarioModel(UsuarioModel usuarioModel) {
//        this.usuarioModel = usuarioModel;
//    }

    public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAgenciaModel() {
		return agenciaModel.getNomeFantasia();
	}

    public Integer getIdAgenciaModel() {
		return agenciaModel.getIdAgencia();
	}
	public void setAgenciaModel(AgenciaModel agenciaModel) {
          this.agenciaModel = agenciaModel;
    }

    public void setIdMembro(Integer idMembro) {
        this.idMembro = idMembro;
    }
    public int getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(int idMembro) {
        this.idMembro = idMembro;
    }

   public String getFuncao() {
   Funcao x = Funcao.toEnum(funcao);
   return x.getDescricao();
   }
   
   public Integer getIdFuncao() {
	   Funcao x = Funcao.toEnum(funcao);
	   return x.getCod();
 }

    public void setFuncao(Integer funcao) {
        Funcao x = Funcao.toEnum(funcao);
        this.funcao = x.getCod();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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