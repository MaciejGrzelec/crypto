package com.example.demo.crypto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "coins")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
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

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public static CoinEntity fromDto(CoinsDto.Data.Coin coin){
        return CoinEntity.builder()
                .uuid(coin.getUuid())
                .symbol(coin.getSymbol())
                .name(coin.getName())
                .price(coin.getPrice())
                .rank(coin.getRank())
                .tier(coin.getTier())
                .build();
    }
}
