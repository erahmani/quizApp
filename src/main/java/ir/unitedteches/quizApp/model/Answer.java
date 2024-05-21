package ir.unitedteches.quizApp.model;

import ir.unitedteches.quizApp.service.QuestionService;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
