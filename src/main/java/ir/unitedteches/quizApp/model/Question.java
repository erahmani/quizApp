package ir.unitedteches.quizApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.*;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @UuidGenerator
    private UUID externalId;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(nullable = false, name = "packageId")
    private Package pack;

    @OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
    private List<Answer> answersList;
}
