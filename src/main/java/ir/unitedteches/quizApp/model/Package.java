package ir.unitedteches.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Data
public class Package {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column
    @UuidGenerator
    private UUID externalId;

    @Column(nullable = false)
    private long numberOfQuestions;

    @ManyToOne
    @JoinColumn(nullable = false, name = "categoryId")
    private Category category;
}
