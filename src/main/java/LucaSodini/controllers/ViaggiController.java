package LucaSodini.controllers;

import LucaSodini.entities.Viaggio;
import LucaSodini.enums.StatoViaggio;
import LucaSodini.payloads.NewViaggioDTO;
import LucaSodini.services.ViaggiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {
    @Autowired
    private ViaggiService viaggiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio createViaggio(@RequestBody @Validated NewViaggioDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new RuntimeException("Invalid viaggio data!");
        }
        return viaggiService.createViaggio(body);
    }

    @GetMapping("/{id}")
    public Viaggio getViaggio(@PathVariable UUID id) {
        return viaggiService.getViaggio(id);
    }

    @PutMapping("/{id}")
    public Viaggio updateViaggio(@PathVariable UUID id, @RequestBody @Validated NewViaggioDTO body) {
        return viaggiService.updateViaggio(id, body);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteViaggio(@PathVariable UUID id) {
        viaggiService.deleteViaggio(id);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio updateViaggioStato(@PathVariable UUID id, @RequestParam StatoViaggio stato) {
        return viaggiService.updateViaggioStato(id, stato);
    }
}
