package com.ezgicinan.springbootapplication.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;



@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private String name;
    private String email;
    private Double customerMoney;
    private Double toBePaidMoney;
    private Double customerChange;
    private String moneyChangeByWords;

}
