package ir.unitedteches.quizApp.repository;

import ir.unitedteches.quizApp.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByExternalId(UUID externalId);

    Optional<List<Question>> findByPackId(long id);
}
