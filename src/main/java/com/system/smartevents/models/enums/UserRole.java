package com.system.smartevents.models.enums;

public enum UserRole {

             ADMIN("admin"),
    GERENTE_EVENTO("Gerente_Evento"),
    GERENTE_MUSICA("Gerente_Musica"),
          PRODUTOR("Produtor_Musical");

    private String role;

    UserRole(String role){
        this.role=role;
    }

    public String getRole(){
        return role;
    }
}
