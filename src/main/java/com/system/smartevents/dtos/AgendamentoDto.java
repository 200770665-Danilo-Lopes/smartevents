package com.system.smartevents.dtos;

import com.system.smartevents.models.AgenciaModel;
import com.system.smartevents.models.EventoModel;
import com.system.smartevents.models.MembroModel;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record AgendamentoDto(
            AgenciaModel agenciaEventos,
            MembroModel  solicitanteEventos,
            MembroModel  solicitadoMusica,
            EventoModel  evento,
            String dataAgendamento,
            boolean      confirmacao
) {
}
