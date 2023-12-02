
package com.system.smartevents.dtos;

import com.system.smartevents.models.AgenciaModel;
import com.system.smartevents.models.MembroModel;
import jakarta.validation.constraints.NotBlank;



public record EventoDto(
                        AgenciaModel agenciaModel,
                        MembroModel membroModel,
                        @NotBlank String nomeEvento,
                        @NotBlank String dataInicial,
                        @NotBlank String dataFinal,
                        		     int capacidade) {
}

