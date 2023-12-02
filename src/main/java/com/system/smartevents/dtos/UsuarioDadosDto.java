package com.system.smartevents.dtos;

import com.system.smartevents.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UsuarioDadosDto(
        String login,
        UserRole role
) {

}
