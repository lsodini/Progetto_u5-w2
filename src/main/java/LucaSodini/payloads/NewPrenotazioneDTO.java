package LucaSodini.payloads;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record NewPrenotazioneDTO(
        @NotNull UUID dipendenteId,
        @NotNull UUID viaggioId,
        String note
) {}
