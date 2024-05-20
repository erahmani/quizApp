package ir.unitedteches.quizApp.dto;

import lombok.Data;

import java.util.*;

@Data
public class CategoryDto {
    private UUID externalId;
    private String title;
    //private byte[] image;TODO
    private List<PackageDto> packageList;
}
