package src.main.entity;

import javax.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix")
    private double prix;

    @Column(name = "quantite")
    private int quantite;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Product() {}
}
