package ua.meta.kuzmenko.yevhenii.cafe.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Statistics {

    @Id
    @GeneratedValue
    private Long id;

    private String workerName;

    private Date date;
    private double cash;
    private double card;

    public Statistics(Date date, String workerName, double cash, double card) {
        this.workerName = workerName;
        this.date = date;
        this.cash = cash;
        this.card = card;
    }

    public Statistics(Date date) {
        this.date = date;
    }
}
