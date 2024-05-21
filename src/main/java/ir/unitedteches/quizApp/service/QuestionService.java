package ir.unitedteches.quizApp.service;

import ir.unitedteches.quizApp.dto.QuestionDto;
import ir.unitedteches.quizApp.model.Package;
import ir.unitedteches.quizApp.model.Question;
import ir.unitedteches.quizApp.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public UUID create(Package pack, QuestionDto questionDto) {
        var savedQuestion = questionRepository.save(Question.builder().title(questionDto.getTitle())
                .pack(pack).build());
        return savedQuestion.getExternalId();
    }

    public boolean existsByPackageExternalIdAndExternalId(UUID packageExternalId, UUID externalId) {
        var question = questionRepository.findByExternalId(externalId);
        return question.isPresent() && Objects.equals(question.get().getPack().getExternalId(), packageExternalId);
    }

    public Question findByExternalId(UUID questionExternalId) {
        return questionRepository.findByExternalId(questionExternalId).orElseThrow(() -> new RuntimeException("Invalid question external id"));
    }

    public List<Question> findQuestionsByPackage(Package package_) {
        var questionList = questionRepository.findByPackId(package_.getId());
        return questionList.orElseGet(LinkedList::new);
    }
}
