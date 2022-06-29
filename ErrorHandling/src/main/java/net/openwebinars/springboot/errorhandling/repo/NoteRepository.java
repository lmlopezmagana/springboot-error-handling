package net.openwebinars.springboot.errorhandling.repo;

import net.openwebinars.springboot.errorhandling.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByAuthor(String author);


}
