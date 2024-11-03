package LucaSodini.controllers;


import LucaSodini.entities.Dipendente;
import LucaSodini.payloads.NewDipendenteDTO;
import LucaSodini.services.DipendentiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/dipendenti")
public class DipendentiController {
    @Autowired
    private DipendentiService dipendentiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated NewDipendenteDTO body, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new RuntimeException("Error in request body!");
        }
        return dipendentiService.save(body);
    }

    @PatchMapping("/{id}/avatar")
    public String uploadAvatar(@PathVariable UUID id, @RequestParam("avatar") MultipartFile file) {
        return dipendentiService.uploadAvatar(id, file);
    }

}
