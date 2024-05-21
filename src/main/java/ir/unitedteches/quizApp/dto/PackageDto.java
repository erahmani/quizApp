package ir.unitedteches.quizApp.dto;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class PackageDto {
    private UUID externalId;
    private long numberOfQuestions;
}
