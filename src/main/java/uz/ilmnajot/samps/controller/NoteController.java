package uz.ilmnajot.samps.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.samps.annotation.CurrentUser;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.NoteDTO;
import uz.ilmnajot.samps.entity.Note;
import uz.ilmnajot.samps.entity.User;
import uz.ilmnajot.samps.service.NoteService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/register")
    public HttpEntity<?> saveNote(@RequestBody NoteDTO noteDTO, @CurrentUser User user) {
        ApiResponse apiResponse = noteService.saveNote(noteDTO, user);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }
    @GetMapping("/all")
    public HttpEntity<?> getAllNotes(){
        List<Note> notes = noteService.getAllNotes();
        return ResponseEntity.ok(notes);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getNote(@RequestParam Long id){
        Optional<Note> optionalNote = noteService.getNote(id);
        return ResponseEntity.status(optionalNote.isPresent()?200:409).body(optionalNote);
    }
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteNote(@RequestParam Long id){
        noteService.deleteNote(id);
        return ResponseEntity.ok(HttpStatus.NO_CONTENT);
    }

}
