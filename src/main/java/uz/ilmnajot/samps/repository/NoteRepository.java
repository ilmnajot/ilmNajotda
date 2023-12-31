package uz.ilmnajot.samps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.samps.entity.Note;


@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    boolean findByContentAndUserId(String content, Long userId);
}
