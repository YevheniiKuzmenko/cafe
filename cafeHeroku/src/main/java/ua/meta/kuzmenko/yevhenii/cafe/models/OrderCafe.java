package ua.meta.kuzmenko.yevhenii.cafe.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderCafe {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Product product;
    private boolean checkIsClosed;
    private boolean payCash;

    public OrderCafe(Product product, boolean checkIsClosed) {
        this.product = product;
        this.checkIsClosed = checkIsClosed;
    }

    public OrderCafe(Product product, boolean checkIsClosed, boolean payCash) {
        this.product = product;
        this.checkIsClosed = checkIsClosed;
        this.payCash = payCash;
    }
}
