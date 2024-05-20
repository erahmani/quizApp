package ir.unitedteches.quizApp.controller;

import ir.unitedteches.quizApp.dto.AnswerDto;
import ir.unitedteches.quizApp.dto.CategoryDto;
import ir.unitedteches.quizApp.dto.PackageDto;
import ir.unitedteches.quizApp.dto.QuestionDto;
import ir.unitedteches.quizApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public UUID createCategory(@RequestBody CategoryDto categoryDto) {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/categories")
    public List<CategoryDto> getCategoryList() {
        var categoryList = categoryService.findAll();
        var categoryDtoList = new LinkedList<CategoryDto>();
        categoryList.forEach(category -> {
            var categoryDto = new CategoryDto();
            categoryDto.setTitle(category.getTitle());
            categoryDto.setExternalId(category.getExternalId());
            var packageDtoList = new LinkedList<PackageDto>();
            if (category.getPackageList() != null) {
                category.getPackageList().forEach(pack -> {
                    var packageDto = new PackageDto();
                    packageDto.setExternalId(pack.getExternalId());
                    packageDto.setNumberOfQuestions(pack.getNumberOfQuestions());
                    packageDtoList.add(packageDto);
                });
            }
            categoryDto.setPackageList(packageDtoList);
            categoryDtoList.add(categoryDto);
        });
        return categoryDtoList;
    }
}
