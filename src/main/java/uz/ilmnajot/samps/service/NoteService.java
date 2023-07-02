package uz.ilmnajot.samps.service;

import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.NoteDTO;
import uz.ilmnajot.samps.entity.Note;
import uz.ilmnajot.samps.entity.User;

import java.util.List;
import java.util.Optional;

public interface NoteService {
    ApiResponse saveNote(NoteDTO noteDTO, User user);

    List<Note> getAllNotes();

    Optional<Note> getNote(Long id);

    void deleteNote(Long id);
}
