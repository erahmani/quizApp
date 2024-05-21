package ir.unitedteches.quizApp.controller;

import ir.unitedteches.quizApp.dto.AnswerDto;
import ir.unitedteches.quizApp.service.AnswerService;
import ir.unitedteches.quizApp.service.CategoryService;
import ir.unitedteches.quizApp.service.PackageService;
import ir.unitedteches.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class AnswerController {
    private final CategoryService categoryService;
    private final PackageService packageService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @Autowired
    public AnswerController(CategoryService categoryService, PackageService packageService, QuestionService questionService, AnswerService answerService) {
        this.categoryService = categoryService;
        this.packageService = packageService;
        this.questionService = questionService;
        this.answerService = answerService;
    }

    @PostMapping("/categories/{category}/packages/{package}/questions/{question}/answers")
    public UUID createAnswer(@PathVariable("category") String categoryTitle,
                             @PathVariable("package") UUID packageExternalId,
                             @PathVariable("question") UUID questionExternalId,
                             @RequestBody AnswerDto answerDto) {

        if (!categoryService.existsByTitle(categoryTitle)) {
            throw new RuntimeException("Invalid category");
        }
        if (!packageService.existsByCategoryTitleAndExternalId(categoryTitle, packageExternalId)) {
            throw new RuntimeException("Invalid category and package");
        }
        if (!questionService.existsByPackageExternalIdAndExternalId(packageExternalId, questionExternalId)){
            throw new RuntimeException("Invalid package and question");
        }

        var question = questionService.findByExternalId(questionExternalId);
        return answerService.create(question, answerDto);
    }
}
