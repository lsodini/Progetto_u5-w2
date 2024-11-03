package LucaSodini.entities;

import LucaSodini.enums.StatoViaggio;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue
    private UUID id;
    private String destinazione;
    private LocalDate dataPartenza;
    private LocalDate dataRitorno;
    private String descrizione;

    @Enumerated(EnumType.STRING)
    private StatoViaggio stato;

    public Viaggio(String destinazione, LocalDate dataPartenza, LocalDate dataRitorno, String descrizione) {
        this.destinazione = destinazione;
        this.dataPartenza = dataPartenza;
        this.dataRitorno = dataRitorno;
        this.descrizione = descrizione;
        this.stato = StatoViaggio.IN_PROGRAMMA;
    }
}
