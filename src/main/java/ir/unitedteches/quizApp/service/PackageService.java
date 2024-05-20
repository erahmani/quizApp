package ir.unitedteches.quizApp.service;

import ir.unitedteches.quizApp.model.Category;
import ir.unitedteches.quizApp.model.Question;
import ir.unitedteches.quizApp.repository.PackageRepository;
import ir.unitedteches.quizApp.model.Package;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PackageService {
    private final PackageRepository packageRepository;

    public PackageService(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public UUID create(Category category) {
        var package_ = new Package();
        package_.setCategory(category);
        package_.setNumberOfQuestions(0);
        var savedQuestion = packageRepository.save(package_);
        return savedQuestion.getExternalId();
    }

    public Package findByExternalId(UUID externalId) {
        return packageRepository.findByExternalId(externalId).stream().findFirst().orElseThrow(() -> new RuntimeException("Package does not exists!"));
    }

    public boolean existsByCategoryTitleAndExternalId(String categoryTitle, UUID externalId) {
        var package_ = packageRepository.findByExternalId(externalId);
        return package_.isPresent() && Objects.equals(package_.get().getCategory().getTitle(), categoryTitle);
    }


    public void increaseQuestionNumber(Package pack) {
        pack.setNumberOfQuestions(pack.getNumberOfQuestions() + 1);
        packageRepository.save(pack);
    }
}