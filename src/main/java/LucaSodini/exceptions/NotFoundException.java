package LucaSodini.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("Resource with ID " + id + " not found.");
    }
}

