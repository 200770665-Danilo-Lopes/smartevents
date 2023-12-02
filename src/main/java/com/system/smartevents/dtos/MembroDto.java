
package com.system.smartevents.dtos;

import com.system.smartevents.models.AgenciaModel;
import com.system.smartevents.models.UsuarioModel;


public record MembroDto(
        Integer funcao,
        String nome,
        String email,
        String whatsApp,
        String telefone,
        String image,
        AgenciaModel agenciaModel) {

}