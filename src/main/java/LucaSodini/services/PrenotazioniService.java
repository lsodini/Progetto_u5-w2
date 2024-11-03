package LucaSodini.services;

import LucaSodini.entities.Dipendente;
import LucaSodini.entities.Prenotazione;
import LucaSodini.entities.Viaggio;
import LucaSodini.payloads.NewPrenotazioneDTO;
import LucaSodini.repositories.DipendentiRepository;
import LucaSodini.repositories.PrenotazioniRepository;
import LucaSodini.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioniService {
    @Autowired
    private PrenotazioniRepository prenotazioneRepository;

    @Autowired
    private DipendentiRepository dipendenteRepository;

    @Autowired
    private ViaggiRepository viaggioRepository;

    public Prenotazione createPrenotazione(NewPrenotazioneDTO dto) {
        Dipendente dipendente = dipendenteRepository.findById(dto.dipendenteId()).orElseThrow();
        Viaggio viaggio = viaggioRepository.findById(dto.viaggioId()).orElseThrow();

        if (prenotazioneRepository.existsByDipendenteAndDataRichiesta(dipendente, LocalDate.now())) {
            throw new RuntimeException("Il dipendente ha già una prenotazione per oggi.");
        }

        Prenotazione prenotazione = new Prenotazione(dipendente, viaggio, LocalDate.now(), dto.note());
        return prenotazioneRepository.save(prenotazione);
    }

    public Prenotazione getPrenotazione(UUID id) {
        return prenotazioneRepository.findById(id).orElseThrow();
    }

    public void deletePrenotazione(UUID id) {
        prenotazioneRepository.deleteById(id);
    }
}
