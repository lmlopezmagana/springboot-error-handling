package net.openwebinars.springboot.errorhandling.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import net.openwebinars.springboot.errorhandling.model.Note;
import net.openwebinars.springboot.errorhandling.repo.NoteRepository;
import net.openwebinars.springboot.errorhandling.service.NoteService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {

    //private final NoteRepository repository;
    private final NoteService noteService;

    @GetMapping("/")
    //public ResponseEntity<List<Note>> getAll() {
    public List<Note> getAll() {
        // Utilizamos un método común para devolver la respuesta de todos los List<Note>
        //return buildResponseOfAList(repository.findAll());

        return noteService.findAll();

    }


    @GetMapping("/{id}")
    //public ResponseEntity<Note> getById(@PathVariable Long id) {
    public Note getById(@PathVariable Long id) {
        /*
            El método ResponseEntity.of recibe como argumento un Optional<?> y devuelve
                - 200 Ok si Optional.isPresent() == true
                - 404 Not Found si Optional.isEmpty() == true
         */
        //return ResponseEntity.of(repository.findById(id));

        return noteService.findById(id);

    }


    @GetMapping("/author/{author}")
    //public ResponseEntity<List<Note>> getByAuthor(@PathVariable String author) {
    public List<Note> getByAuthor(@PathVariable String author) {
        // Utilizamos un método comun para devolver la respuesta de todos los List<Note>
        //return buildResponseOfAList(repository.findByAuthor(author));

        return noteService.getByAuthor(author);
    }



    @PostMapping("/")
    public ResponseEntity<Note> createNewNote(@Valid @RequestBody Note note) {

        // En este método sí queremos gestionar la respuesta, para devolver 201
        Note created = noteService.save(note);

        URI createdURI = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId()).toUri();

        /*
            Habitualmente, la respuesta correcta de una petición POST es 201 Created.
            Adicionalmente, se puede devolver un encabezado Location con la URI que
            nos permite realizar la petición GET al recurso recién creado.
         */
        return ResponseEntity
                .created(createdURI)
                .body(created);

    }

    @PutMapping("/{id}")
    //public ResponseEntity<Note> edit(@PathVariable Long id, @RequestBody Note edited) {
    public Note edit(@PathVariable Long id, @RequestBody Note edited) {
        return noteService.edit(id, edited);



    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {


        noteService.delete(id);

        return ResponseEntity.noContent().build();

    }




}
