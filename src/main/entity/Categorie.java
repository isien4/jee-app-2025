package src.main.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "categorie")
public class Categorie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nom")
    private String nom;

    public Categorie() {}
}
