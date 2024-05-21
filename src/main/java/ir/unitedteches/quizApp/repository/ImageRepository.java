package ir.unitedteches.quizApp.repository;

import ir.unitedteches.quizApp.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
