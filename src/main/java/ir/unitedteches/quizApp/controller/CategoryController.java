package ir.unitedteches.quizApp.controller;

import ir.unitedteches.quizApp.dto.CategoryDto;
import ir.unitedteches.quizApp.dto.PackageDto;
import ir.unitedteches.quizApp.service.CategoryService;
import ir.unitedteches.quizApp.service.ImageUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.zip.DataFormatException;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public UUID createCategory(@ModelAttribute CategoryDto categoryDto) throws IOException {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategoryList() {
        var categoryList = categoryService.findAll();
        var categoryDtoList = new LinkedList<CategoryDto>();
        categoryList.forEach(category -> {
            var packageDtoList = new LinkedList<PackageDto>();
            if (category.getPackageList() != null) {
                category.getPackageList().forEach(pack -> {
                    packageDtoList.add(PackageDto.builder().externalId(pack.getExternalId())
                            .numberOfQuestions(pack.getNumberOfQuestions()).build());
                });
            }
            try {
                categoryDtoList.add(CategoryDto.builder().title(category.getTitle())
                        .externalId(category.getExternalId())
                        .packageList(packageDtoList)
                        .imageByte(ImageUtility.decompressImage(category.getImage().getImageByte())).build()); //TODO
            } catch (IOException | DataFormatException e) {
                throw new RuntimeException(e);
            }
        });
        return categoryDtoList;
    }
}
