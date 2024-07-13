package org.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(generator = "seqProduct",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "seqProduct", allocationSize = 1)
    private Long id;
    private static Long lastId=0l;
    @Column(unique = true)
    private String name;

    @Column(unique = true)

    private String barcode;
    @Enumerated   // list cotegory olsaydi @elementcollection - collection element
    private Category category;
    private Double price;
    private int amount;

    {
        lastId++;
        id=lastId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
