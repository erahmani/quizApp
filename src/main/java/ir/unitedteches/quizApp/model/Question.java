package ir.unitedteches.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column
    @UuidGenerator
    private UUID externalId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false, name = "packageId")
    private Package pack;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answersList;
}
