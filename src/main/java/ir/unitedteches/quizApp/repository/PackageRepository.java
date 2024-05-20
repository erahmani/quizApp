package ir.unitedteches.quizApp.repository;

import ir.unitedteches.quizApp.model.Package;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface PackageRepository extends JpaRepository<Package, Long> {
    Optional<Package> findByExternalId(UUID externalId);
}
