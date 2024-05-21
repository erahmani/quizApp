package ir.unitedteches.quizApp.service;

import ir.unitedteches.quizApp.dto.CategoryDto;
import ir.unitedteches.quizApp.model.Category;
import ir.unitedteches.quizApp.model.Image;
import ir.unitedteches.quizApp.repository.CategoryRepository;
import ir.unitedteches.quizApp.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ImageRepository imageRepository) {
        this.categoryRepository = categoryRepository;
        this.imageRepository = imageRepository;
    }

    public UUID create(CategoryDto categoryDto) throws IOException {
        //TODO image is null??
        var savedImage = imageRepository.save(Image.builder().name(categoryDto.getImage().getOriginalFilename())
                .type(categoryDto.getImage().getContentType())
                .imageByte(ImageUtility.compressImage(categoryDto.getImage().getBytes())).build());

        //Another api for image is required
        var savedCategory = categoryRepository.save(Category.builder().title(categoryDto.getTitle())
                .image(savedImage).build());
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
