package LucaSodini.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewDipendenteDTO(
        @NotEmpty String username,
        @NotEmpty @Size(min = 2, max = 40) String nome,
        @NotEmpty @Size(min = 2, max = 40) String cognome,
        @NotEmpty @Email String email
) {}
