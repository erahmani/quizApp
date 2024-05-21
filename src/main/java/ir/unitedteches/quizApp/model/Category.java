package ir.unitedteches.quizApp.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    @UuidGenerator
    private UUID externalId;

    @Column(nullable = false, unique = true)
    private String title;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imageId", referencedColumnName = "id")
    private Image image;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Package> packageList;
}
