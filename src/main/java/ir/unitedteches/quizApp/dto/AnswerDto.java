package ir.unitedteches.quizApp.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class AnswerDto {
    private UUID externalId;
    private String content;
}
