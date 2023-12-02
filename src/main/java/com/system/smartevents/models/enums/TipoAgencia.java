
package com.system.smartevents.models.enums;

public enum TipoAgencia {


       EVENTO(1,"EVENTOS"),
       MUSICA(2,"MUSICA");



    private int cod;
    private String descricao;


    private TipoAgencia(int cod, String descricao) {
        this.cod=cod;
        this.descricao=descricao;
    }


    public int getCod(){
        return cod;
    }

    public String getDescricao(){
        return descricao;
    }


    public static TipoAgencia toEnum(Integer cod){

        if(cod ==null){
            return null;
        }

        for(TipoAgencia x : TipoAgencia.values()) {
            if(cod.equals(x.getCod())) {
                return x;
            }
        }

        throw new IllegalArgumentException("Função: "+cod+" inválida, informar: 1-EVENTOS,2-MUSICA");
    }


}