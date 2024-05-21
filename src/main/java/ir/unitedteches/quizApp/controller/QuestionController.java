package ir.unitedteches.quizApp.controller;

import ir.unitedteches.quizApp.dto.AnswerDto;
import ir.unitedteches.quizApp.dto.QuestionDto;
import ir.unitedteches.quizApp.service.CategoryService;
import ir.unitedteches.quizApp.service.PackageService;
import ir.unitedteches.quizApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class QuestionController {
    private final CategoryService categoryService;
    private final PackageService packageService;
    private final QuestionService questionService;

    @Autowired
    public QuestionController(CategoryService categoryService, PackageService packageService, QuestionService questionService) {
        this.categoryService = categoryService;
        this.packageService = packageService;
        this.questionService = questionService;
    }

    @PostMapping("/categories/{category}/packages/{package}/questions")
    public UUID createQuestion(@PathVariable("category") String categoryTitle,
                               @PathVariable("package") UUID packageExternalId,
                               @RequestBody QuestionDto questionDto) {

        if (!categoryService.existsByTitle(categoryTitle)) {
            throw new RuntimeException("Invalid category");
        }
        if (!packageService.existsByCategoryTitleAndExternalId(categoryTitle, packageExternalId)) {
            throw new RuntimeException("Invalid category and package");
        }
        var pack = packageService.findByExternalId(packageExternalId);
        var externalId = questionService.create(pack, questionDto);
        packageService.increaseQuestionNumber(pack);
        return externalId;
    }

    @GetMapping("/categories/{category}/packages/{package}/questions")
    public List<QuestionDto> getQuestionList(@PathVariable("category") String categoryTitle,
                                             @PathVariable("package") UUID packageExternalId) {
        if (!categoryService.existsByTitle(categoryTitle)) {
            throw new RuntimeException("Invalid category");
        }
        if (!packageService.existsByCategoryTitleAndExternalId(categoryTitle, packageExternalId)) {
            throw new RuntimeException("Invalid category and package");
        }
        var pack = packageService.findByExternalId(packageExternalId);
        var questionList = questionService.findQuestionsByPackage(pack);
        var questionDtoList = new LinkedList<QuestionDto>();
        questionList.forEach(question -> {
            var answersDtoList = new LinkedList<AnswerDto>();
            if (question.getAnswersList() != null) {
                question.getAnswersList().forEach(answer -> {
                    answersDtoList.add(AnswerDto.builder().externalId(answer.getExternalId())
                            .content(answer.getContent()).build());
                });
            }
            questionDtoList.add(QuestionDto.builder().title(question.getTitle())
                    .externalId(question.getExternalId())
                    .answers(answersDtoList).build());
        });
        return questionDtoList;
    }
}