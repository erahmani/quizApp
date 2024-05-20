package ir.unitedteches.quizApp.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AnswerDto {
    private UUID externalId;
    private String content;
}
