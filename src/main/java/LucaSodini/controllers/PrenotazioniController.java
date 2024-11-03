package LucaSodini.controllers;

import LucaSodini.entities.Prenotazione;
import LucaSodini.payloads.NewPrenotazioneDTO;
import LucaSodini.services.PrenotazioniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioniController {

    @Autowired
    private PrenotazioniService prenotazioniService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione createPrenotazione(@RequestBody @Validated NewPrenotazioneDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new RuntimeException("Errore nella richiesta di prenotazione!");
        }
        return prenotazioniService.createPrenotazione(body);
    }

    @GetMapping("/{id}")
    public Prenotazione getPrenotazione(@PathVariable UUID id) {
        return prenotazioniService.getPrenotazione(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable UUID id) {
        prenotazioniService.deletePrenotazione(id);
    }
}
