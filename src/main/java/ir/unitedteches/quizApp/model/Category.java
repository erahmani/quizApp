package ir.unitedteches.quizApp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long id;

    @Column
    @UuidGenerator
    private UUID externalId;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String image; //TODO

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Package> packageList;
}
