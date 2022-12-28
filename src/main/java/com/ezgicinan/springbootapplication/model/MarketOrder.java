package com.ezgicinan.springbootapplication.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.*;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "MarketOrder")
public class MarketOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Integer id;

    private Integer customerId;
    private List<Integer> productList;
    private Double totalPrice;
    private String orderDay;

}
