package ua.meta.kuzmenko.yevhenii.cafe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private double price;
    private String photoName;

    public Product(String name, double price, String photoName) {
        this.name = name;
        this.price = price;
        this.photoName = photoName;
    }
}
