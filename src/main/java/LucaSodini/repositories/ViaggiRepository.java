package LucaSodini.repositories;



import LucaSodini.entities.Dipendente;
import LucaSodini.entities.Viaggio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ViaggiRepository extends JpaRepository<Viaggio, UUID> {
    Optional<Viaggio> findById(UUID id);
}
