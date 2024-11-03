package LucaSodini.payloads;

import LucaSodini.enums.StatoViaggio;
import jakarta.validation.constraints.NotEmpty;
import java.time.LocalDate;

public record NewViaggioDTO(
        @NotEmpty String destinazione,
        LocalDate dataPartenza,
        LocalDate dataRitorno,
        String descrizione,
        StatoViaggio stato
) {}

