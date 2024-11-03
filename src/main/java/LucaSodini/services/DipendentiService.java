package LucaSodini.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import LucaSodini.entities.Dipendente;
import LucaSodini.exceptions.BadRequestException;
import LucaSodini.exceptions.NotFoundException;
import LucaSodini.repositories.DipendentiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Service
public class DipendentiService {

    @Autowired
    private DipendentiRepository dipendentiRepository;

    @Autowired
    private Cloudinary cloudinary;

    public Dipendente saveDipendente(Dipendente dipendente) {
        dipendentiRepository.findByEmail(dipendente.getEmail()).ifPresent(existingDipendente -> {
            throw new BadRequestException("Email " + dipendente.getEmail() + " già in uso.");
        });
        return dipendentiRepository.save(dipendente);
    }

    public Page<Dipendente> findAllDipendenti(int page, int size) {
        return dipendentiRepository.findAll(PageRequest.of(page, size));
    }

    public Dipendente findDipendenteById(UUID id) {
        return dipendentiRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente updateDipendente(UUID id, Dipendente updatedDipendente) {
        Dipendente existingDipendente = findDipendenteById(id);

        existingDipendente.setNome(updatedDipendente.getNome());
        existingDipendente.setCognome(updatedDipendente.getCognome());
        existingDipendente.setEmail(updatedDipendente.getEmail());

        return dipendentiRepository.save(existingDipendente);
    }

    public void deleteDipendente(UUID id) {
        Dipendente dipendente = findDipendenteById(id);
        dipendentiRepository.delete(dipendente);
    }

    public String uploadAvatar(UUID dipendenteId, MultipartFile file) {
        Dipendente dipendente = findDipendenteById(dipendenteId);

        try {
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String avatarUrl = (String) uploadResult.get("url");

            dipendente.setAvatarURL(avatarUrl);
            dipendentiRepository.save(dipendente);

            return avatarUrl;
        } catch (IOException e) {
            throw new BadRequestException("Errore durante il caricamento dell'avatar: " + e.getMessage());
        }
    }
}
