package ir.unitedteches.quizApp.controller;

import ir.unitedteches.quizApp.service.CategoryService;
import ir.unitedteches.quizApp.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class PackageController {
    private final PackageService packageService;
    private final CategoryService categoryService;

    @Autowired
    public PackageController(PackageService packageService, CategoryService categoryService) {
        this.packageService = packageService;
        this.categoryService = categoryService;
    }

    @PostMapping("/categories/{category}/packages")
    public UUID createPackage(@PathVariable("category") String categoryTitle) {
        if (!categoryService.existsByTitle(categoryTitle)) {
            throw new RuntimeException("Invalid category");
        }
        var category = categoryService.findByCategoryTitle(categoryTitle);
        return packageService.create(category);
    }
}