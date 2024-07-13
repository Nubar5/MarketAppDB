package org.models;

import jakarta.persistence.*;
import lombok.*;
import org.service.impl.ItemImpl;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "items")
@Entity
public class Item {
    @Id
    @GeneratedValue(generator = "seqItem",strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "sqeItem", allocationSize = 1)
    private Long id;
    private static Long lastId=0l;
    private String barcode;
    private int count;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="sale_id",referencedColumnName = "id")
    private Sale sale;
    private Double total;
//    {
//        lastId++;
//        id=lastId;
//    }
    public void setTotal(){
        ItemImpl item = new ItemImpl();
        total=item.itemTotal(this.barcode,this.count);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", barcode='" + barcode + '\'' +
                ", count=" + count +
                ", total=" + total +
                '}';
    }
}
