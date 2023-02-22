package ua.meta.kuzmenko.yevhenii.cafe.models;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Worker {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Worker(String name) {
        this.name = name;
    }
}
