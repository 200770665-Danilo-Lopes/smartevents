
package com.system.smartevents.models.enums;
public enum Funcao {


        GERENTE_EVENTO(1,"Gerente_Evento"),
        GERENTE_MUSICA(2,"Gerente_Musica"),
              PRODUTOR(3,"Produtor_Musical");


    private int cod;
    private String descricao;


    private Funcao(int cod, String descricao) {
        this.cod=cod;
        this.descricao=descricao;
    }


    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }


    public static Funcao toEnum(Integer cod){

        if(cod ==null){
            return null;
        }

        for(Funcao x : Funcao.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Função: "+cod+" inválida, informar: 1-GERENTE,2-SECRETARIO OU 3-PRODUTOR MUSICAL");
    }


}