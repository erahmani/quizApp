package ir.unitedteches.quizApp.service;

import ir.unitedteches.quizApp.dto.CategoryDto;
import ir.unitedteches.quizApp.model.Category;
import ir.unitedteches.quizApp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public UUID create(CategoryDto categoryDto) {
        var category = new Category();
        category.setTitle(categoryDto.getTitle());
        //category.setImage(categoryDto.getImage()); TODO
        var savedCategory = categoryRepository.save(category);
        return savedCategory.getExternalId();
    }

    public boolean existsByTitle(String title) {
        return categoryRepository.findByTitle(title).isPresent();
    }

    public Category findByCategoryTitle(String categoryTitle) {
        return categoryRepository.findByTitle(categoryTitle).orElseThrow(() -> new RuntimeException("Invalid category title of category"));
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
