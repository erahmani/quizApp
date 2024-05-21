package ir.unitedteches.quizApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class QuestionDto {
    private UUID externalId;
    private String title;
    private List<AnswerDto> answers;
}
