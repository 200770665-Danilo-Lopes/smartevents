
package com.system.smartevents.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;





public record AgenciaDto(
                   Integer tipo,
                 @NotNull @NotBlank  String cnpj,
                 @NotBlank   String nomeRegistro,
                 @NotBlank  String nomeFantasia,
                 @NotBlank   String email,
                 @NotBlank   String whatsApp,
                 @NotBlank   String telefone//,
         //List<MembroModel> membroModels
) {
    }



