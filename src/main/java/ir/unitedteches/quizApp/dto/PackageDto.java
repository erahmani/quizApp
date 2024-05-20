package ir.unitedteches.quizApp.dto;
import lombok.Data;

import java.util.UUID;

@Data
public class PackageDto {
    private UUID externalId;
    private long numberOfQuestions;
}
