package ir.unitedteches.quizApp.service;

import ir.unitedteches.quizApp.dto.AnswerDto;
import ir.unitedteches.quizApp.model.Answer;
import ir.unitedteches.quizApp.model.Question;
import ir.unitedteches.quizApp.repository.AnswerRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public UUID create(Question question, AnswerDto answerDto) {
        var answer = new Answer();
        answer.setContent(answerDto.getContent());
        answer.setQuestion(question);
        var savedAnswer = answerRepository.save(answer); //TODO check for answer id;
        return savedAnswer.getExternalId();
    }
}
