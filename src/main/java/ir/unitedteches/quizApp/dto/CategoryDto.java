package ir.unitedteches.quizApp.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Data
@Builder
public class CategoryDto {
    private UUID externalId;
    private String title;
    private MultipartFile image;
    private List<PackageDto> packageList;
    //TODO
    private byte[] imageByte;
}
