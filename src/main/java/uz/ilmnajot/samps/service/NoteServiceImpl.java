package uz.ilmnajot.samps.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.ilmnajot.samps.apiResponse.ApiResponse;
import uz.ilmnajot.samps.dto.NoteDTO;
import uz.ilmnajot.samps.entity.Note;
import uz.ilmnajot.samps.entity.User;
import uz.ilmnajot.samps.repository.NoteRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class NoteServiceImpl implements NoteService{

    private final NoteRepository noteRepository;

    @Override
    public ApiResponse saveNote(NoteDTO noteDTO, User user) {
        boolean byContent = noteRepository.findByContentAndUserId(noteDTO.getContent(), user.getId());
        if (byContent){
            return new ApiResponse("you created this content already", false);
        }
        Note note = new Note(
                noteDTO.getName(),
                noteDTO.getContent(),
                user
        );
        noteRepository.save(note);
        return new ApiResponse("Note has been created",true);
    }

    @Override
    public List<Note> getAllNotes() {
        return noteRepository.findAll(Sort.by("id"));
    }
    @Override
    public Optional<Note> getNote(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()){
            return optionalNote;
        }
        return Optional.empty();
    }
    @Override
    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}
