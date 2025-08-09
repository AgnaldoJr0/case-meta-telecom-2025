package com.case2025.meta.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClientRequestDTO(
        @NotBlank(message = "Campo Nome é Obrigatório!") String name,
        @NotNull(message = "Campo Idade é Obrigatório!") @Min(value = 1, message = "Idade deve ser maior que 0!") Integer age,
        @Email(message = "Email inválido!") String email,
        @NotNull(message = "Nenhum usuário encontrado!") Long id

) {

}
