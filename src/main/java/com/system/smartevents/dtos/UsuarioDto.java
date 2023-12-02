package com.system.smartevents.dtos;

import com.system.smartevents.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(
       @NotNull String login,
       @NotNull String password,
       @NotNull UserRole role
) {

}
