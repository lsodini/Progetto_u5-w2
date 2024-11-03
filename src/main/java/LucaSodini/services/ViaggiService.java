package LucaSodini.services;


import LucaSodini.entities.Viaggio;
import LucaSodini.enums.StatoViaggio;
import LucaSodini.exceptions.NotFoundException;
import LucaSodini.payloads.NewViaggioDTO;
import LucaSodini.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ViaggiService {
    @Autowired
    private ViaggiRepository viaggiRepository;

    public Viaggio createViaggio(NewViaggioDTO dto) {
        Viaggio viaggio = new Viaggio(dto.destinazione(), dto.dataPartenza(), dto.dataRitorno(), dto.descrizione());
        return viaggiRepository.save(viaggio);
    }

    public Viaggio getViaggio(UUID id) {
        return viaggiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio updateViaggio(UUID id, NewViaggioDTO dto) {
        Viaggio viaggio = getViaggio(id);
        viaggio.setDestinazione(dto.destinazione());
        viaggio.setDataPartenza(dto.dataPartenza());
        viaggio.setDataRitorno(dto.dataRitorno());
        viaggio.setDescrizione(dto.descrizione());
        return viaggiRepository.save(viaggio);
    }

    public void deleteViaggio(UUID id) {
        Viaggio viaggio = getViaggio(id);
        viaggiRepository.delete(viaggio);
    }

    public Viaggio updateViaggioStato(UUID id, StatoViaggio stato) {
        Viaggio viaggio = getViaggio(id);
        viaggio.setStato(stato);
        return viaggiRepository.save(viaggio);
    }
}


