package ir.unitedteches.quizApp.dto;

import lombok.Data;

import java.util.*;

@Data
public class QuestionDto {
    private UUID externalId;
    private String title;
    private List<AnswerDto> answers;
}
