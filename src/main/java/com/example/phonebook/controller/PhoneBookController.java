package com.example.phonebook.controller;

import com.example.phonebook.exception.ResourceNotFoundException;
import com.example.phonebook.model.PhoneBook;
import com.example.phonebook.repository.PhoneBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PhoneBookController {

    @Autowired
    PhoneBookRepository noteRepository;

    // Get All Notes
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/contacts")
    public List<PhoneBook> getAllNotes() {
        return noteRepository.findAll();
    }
    // Create a new Note
    
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value = "/contacts", method = RequestMethod.POST)
    public PhoneBook createNote(@RequestBody PhoneBook note) {
        return noteRepository.save(note);
    }
    // Get a Single Note
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/contacts/{id}")
    public PhoneBook getNoteById(@PathVariable(value = "id") Long noteId) {
        return noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }
    // Update a Note
    @CrossOrigin(origins = "http://localhost:4200")	
    @PutMapping("/contacts/{id}")
    public PhoneBook updateNote(@PathVariable(value = "id") Long noteId,
                                            @Valid  PhoneBook noteDetails) {

        PhoneBook note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        note.setName(noteDetails.getName());
        note.setNumber(noteDetails.getNumber());

        PhoneBook updatedNote = noteRepository.save(note);
        return updatedNote;
    }
    // Delete a Note
    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long noteId) {
        PhoneBook note = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        noteRepository.delete(note);

        return ResponseEntity.ok().build();
    }
    
}
