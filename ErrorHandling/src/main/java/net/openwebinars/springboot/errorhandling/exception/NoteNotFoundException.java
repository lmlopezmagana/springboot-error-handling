package net.openwebinars.springboot.errorhandling.exception;

import jakarta.persistence.EntityNotFoundException;

public class NoteNotFoundException extends EntityNotFoundException {

    public NoteNotFoundException() {
        super("The note could not be found");
    }

    public NoteNotFoundException(Long id) {
        super(String.format("The note with id %d could not be found", id));
    }

}

//note.notfound=The note with id {0} could not be found