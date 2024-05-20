package ir.unitedteches.quizApp.model;

import ir.unitedteches.quizApp.service.QuestionService;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    @UuidGenerator
    private UUID externalId;

    @Column(nullable = false)
    private String content;

    @ManyToOne()
    @JoinColumn(nullable = false, name = "questionId")
    private Question question;
}
