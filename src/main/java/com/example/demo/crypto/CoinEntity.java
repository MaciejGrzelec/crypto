package com.example.demo.crypto;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coins")
@Data
public class CoinEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "uuid")
    private String uuid;
    private String symbol;
    private String name;
    private String price;
    private int rank;
    private int tier;
}
